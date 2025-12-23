-- sql
CREATE OR REPLACE FUNCTION trg_set_numero_sesiones_and_update_cita()
RETURNS trigger AS $$
BEGIN
  -- Bloquea la fila de historia_clinica para serializar inserciones por hc_id
  PERFORM 1 FROM historia_clinica WHERE id = NEW.hc_id FOR UPDATE;

  -- Calcula el siguiente número de sesiones para ese hc_id
  SELECT COALESCE(MAX(numero_sesiones), 0) + 1
    INTO NEW.numero_sesiones
    FROM session
   WHERE hc_id = NEW.hc_id;

  -- Si hay proxima_cita, actualiza la cita más reciente de cita_medica para ese hc_id
  IF NEW.proxima_cita IS NOT NULL THEN
    UPDATE cita_medica
    SET fecha = NEW.proxima_cita
    WHERE id = (
      SELECT id
      FROM cita_medica
      WHERE hc_id = NEW.hc_id
      ORDER BY fecha DESC
      LIMIT 1
    );
    -- Si no existe ninguna cita_medica para ese hc_id, la subconsulta devuelve NULL y no se actualiza nada
  END IF;

  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS before_insert_session_set_numero ON session;
CREATE TRIGGER before_insert_session_set_numero
BEFORE INSERT ON session
FOR EACH ROW
EXECUTE FUNCTION trg_set_numero_sesiones_and_update_cita();