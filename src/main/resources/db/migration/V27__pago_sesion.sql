-- sql
-- Secuencia para numeración de códigos
CREATE SEQUENCE IF NOT EXISTS pago_sesion_codigo_seq
START WITH 1;

-- Tabla pago_sesion con columna codigo (se generará si no se provee)
CREATE TABLE IF NOT EXISTS pago_sesion (
    id SERIAL PRIMARY KEY,
    paciente_id TEXT NOT NULL,
    session_id INTEGER NOT NULL REFERENCES session(id) ON DELETE CASCADE,
    monto NUMERIC(12,2) NOT NULL CHECK (monto >= 0),
    pagado BOOLEAN NOT NULL DEFAULT TRUE,
    fecha_pago TIMESTAMP NOT NULL DEFAULT NOW(),
    notas TEXT,
    comprobante TEXT,
    codigo VARCHAR(50),
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

-- Índices
CREATE INDEX IF NOT EXISTS idx_pago_sesion_session_id ON pago_sesion(session_id);
CREATE UNIQUE INDEX IF NOT EXISTS idx_pago_sesion_codigo ON pago_sesion(codigo);

-- Función que genera el código si no viene en la inserción
CREATE OR REPLACE FUNCTION fn_generate_codigo_pago_sesion()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.codigo IS NULL OR TRIM(NEW.codigo) = '' THEN
        NEW.codigo :=
            'FACT-' ||
            EXTRACT(YEAR FROM CURRENT_DATE)::text || '-' ||
            LPAD(nextval('pago_sesion_codigo_seq')::text, 6, '0');
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Trigger que llama a la función antes de insertar
DROP TRIGGER IF EXISTS trg_generate_codigo_pago_sesion ON pago_sesion;

CREATE TRIGGER trg_generate_codigo_pago_sesion
    BEFORE INSERT ON pago_sesion
    FOR EACH ROW
    EXECUTE FUNCTION fn_generate_codigo_pago_sesion();