CREATE TABLE paciente (
                          id SERIAL PRIMARY KEY,
                          user_id BIGINT NOT NULL REFERENCES public.user(id) ON DELETE CASCADE,

                          fecha_nacimiento DATE NOT NULL,

                          genero VARCHAR(20) NOT NULL CHECK (genero IN
                                                             ('FEMENINO','MASCULINO','NO_BINARIO','PREFIERO_NO_ESPECIFICAR')),

                          estado_civil VARCHAR(30) NOT NULL CHECK (estado_civil IN
                                                                   ('SOLTERO','CASADO','DIVORCIADO','VIUDO','UNION_LIBRE')),

                          ocupacion VARCHAR(100),

                          nivel_educativo VARCHAR(50) NOT NULL CHECK (nivel_educativo IN
                                                                      ('PRIMARIA','SECUNDARIA','TECNICO','UNIVERSITARIO','POSGRADO')),

                          direccion VARCHAR(200),

                          persona_contacto VARCHAR(100) NOT NULL,

                          parentesco VARCHAR(30) NOT NULL CHECK (parentesco IN
                                                                 ('PADRE','MADRE','HIJO','CONYUGE','HERMANO','OTRO')),

                          telefono_contacto VARCHAR(15) NOT NULL
);
