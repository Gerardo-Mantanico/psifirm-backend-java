package com.pifirm.web.controller.reportes;

import com.pifirm.domain.dto.reporte.MedicamentoNearMinProjection;
import com.pifirm.domain.service.reporte.ReporteInventarioService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reporte/reportes/inventario")
public class ReporteInventarioController {
    private final ReporteInventarioService service;

    public ReporteInventarioController(ReporteInventarioService service) {
        this.service = service;
    }

    @GetMapping("/proximo-minimo")
    public List<MedicamentoNearMinProjection> proximosAlMinimo(@RequestParam(defaultValue = "10") int umbral) {
        return service.medicamentosProximosAlMinimo(umbral);
    }
}