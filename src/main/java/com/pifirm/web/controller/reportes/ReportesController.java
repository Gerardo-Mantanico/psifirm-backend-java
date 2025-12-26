package com.pifirm.web.controller.reportes;

import com.pifirm.domain.dto.reporte.AreaServicioSesionesDto;
import com.pifirm.domain.service.reporte.ReporteService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reporte")
@AllArgsConstructor
public class ReportesController {

    private  final  ReporteService reporteService;

    @GetMapping("/sesiones-area-servicio")
    public List<AreaServicioSesionesDto> listarSesionesPorAreaYServicio() {
        return reporteService.obtenerSesionesPorAreaYServicio();
    }
}
