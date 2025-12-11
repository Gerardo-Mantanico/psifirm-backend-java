CREATE  TABLE  especialidades (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE ,
    descripcion TEXT,
    created_at   timestamp(3) without time zone                    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at   timestamp(3) without time zone                    NOT NULL
);

-- Inserts iniciales para la tabla especialidades
INSERT INTO especialidades (nombre, descripcion, updated_at) VALUES
('Psicólogo', 'Atención psicológica: evaluación, terapia individual y grupal, intervención psicoeducativa.', CURRENT_TIMESTAMP),
('Psiquiatra', 'Atención psiquiátrica: diagnóstico médico, manejo farmacológico y seguimiento de trastornos mentales.', CURRENT_TIMESTAMP),
('Neuropsicología', 'Evaluación y rehabilitación neuropsicológica de funciones cognitivas; pruebas y programas de intervención.', CURRENT_TIMESTAMP);



CREATE  TABLE informacion_laboral_empleados (
    id SERIAL PRIMARY KEY,
    especialidad_id INT NOT NULL REFERENCES especialidades(id) ON DELETE CASCADE,
    user_id INT NOT NULL REFERENCES public.user(id) ON DELETE CASCADE UNIQUE ,
    colegiado VARCHAR(100),
    area_id INT NOT NULL REFERENCES area(id) ON DELETE CASCADE
);


CREATE TABLE horarios (
                          id SERIAL PRIMARY KEY,
                          user_Id INT REFERENCES public.user(id) ON DELETE CASCADE,
                          dia_semana VARCHAR(15),  -- lunes, martes, etc.
                          hora_inicio TIME,
                          hora_fin TIME
);


