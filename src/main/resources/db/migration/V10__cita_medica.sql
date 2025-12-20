CREATE  TABLE cita_medica(
    id SERIAL PRIMARY KEY,
    hc_id BIGINT  REFERENCES historia_clinica(id) ON DELETE CASCADE,
    paciente_id BIGINT NOT NULL REFERENCES public.user(id) ON DELETE CASCADE,
    medico_id BIGINT  REFERENCES public.user(id) ON DELETE CASCADE,
    fecha_cita TIMESTAMP NOT NULL,
    estado_cita VARCHAR(20)
        CHECK (
            estado_cita IN (
                            'PROGRAMADA',
                            'CONFIRMADA',
                            'REPROGRAMADA',
                            'EN_PROCESO',
                            'COMPLETADA',
                            'NO_ASISTIO',
                            'CANCELADA'
                )
            ) NOT NULL DEFAULT 'PROGRAMADA',
    servicio_medico_id BIGINT NOT NULL REFERENCES servicios(id) ON DELETE CASCADE,
    nota TEXT,
    created_at TIMESTAMP NOT NULL DEFAULT NOW()
);