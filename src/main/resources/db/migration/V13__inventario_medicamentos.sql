
CREATE TABLE categorias (
                            id SERIAL PRIMARY KEY,
                            nombre VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE proveedores (
                             id SERIAL PRIMARY KEY,
                             nombre VARCHAR(150) NOT NULL
);

CREATE TABLE unidades_medida (
                                 id SERIAL PRIMARY KEY,
                                 nombre VARCHAR(20) UNIQUE NOT NULL,
                                 simbolo VARCHAR(10) UNIQUE NOT NULL
);

CREATE TABLE formas_farmaceuticas (
                                      id SERIAL PRIMARY KEY,
                                      nombre VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE ubicaciones (
                             id SERIAL PRIMARY KEY,
                             nombre VARCHAR(100) NOT NULL
);



CREATE TABLE principios_activos (
                                    id SERIAL PRIMARY KEY,
                                    nombre VARCHAR(150) UNIQUE NOT NULL
);


CREATE TABLE medicamentos (
                              id SERIAL PRIMARY KEY,
                              nombre_comercial VARCHAR(150) NOT NULL,
                              forma_farmaceutica_id INT REFERENCES formas_farmaceuticas(id),
                              categoria_id INT REFERENCES categorias(id),
                              unidades_por_empaque INT NOT NULL,
                              stock_minimo INT DEFAULT 0,
                              precio_venta NUMERIC(10,2) NOT NULL,
                              activo BOOLEAN DEFAULT TRUE
);



CREATE TABLE medicamento_principio_activo (
                                              id SERIAL PRIMARY KEY,
                                              medicamento_id INT REFERENCES medicamentos(id) ON DELETE CASCADE,
                                              principio_activo_id INT REFERENCES principios_activos(id),
                                              concentracion NUMERIC(10,2) NOT NULL,
                                              unidad_medida_id INT REFERENCES unidades_medida(id),
                                              UNIQUE (medicamento_id, principio_activo_id)
);

CREATE TABLE lotes (
                       id SERIAL PRIMARY KEY,
                       medicamento_id INT REFERENCES medicamentos(id) ON DELETE CASCADE,
                       proveedor_id INT REFERENCES proveedores(id),
                       ubicacion_id INT REFERENCES ubicaciones(id),
                       numero_lote VARCHAR(50),
                       fecha_vencimiento DATE NOT NULL,
                       cantidad INT NOT NULL CHECK (cantidad >= 0),
                       precio_compra NUMERIC(10,2)
);


CREATE TABLE movimientos_inventario (
                                        id SERIAL PRIMARY KEY,
                                        lote_id INT REFERENCES lotes(id) ON DELETE CASCADE,
                                        tipo_movimiento VARCHAR(20)
                                            CHECK (tipo_movimiento IN ('ENTRADA','SALIDA')),
                                        cantidad INT NOT NULL,
                                        fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                        observacion TEXT
);



CREATE OR REPLACE FUNCTION fn_actualizar_stock()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.tipo_movimiento = 'SALIDA' THEN
UPDATE lotes
SET cantidad = cantidad - NEW.cantidad
WHERE id = NEW.lote_id;
ELSE
UPDATE lotes
SET cantidad = cantidad + NEW.cantidad
WHERE id = NEW.lote_id;
END IF;
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_stock
    AFTER INSERT ON movimientos_inventario
    FOR EACH ROW
    EXECUTE FUNCTION fn_actualizar_stock();




INSERT INTO categorias (nombre) VALUES ('Analgésicos');
INSERT INTO proveedores (nombre) VALUES ('Proveedor Central');

INSERT INTO unidades_medida (nombre, simbolo) VALUES
    ('Miligramo','mg');

INSERT INTO formas_farmaceuticas (nombre) VALUES ('Tableta');
INSERT INTO ubicaciones (nombre) VALUES ('Bodega Principal');

INSERT INTO principios_activos (nombre)
VALUES ('Ácido acetilsalicílico');



INSERT INTO medicamentos (
    nombre_comercial,
    forma_farmaceutica_id,
    categoria_id,
    unidades_por_empaque,
    stock_minimo,
    precio_venta
) VALUES (
             'Aspirina',
             (SELECT id FROM formas_farmaceuticas WHERE nombre='Tableta'),
             (SELECT id FROM categorias WHERE nombre='Analgésicos'),
             20,
             50,
             35.00
         );

INSERT INTO medicamento_principio_activo (
    medicamento_id,
    principio_activo_id,
    concentracion,
    unidad_medida_id
) VALUES (
             (SELECT id FROM medicamentos WHERE nombre_comercial='Aspirina'),
             (SELECT id FROM principios_activos WHERE nombre='Ácido acetilsalicílico'),
             800,
             (SELECT id FROM unidades_medida WHERE simbolo='mg')
         );


INSERT INTO lotes (
    medicamento_id,
    proveedor_id,
    ubicacion_id,
    numero_lote,
    fecha_vencimiento,
    cantidad,
    precio_compra
) VALUES (
             (SELECT id FROM medicamentos WHERE nombre_comercial='Aspirina'),
             1,
             1,
             'L001',
             '2026-06-30',
             100,
             25.00
         );


INSERT INTO movimientos_inventario (
    lote_id,
    tipo_movimiento,
    cantidad,
    observacion
) VALUES (
             (SELECT id FROM lotes WHERE numero_lote='L001'),
             'ENTRADA',
             100,
             'Compra inicial'
         );


INSERT INTO movimientos_inventario (
    lote_id,
    tipo_movimiento,
    cantidad,
    observacion
) VALUES (
             (SELECT id FROM lotes WHERE numero_lote='L001'),
             'SALIDA',
             3,
             'Venta mostrador'
         );

