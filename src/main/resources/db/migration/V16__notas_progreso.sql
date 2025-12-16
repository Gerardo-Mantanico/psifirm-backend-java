
CREATE  TABLE session(
    id SERIAL PRIMARY KEY,
    hc_id BIGINT NOT NULL REFERENCES historia_clinica(id) ON DELETE CASCADE,
    fecha TIMESTAMP    DEFAULT NOW(),
    numero_sesiones INT NOT NULL,
    asistencia boolean       NOT NULL,
    justificacion_inasistencia TEXT ,
    temas_abordados TEXT          NOT NULL,
    intervenciones_realizadas TEXT          NOT NULL,
    repuesta_paciente TEXT          NOT NULL,
    tareas_asignadas TEXT          NOT NULL,
    Observaciones TEXT          NOT NULL,
    proxima_cita TIMESTAMP NOT NULL,
    firma_psicologo TEXT          NOT NULL
);


CREATE TABLE tipo_evaluacion(
    id SERIAL PRIMARY KEY ,
    description VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE evaluaciones_periodicas(
    id SERIAL PRIMARY KEY,
    hc_id BIGINT NOT NULL REFERENCES historia_clinica(id) ON DELETE CASCADE,
    fecha_evalucacion DATE default  now(),
    tipo_evaluacion BIGINT NOT NULL REFERENCES  tipo_evaluacion(id) ON DELETE CASCADE,
    progreso_observado TEXT          NOT NULL,
    objetivo_alcanzado TEXT       NOT NULL,
    objetivos_pedientes TEXT          NOT NULL,
    recomendaciones TEXT ,
    escala_progreso INT NOT NULL,
    modificacion_plan_tratamiento TEXT          NOT NULL,
    reevaluacion_diagnostico TEXT          NOT NULL
);

CREATE TABLE    notas_especiales(
    hc_id BIGINT NOT NULL REFERENCES historia_clinica(id) ON DELETE CASCADE,
    id SERIAL PRIMARY KEY,
    situaciones_criticas TEXT,
    contacto_con_otro_profecionales TEXT,
    autorizaciones TEXT,
    confidencialidad_limites TEXT

);


CREATE TABLE motivo_alta(
    id SERIAL PRIMARY KEY ,
    description VARCHAR(255) UNIQUE
);

CREATE TABLE alta_terapeutica
(
    id SERIAL PRIMARY KEY,
    hc_id BIGINT NOT NULL REFERENCES historia_clinica(id) ON DELETE CASCADE,
    fecha_alta          TIMESTAMP NOT NULL,
    motivo_alta_id BIGINT NOT NULL  REFERENCES  motivo_alta(id) on DELETE  CASCADE,
    estado_alta TEXT NOT NULL,
    recomendaciones_posteriores TEXT NOT NULL,
    seguimiento_programada BOOLEAN,
    fecha_seguimiento DATE NOT NULL ,
    firma_paciente TEXT NOT NULL,
    firma_psicologo TEXT NOT NULL
);


CREATE TABLE hojas_adicionales(
    hc_id BIGINT NOT NULL REFERENCES historia_clinica(id) ON DELETE CASCADE,
    id SERIAL PRIMARY KEY ,
    consentimiento_firmado TEXT NOT NULL,
    resultado_completo_con_otro_profecionales TEXT NOT NULL,
    registro_conductuales TEXT NOT NULL
);