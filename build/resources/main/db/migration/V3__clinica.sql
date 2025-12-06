CREATE TABLE clinica (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    correo VARCHAR(255) NOT NULL UNIQUE,
    telefono VARCHAR(20) unique,
    direccion VARCHAR(100),
    horas_atencion TIME NOT NULL,
    hora_cierre TIME NOT NULL,
    nit VARCHAR(50) UNIQUE NOT NULL,
    registro_sanitario VARCHAR(50) UNIQUE NOT NULL
);

INSERT INTO clinica (
    nombre,
    correo,
    telefono,
    direccion,
    horas_atencion,
    hora_cierre,
    nit,
    registro_sanitario
) VALUES (
             'Clínica San Rafael',
             'contacto@sanrafael.com',
             '77664512',
             '6a Avenida 4-55 Zona 1, Quetzaltenango',
             '08:00',
             '17:00',
             '1234567-8',
             'RS-987654'
         );
