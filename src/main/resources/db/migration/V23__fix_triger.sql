-- sql
DROP FUNCTION IF EXISTS trg_session_before_insert() CASCADE;
CREATE OR REPLACE FUNCTION trg_session_before_insert()
    RETURNS trigger AS $$
DECLARE
v_next integer;
    v_cita_id bigint;
BEGIN
    PERFORM 1 FROM historia_clinica WHERE id = NEW.hc_id FOR UPDATE;

SELECT COALESCE(MAX(numero_sesiones), 0) + 1
INTO v_next
FROM session
WHERE hc_id = NEW.hc_id;
NEW.numero_sesiones := v_next;

    IF NEW.proxima_cita IS NOT NULL THEN
SELECT id
INTO v_cita_id
FROM cita_medica
WHERE hc_id = NEW.hc_id
ORDER BY fecha_cita DESC    -- <- reemplazar `fecha_cita` por el nombre correcto de la columna de fecha
    LIMIT 1
            FOR UPDATE;

IF v_cita_id IS NOT NULL THEN
UPDATE cita_medica
SET fecha_cita = NEW.proxima_cita   -- <- reemplazar si el nombre real es distinto
WHERE id = v_cita_id;
END IF;
END IF;

RETURN NEW;
END;
$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS trg_before_insert_session ON session;
CREATE TRIGGER trg_before_insert_session
    BEFORE INSERT ON session
    FOR EACH ROW
    EXECUTE FUNCTION trg_session_before_insert();
