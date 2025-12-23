
DO
$$
BEGIN
    -- Renombrar columna solo si existe
    IF EXISTS (
        SELECT 1
        FROM information_schema.columns
        WHERE table_name = 'historial_personal'
          AND column_name = 'hc_'
    ) THEN
        ALTER TABLE historial_personal RENAME COLUMN "hc_" TO hc_id;
    END IF;

    -- Agregar FK solo si no existe ya
    IF NOT EXISTS (
        SELECT 1
        FROM pg_constraint c
        JOIN pg_class t ON c.conrelid = t.oid
        JOIN pg_namespace n ON t.relnamespace = n.oid
        WHERE t.relname = 'historial_personal'
          AND c.conname = 'fk_historial_personal_hc_id'
    ) THEN
        ALTER TABLE historial_personal
            ADD CONSTRAINT fk_historial_personal_hc_id
            FOREIGN KEY (hc_id) REFERENCES historia_clinica(id) ON DELETE CASCADE;
    END IF;
END
$$;