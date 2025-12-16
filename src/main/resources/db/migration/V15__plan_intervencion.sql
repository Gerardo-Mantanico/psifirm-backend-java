
CREATE TABLE tipo_objetivo (
                               id SERIAL PRIMARY KEY,
                               tipo_objetivo TEXT NOT NULL,
                               tiempo VARCHAR UNIQUE NOT NULL
);

CREATE TABLE modalidad_intervencion (
                                        id SERIAL PRIMARY KEY,
                                        nombre VARCHAR NOT NULL UNIQUE
);

CREATE TABLE enfoque_terapeutico (
                                     id SERIAL PRIMARY KEY,
                                     nombre VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE frecuencia (
                            id SERIAL PRIMARY KEY,
                            descripcion VARCHAR(255) NOT NULL UNIQUE
);



INSERT INTO modalidad_intervencion (nombre) VALUES
                                                ('Individual'),
                                                ('Familiar'),
                                                ('Pareja'),
                                                ('Grupo');

INSERT INTO enfoque_terapeutico (nombre) VALUES
                                             ('Cognitivo-Conductual'),
                                             ('Sistémico'),
                                             ('Psicodinámico'),
                                             ('Humanista'),
                                             ('Integrativo');

INSERT INTO frecuencia (descripcion) VALUES
                                         ('Semanal'),
                                         ('Quincenal'),
                                         ('Mensual');


CREATE TABLE configuracion_tratamiento (
                                           id SERIAL PRIMARY KEY,
                                           objetivo_general BIGINT NOT NULL REFERENCES tipo_objetivo(id) ON DELETE CASCADE,
                                           hc_id BIGINT NOT NULL REFERENCES historia_clinica(id) ON DELETE CASCADE,
                                           frecuencia_id BIGINT NOT NULL REFERENCES frecuencia(id) ON DELETE CASCADE,
                                           sesiones_por_semana SMALLINT CHECK (sesiones_por_semana BETWEEN 1 AND 3),
                                           duracion_estimada INT,
                                           costos_por_session DECIMAL(10,2)
);

CREATE TABLE modalidad (
                           id SERIAL PRIMARY KEY,
                           configuracion_tratamiento BIGINT NOT NULL
                               REFERENCES configuracion_tratamiento(id) ON DELETE CASCADE
);

CREATE TABLE enfoque (
                         id SERIAL PRIMARY KEY,
                         configuracion_tratamiento BIGINT NOT NULL
                             REFERENCES configuracion_tratamiento(id) ON DELETE CASCADE
);
