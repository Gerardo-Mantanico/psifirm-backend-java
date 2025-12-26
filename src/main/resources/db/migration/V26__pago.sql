-- ============================
-- TABLA
-- ============================
CREATE TABLE nomina_pagada (
                               id BIGSERIAL PRIMARY KEY,

                               nomina_id BIGINT NOT NULL
                                   REFERENCES nominas(id)
                                       ON DELETE CASCADE,

                               user_id BIGINT
                                                REFERENCES "user"(id)
                                                    ON DELETE SET NULL,

                               codigo_factura TEXT,

                               data_json JSONB NOT NULL,

                               fecha_pago TIMESTAMP DEFAULT NOW(),

                               pagado BOOLEAN NOT NULL DEFAULT TRUE,

                               created_at TIMESTAMP NOT NULL DEFAULT NOW()
);

-- ============================
-- SECUENCIA
-- ============================
CREATE SEQUENCE IF NOT EXISTS nomina_pagada_codigo_seq
START 1;

-- ============================
-- FUNCIÓN (CORREGIDA)
-- ============================
CREATE OR REPLACE FUNCTION fn_generate_codigo_factura_nomina_pagada()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.codigo_factura IS NULL OR TRIM(NEW.codigo_factura) = '' THEN
        NEW.codigo_factura :=
            'FAC-' ||
            EXTRACT(YEAR FROM CURRENT_DATE)::text || '-' ||
            LPAD(nextval('nomina_pagada_codigo_seq')::text, 6, '0');
END IF;

RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- ============================
-- TRIGGER
-- ============================
DROP TRIGGER IF EXISTS trg_generate_codigo_factura_nomina_pagada
ON nomina_pagada;

CREATE TRIGGER trg_generate_codigo_factura_nomina_pagada
    BEFORE INSERT ON nomina_pagada
    FOR EACH ROW
    EXECUTE FUNCTION fn_generate_codigo_factura_nomina_pagada();
