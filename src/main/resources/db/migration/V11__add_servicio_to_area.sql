-- Agregar columna servicio_id a la tabla area
ALTER TABLE area ADD COLUMN servicio_id INT;

-- Agregar la foreign key constraint
ALTER TABLE area ADD CONSTRAINT fk_area_servicio
    FOREIGN KEY (servicio_id) REFERENCES servicios(id) ON DELETE CASCADE;



