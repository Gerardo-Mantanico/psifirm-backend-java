CREATE TABLE historia_clinica(
                                 id SERIAL PRIMARY KEY,
                                 nombre_institucion VARCHAR NOT NULL,
                                 servicio_id BIGINT NOT NULL REFERENCES servicios(id) ON DELETE CASCADE,
                                 servicio VARCHAR NOT NULL,
                                 code VARCHAR NOT NULL UNIQUE,
                                 date DATE DEFAULT CURRENT_DATE,
                                 psicologo_id BIGINT NOT NULL,
                                 psicologo VARCHAR NOT NULL,
                                 colegiado_psicologo INTEGER,
                                 estado BOOLEAN DEFAULT TRUE
);

CREATE INDEX idx_codigo ON historia_clinica(code);

-- Secuencia correcta
CREATE SEQUENCE code_seq START 1;

-- Función corregida
CREATE OR REPLACE FUNCTION fn_generate_code()
RETURNS TRIGGER AS
$$
BEGIN
    NEW.code := 'HC-' || EXTRACT(YEAR FROM CURRENT_DATE) || '-' ||
                LPAD(nextval('code_seq')::text, 6, '0');
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Trigger en la tabla correcta
CREATE TRIGGER trg_generate_code
    BEFORE INSERT ON historia_clinica
    FOR EACH ROW
    EXECUTE FUNCTION fn_generate_code();

CREATE  TABLE informacion_paciente(
    id SERIAL PRIMARY KEY,
    hc_id BIGINT NOT NULL REFERENCES historia_clinica(id) ON DELETE CASCADE,
    edad INTEGER NOT NULL,
    nombre_completo VARCHAR NOT NULL,
    genero VARCHAR NOT NULL,
    estado_civil VARCHAR NOT NULL,
    ocupacion VARCHAR NOT NULL,
    nivel_educativo VARCHAR NOT NULL,
    direccion TEXT NOT NULL,
    telefono VARCHAR NOT NULL,
    email VARCHAR NOT NULL,
    persona_contacto VARCHAR NOT NULL,
    parentesco_contacto VARCHAR NOT NULL,
    telefono_contacto VARCHAR NOT NULL,
    procedencia VARCHAR NOT NULL,
    motivo_consulta TEXT NOT NULL
);


CREATE  TABLE antecedentes_personales(
    id SERIAL PRIMARY KEY,
    hc_id BIGINT NOT NULL REFERENCES historia_clinica(id) ON DELETE CASCADE,
    estructura_fa TEXT NOT NULL,
    trastornos BOOLEAN,
    trastorno_detalles TEXT,
    eventos_fr TEXT NOT NULL
);

CREATE  TABLE historial_personal(
    id SERIAL PRIMARY KEY ,
    hc_ BIGINT NOT NULL,
    desarrollo_evolutivo TEXT,
    h_academica TEXT,
    h_medica TEXT,
    medicacion_actual TEXT,
    consumo_alcohol   VARCHAR(20) CHECK (consumo_alcohol IN
                                                    ('NUNCA','OCASIONAL','MODERADO','EXCESIVO')),
    consumo_tabaco VARCHAR(20) CHECK (consumo_tabaco IN
                                                    ('NUNCA','OCASIONAL','FUMADOR','EXFUMADOR')),
    consumo_drogras TEXT,
    tratamientos_previos BOOLEAN,
    tratamientos_previos_detalles TEXT,
    Hospitalizaciones BOOLEAN,
    Hospitalizaciones_detalles TEXT

);


