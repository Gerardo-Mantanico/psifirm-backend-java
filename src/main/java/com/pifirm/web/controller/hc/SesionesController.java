package com.pifirm.web.controller.hc;

import com.pifirm.domain.service.hc.SesionesService;
import com.pifirm.persistence.entity.notasProgreso.SessionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sesiones")
public class SesionesController {
    @Autowired
    private SesionesService sesionesService;

    @GetMapping("/{id}")
    public ResponseEntity<List<SessionEntity>> getAllSessions(@PathVariable  Long id) {
        return ResponseEntity.ok(this.sesionesService.getAllSessions(id));

    }

    @PostMapping
    public  ResponseEntity<?> registerSession(@RequestBody SessionEntity sessionData) {
        this.sesionesService.registerSession(sessionData);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSession(@PathVariable Long id) {
        this.sesionesService.deleteSession(id);
        return ResponseEntity.ok().build();
    }


}
