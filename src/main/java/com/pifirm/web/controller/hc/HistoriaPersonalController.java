package com.pifirm.web.controller.hc;

import com.pifirm.domain.dto.hc.data.HistorialPersonalDto;
import com.pifirm.domain.service.hc.HistoriaPersonalService;
import com.pifirm.persistence.entity.HistorialPersonalEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hc/historia-personal")
public class HistoriaPersonalController {

    @Autowired
    private HistoriaPersonalService historiaPersonalService;


    @PostMapping
    public ResponseEntity<?> register (@Valid @RequestBody HistorialPersonalDto body){
        this.historiaPersonalService.save(body);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistorialPersonalEntity> getInfoPaciente(@PathVariable Long id) {
        return ResponseEntity.ok(this.historiaPersonalService.findByIdHc(id));
    }


}
