CREATE TABLE evaluacion_psicologica(
    id SERIAL PRIMARY KEY,
    hc_id BIGINT NOT NULL REFERENCES historia_clinica(id) ON DELETE CASCADE,
    estado_animo INT NOT NULL,
    ansiedad INT NOT NULL,
    funcionamiento_social INT NOT NULL,
    calidad_sueno INT NOT NULL,
    apetito INT NOT NULL,
    observaciones_generales TEXT
);

CREATE  TABLE tipo_pruebas(
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion TEXT
);

CREATE TABLE prueba_aplicadas(
    id SERIAL PRIMARY KEY,
    hc_id BIGINT NOT NULL REFERENCES historia_clinica(id) ON DELETE CASCADE,
    prueba_id BIGINT NOT NULL REFERENCES tipo_pruebas(id) ON DELETE CASCADE,
    fecha_aplicacion DATE default NOW(),
    resultado DECIMAL(5,2),
    interpretacion TEXT,
    archivo TEXT
);


CREATE TABLE diagnostico_dsm_5(
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    codigo VARCHAR NOT NULL,
    description TEXT
);

CREATE TABLE diagnostico_cie_11(
                                  id SERIAL PRIMARY KEY,
                                  nombre VARCHAR(255) NOT NULL,
                                  codigo VARCHAR NOT NULL,
                                  description TEXT
);



CREATE TABLE impresion_diagnostica(
    id SERIAL PRIMARY KEY,
    hc_id BIGINT NOT NULL REFERENCES historia_clinica(id) ON DELETE CASCADE,
    diagnostico_principal_cie_11 BIGINT NOT NULL REFERENCES  diagnostico_cie_11(id)ON DELETE CASCADE,
    --- no estoy seguro
    diagnostico_principal_dsm_5 BIGINT NOT NULL REFERENCES  diagnostico_dsm_5(id)ON DELETE CASCADE,
    factores_predisponentes TEXT,
    factores_precipiantes TEXT,
    factores_mantenedores TEXT,
    nivel_funcionamiento INT NOT NULL

);


