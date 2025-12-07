CREATE  TABLE  area (
                        id SERIAL PRIMARY KEY,
                        nombre VARCHAR(100) NOT NULL UNIQUE ,
                        descripcion TEXT,
                        created_at   timestamp(3) without time zone                    NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        updated_at   timestamp(3) without time zone                    NOT NULL
);

-- Insertar áreas iniciales
INSERT INTO area (nombre, descripcion, created_at, updated_at) VALUES
('Apoyo psicológico', 'Servicios de apoyo psicológico individual y grupal para niños y familias', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Psiquiatría', 'Evaluación y tratamiento psiquiátrico, manejo farmacológico y seguimiento', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Terapias para dificultades de aprendizaje infantil', 'Programas y terapias específicas para trastornos del aprendizaje en edad infantil', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

