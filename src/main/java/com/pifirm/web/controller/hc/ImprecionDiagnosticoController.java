package com.pifirm.web.controller.hc;

import com.pifirm.domain.dto.hc.data.HistorialPersonalDto;
import com.pifirm.domain.service.hc.ImprecionDiagnosticoService;
import com.pifirm.persistence.entity.HistorialPersonalEntity;
import com.pifirm.persistence.entity.diagnostico.DiagnosticoCie11Entity;
import com.pifirm.persistence.entity.diagnostico.DiagnosticoDsm5Entity;
import com.pifirm.persistence.entity.diagnostico.ImpresionDiagnosticaEntity;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/imprecion-diagnostico")

public class ImprecionDiagnosticoController {
    @Autowired
    private ImprecionDiagnosticoService imprecionDiagnosticoService;

    @PostMapping
    public ResponseEntity<?> register (@Valid @RequestBody ImpresionDiagnosticaEntity body){
        this.imprecionDiagnosticoService.save(body);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImpresionDiagnosticaEntity> getInfoPaciente(@PathVariable Long id) {
        return ResponseEntity.ok(this.imprecionDiagnosticoService.findByIdHc(id));
    }


    @GetMapping("/c11")
    public ResponseEntity<List<DiagnosticoCie11Entity>> getC11() {
        return ResponseEntity.ok(this.imprecionDiagnosticoService.getC11());
    }

    @GetMapping("/dm5")
    public ResponseEntity<List<DiagnosticoDsm5Entity>> getdm5() {
        return ResponseEntity.ok(this.imprecionDiagnosticoService.getDsm5());
    }
}
