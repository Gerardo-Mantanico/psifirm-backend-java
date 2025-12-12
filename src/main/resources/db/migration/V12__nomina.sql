
CREATE TABLE metodo_pago (
                             id SERIAL PRIMARY KEY,
                             descripcion VARCHAR(100) UNIQUE NOT NULL
);

INSERT INTO metodo_pago (descripcion) VALUES
                                          ('Depósito bancario'),
                                          ('Cheque'),
                                          ('Efectivo'),
                                          ('Transferencia electrónica');


CREATE TABLE tipo_bono (
                           id SERIAL PRIMARY KEY,
                           descripcion VARCHAR(100) UNIQUE NOT NULL
);

INSERT INTO tipo_bono (descripcion) VALUES
                                        ('Bono de puntualidad'),
                                        ('Bono de productividad'),
                                        ('Bono de desempeño'),
                                        ('Bono extra por horas trabajadas'),
                                        ('Bono por antigüedad');


CREATE TABLE tipo_retencion (
                                id SERIAL PRIMARY KEY,
                                descripcion VARCHAR(100) UNIQUE NOT NULL
);

INSERT INTO tipo_retencion (descripcion) VALUES
                                             ('IGSS'),
                                             ('ISR'),
                                             ('Retención judicial'),
                                             ('Descuento por préstamo');


CREATE TABLE tipo_descuento (
                                id SERIAL PRIMARY KEY,
                                descripcion VARCHAR(100) UNIQUE NOT NULL
);

INSERT INTO tipo_descuento (descripcion) VALUES
                                             ('Descuento por tardanza'),
                                             ('Descuento por inasistencia'),
                                             ('Descuento por daño a propiedad'),
                                             ('Descuento por adelanto de sueldo');



CREATE TABLE nominas (
                         id SERIAL PRIMARY KEY,
    -- NOTA: Asume que la tabla  users' ya existe
                         user_id INT REFERENCES public.user(id) ON DELETE CASCADE,
                         periodo DATE NOT NULL,
                         salario_base NUMERIC(10,2) NOT NULL,
                         sesiones_trabajadas INT DEFAULT 0,
    -- Campo calculado (Total de ingresos: Base + Variables + Bonos)
                         salario_bruto NUMERIC(10,2) NOT NULL,
    -- Campo calculado (Monto neto adeudado: Bruto - Retenciones - Descuentos)
                         salario_neto_adeudado NUMERIC(10,2) NOT NULL,
                         metodo_pago_id INT REFERENCES metodo_pago(id) ON DELETE SET NULL,
                         detalle_pago TEXT,
                         fecha_cierre DATE DEFAULT CURRENT_DATE
);



CREATE TABLE nomina_bonos (
                              id SERIAL PRIMARY KEY,
                              nomina_id INT REFERENCES nominas(id) ON DELETE CASCADE,
                              tipo_bono_id INT REFERENCES tipo_bono(id) ON DELETE CASCADE,
                              monto NUMERIC(10,2) NOT NULL
);


CREATE TABLE nomina_retenciones (
                                    id SERIAL PRIMARY KEY,
                                    nomina_id INT REFERENCES nominas(id) ON DELETE CASCADE,
                                    tipo_retencion_id INT REFERENCES tipo_retencion(id) ON DELETE CASCADE,
                                    monto NUMERIC(10,2) NOT NULL
);


CREATE TABLE nomina_descuentos (
                                   id SERIAL PRIMARY KEY,
                                   nomina_id INT REFERENCES nominas(id) ON DELETE CASCADE,
                                   tipo_descuento_id INT REFERENCES tipo_descuento(id) ON DELETE CASCADE,
                                   monto NUMERIC(10,2) NOT NULL
);



CREATE TABLE historial_pagos (
                                 id SERIAL PRIMARY KEY,
                                 nomina_id INT REFERENCES nominas(id) ON DELETE CASCADE,
                                 user_id INT REFERENCES public.user(id) ON DELETE CASCADE,
                                 periodo DATE,
                                 monto_pagado NUMERIC(10,2) NOT NULL, -- EL MONTO REAL DE LA TRANSACCIÓN
                                 fecha_pago DATE DEFAULT CURRENT_DATE, -- Fecha exacta de la transferencia/entrega
                                 estado_pago VARCHAR(50) DEFAULT 'Completado'
);



