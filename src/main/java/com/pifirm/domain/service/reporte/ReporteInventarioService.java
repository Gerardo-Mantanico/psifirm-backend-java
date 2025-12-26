package com.pifirm.domain.service.reporte;

import com.pifirm.domain.dto.reporte.MedicamentoNearMinProjection;
import com.pifirm.domain.repository.reporte.InventarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;


    @Service
    public class ReporteInventarioService {

        private final InventarioRepository repo;

        public ReporteInventarioService(InventarioRepository repo) {
            this.repo = repo;
        }

        public List<MedicamentoNearMinProjection> medicamentosProximosAlMinimo(int umbral) {
            return repo.findMedicamentosProximosAlMinimo(umbral);
        }
    }

