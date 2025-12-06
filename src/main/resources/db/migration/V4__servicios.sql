CREATE  TABLE  servicios (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE,
    descripcion TEXT,
    precio DECIMAL(10, 2) NOT NULL,
    created_at   timestamp(3) without time zone                    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at   timestamp(3) without time zone                    NOT NULL
);