CREATE TABLE comprobantes (
                              id SERIAL PRIMARY KEY,
                              nomina_id INT REFERENCES nominas(id) ON DELETE CASCADE,
                              folio VARCHAR(50) UNIQUE,
                              detalle TEXT,
                              firma_digital VARCHAR(255),
                              fecha_generacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


-- Ahora crear la nueva función y triggers
CREATE OR REPLACE FUNCTION actualizar_salario_nomina(nomina_id_param INT)
    RETURNS VOID AS
$$
DECLARE
v_salario_base NUMERIC(10, 2);
    v_total_bonos NUMERIC(10, 2);
    v_total_retenciones NUMERIC(10, 2);
    v_total_descuentos NUMERIC(10, 2);
    v_salario_bruto NUMERIC(10, 2);
    v_salario_neto NUMERIC(10, 2);
BEGIN
    -- 1. Obtener la base de la nómina
SELECT salario_base INTO v_salario_base
FROM nominas WHERE id = nomina_id_param;

-- 2. Sumar Bonos (Ingresos)
SELECT COALESCE(SUM(monto), 0) INTO v_total_bonos
FROM nomina_bonos WHERE nomina_id = nomina_id_param;

-- 3. Sumar Retenciones (Egresos obligatorios)
SELECT COALESCE(SUM(monto), 0) INTO v_total_retenciones
FROM nomina_retenciones WHERE nomina_id = nomina_id_param;

-- 4. Sumar Descuentos (Egresos especiales)
SELECT COALESCE(SUM(monto), 0) INTO v_total_descuentos
FROM nomina_descuentos WHERE nomina_id = nomina_id_param;

-- 5. Calcular Salario Bruto y Neto
v_salario_bruto := v_salario_base + v_total_bonos;
    v_salario_neto := v_salario_bruto - v_total_retenciones - v_total_descuentos;

    -- 6. Actualizar
UPDATE nominas
SET
    salario_bruto = v_salario_bruto,
    salario_neto_adeudado = v_salario_neto
WHERE id = nomina_id_param;
END;
$$
LANGUAGE plpgsql;

-- Trigger único para INSERT en nominas
CREATE OR REPLACE FUNCTION trigger_nominas_insert()
    RETURNS TRIGGER AS
$$
BEGIN
    -- Para nueva nómina, calcular valores iniciales
    NEW.salario_bruto := NEW.salario_base;
    NEW.salario_neto_adeudado := NEW.salario_base;
RETURN NEW;
END;
$$
LANGUAGE plpgsql;

-- Trigger BEFORE para establecer valores iniciales
CREATE TRIGGER nominas_before_insert
    BEFORE INSERT ON nominas
    FOR EACH ROW
    EXECUTE FUNCTION trigger_nominas_insert();

-- Función única para tablas relacionadas
CREATE OR REPLACE FUNCTION trigger_calcular_salario()
    RETURNS TRIGGER AS
$$
DECLARE
v_nomina_id INT;
BEGIN
    -- Determinar el ID de la nómina afectada
    IF TG_OP = 'DELETE' THEN
        v_nomina_id := OLD.nomina_id;
ELSE
        v_nomina_id := NEW.nomina_id;
END IF;

    -- Llamar a la función de actualización
    PERFORM actualizar_salario_nomina(v_nomina_id);

RETURN NULL;
END;
$$
LANGUAGE plpgsql;

-- Triggers simplificados
CREATE TRIGGER bonos_after_changes
    AFTER INSERT OR UPDATE OR DELETE ON nomina_bonos
    FOR EACH ROW
    EXECUTE FUNCTION trigger_calcular_salario();

CREATE TRIGGER retenciones_after_changes
    AFTER INSERT OR UPDATE OR DELETE ON nomina_retenciones
    FOR EACH ROW
    EXECUTE FUNCTION trigger_calcular_salario();

CREATE TRIGGER descuentos_after_changes
    AFTER INSERT OR UPDATE OR DELETE ON nomina_descuentos
    FOR EACH ROW
    EXECUTE FUNCTION trigger_calcular_salario();