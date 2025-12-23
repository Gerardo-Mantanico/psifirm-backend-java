-- src/main/resources/db/migration/V25__insert_tipo_prueba.sql
INSERT INTO tipo_pruebas (id, nombre, descripcion) VALUES
  (1, 'BDI', 'Inventario de Depresión de Beck'),
  (2, 'BAI', 'Inventario de Ansiedad de Beck'),
  (3, 'MMPI', 'Inventario Multifásico de Personalidad de Minnesota'),
  (4, 'WAIS', 'Escala de Inteligencia de Wechsler para Adultos'),
  (5, 'Otro', NULL);
