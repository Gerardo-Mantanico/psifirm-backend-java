





-- Insertar servicios iniciales (evita duplicados con ON CONFLICT)
INSERT INTO servicios (nombre, descripcion, precio, created_at, updated_at)
VALUES (
  'Servicios clínicos',
  'Atención clínica integral: consultas, evaluación y seguimiento médico',
  50.00,
  CURRENT_TIMESTAMP,
  CURRENT_TIMESTAMP
) ON CONFLICT (nombre) DO NOTHING;

INSERT INTO servicios (nombre, descripcion, precio, created_at, updated_at)
VALUES (
  'Diagnósticos',
  'Evaluaciones y pruebas diagnósticas especializadas (tests, imágenes, evaluación funcional)',
  75.00,
  CURRENT_TIMESTAMP,
  CURRENT_TIMESTAMP
) ON CONFLICT (nombre) DO NOTHING;

INSERT INTO servicios (nombre, descripcion, precio, created_at, updated_at)
VALUES (
  'Actividades de apoyo terapéutico',
  'Talleres y actividades de apoyo terapéutico grupal e individual para promoción y rehabilitación',
  30.00,
  CURRENT_TIMESTAMP,
  CURRENT_TIMESTAMP
) ON CONFLICT (nombre) DO NOTHING;

