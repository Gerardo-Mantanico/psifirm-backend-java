
CREATE TABLE nomina_bonos (
                              id SERIAL PRIMARY KEY,
                              nomina_id INT REFERENCES nominas(id) ON DELETE CASCADE,
                              tipo VARCHAR(100),   -- incentivo, productividad, etc.
                              monto NUMERIC(10,2) NOT NULL
);

CREATE TABLE nominas (
                         id SERIAL PRIMARY KEY,
                         empleado_id INT REFERENCES empleados(id) ON DELETE CASCADE,
                         periodo DATE NOT NULL,
                         salario_base NUMERIC(10,2) NOT NULL,
                         sesiones_trabajadas INT DEFAULT 0,
                         salario_calculado NUMERIC(10,2) NOT NULL,
                         metodo_pago VARCHAR(50),  -- transferencia, efectivo, depósito, etc.
                         fecha_pago DATE DEFAULT CURRENT_DATE
);

CREATE TABLE nomina_retenciones (
                                    id SERIAL PRIMARY KEY,
                                    nomina_id INT REFERENCES nominas(id) ON DELETE CASCADE,
                                    tipo VARCHAR(100),      -- IGSS, ISR, otro
                                    monto NUMERIC(10,2) NOT NULL
);


CREATE TABLE nomina_descuentos (
                                   id SERIAL PRIMARY KEY,
                                   nomina_id INT REFERENCES nominas(id) ON DELETE CASCADE,
                                   tipo VARCHAR(100),       -- ausencias, adelantos, penalizaciones
                                   monto NUMERIC(10,2) NOT NULL
);


CREATE TABLE historial_pagos (
                                 id SERIAL PRIMARY KEY,
                                 nomina_id INT REFERENCES nominas(id) ON DELETE CASCADE,
                                 empleado_id INT REFERENCES empleados(id) ON DELETE CASCADE,
                                 periodo DATE,
                                 monto_total NUMERIC(10,2),
                                 fecha_pago DATE DEFAULT CURRENT_DATE
);


CREATE TABLE comprobantes (
                              id SERIAL PRIMARY KEY,
                              nomina_id INT REFERENCES nominas(id) ON DELETE CASCADE,
                              folio VARCHAR(50) UNIQUE,
                              detalle TEXT,
                              firma_digital VARCHAR(255),
                              fecha_generacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
