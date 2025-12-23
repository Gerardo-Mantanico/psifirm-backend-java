DO $$
BEGIN
  -- objetivo cortoplazo (múltiples variantes)
  IF EXISTS (
    SELECT 1 FROM information_schema.columns
    WHERE table_schema = 'public' AND table_name = 'configuracion_tratamiento' AND column_name = 'objetivocortoplazo'
  ) THEN
    ALTER TABLE configuracion_tratamiento RENAME COLUMN objetivocortoplazo TO objetivo_cortoplazo;
  END IF;

  IF EXISTS (
    SELECT 1 FROM information_schema.columns
    WHERE table_schema = 'public' AND table_name = 'configuracion_tratamiento' AND column_name = 'objetivoCortoplazo'
  ) THEN
    EXECUTE 'ALTER TABLE configuracion_tratamiento RENAME COLUMN "objetivoCortoplazo" TO objetivo_cortoplazo';
  END IF;

  -- objetivo medioplazo
  IF EXISTS (
    SELECT 1 FROM information_schema.columns
    WHERE table_schema = 'public' AND table_name = 'configuracion_tratamiento' AND column_name = 'objetivomedioplazo'
  ) THEN
    ALTER TABLE configuracion_tratamiento RENAME COLUMN objetivomedioplazo TO objetivo_medioplazo;
  END IF;

  IF EXISTS (
    SELECT 1 FROM information_schema.columns
    WHERE table_schema = 'public' AND table_name = 'configuracion_tratamiento' AND column_name = 'objetivoMedioplazo'
  ) THEN
    EXECUTE 'ALTER TABLE configuracion_tratamiento RENAME COLUMN "objetivoMedioplazo" TO objetivo_medioplazo';
  END IF;

  -- objetivo largoplazo
  IF EXISTS (
    SELECT 1 FROM information_schema.columns
    WHERE table_schema = 'public' AND table_name = 'configuracion_tratamiento' AND column_name = 'objetivolargoplazo'
  ) THEN
    ALTER TABLE configuracion_tratamiento RENAME COLUMN objetivolargoplazo TO objetivo_largoplazo;
  END IF;

  IF EXISTS (
    SELECT 1 FROM information_schema.columns
    WHERE table_schema = 'public' AND table_name = 'configuracion_tratamiento' AND column_name = 'objetivoLargoplazo'
  ) THEN
    EXECUTE 'ALTER TABLE configuracion_tratamiento RENAME COLUMN "objetivoLargoplazo" TO objetivo_largoplazo';
  END IF;
END
$$ LANGUAGE plpgsql;