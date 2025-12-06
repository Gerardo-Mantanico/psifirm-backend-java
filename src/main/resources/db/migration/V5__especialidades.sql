CREATE  TABLE  especialidades (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE ,
    descripcion TEXT,
    created_at   timestamp(3) without time zone                    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at   timestamp(3) without time zone                    NOT NULL
);

CREATE TABLE user_especialidades (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    especialidad_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES public.user(id) ON DELETE CASCADE,
    FOREIGN KEY (especialidad_id) REFERENCES especialidades(id) ON DELETE CASCADE,
    created_at   timestamp(3) without time zone                    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at   timestamp(3) without time zone                    NOT NULL

);