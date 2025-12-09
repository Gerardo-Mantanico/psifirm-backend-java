package com.pifirm.web.controller;

import com.pifirm.domain.dto.psm.PsmReqDto;
import com.pifirm.domain.dto.psm.PsmResDto;
import com.pifirm.domain.service.PsmServicie;
import jakarta.validation.Valid;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/psm")
public class PsmController {
    @Autowired
    private PsmServicie psmServicie;

    @PostMapping("/{id}")
    public ResponseEntity<?> register(@Valid @RequestBody PsmReqDto psmDto, @PathVariable Long id) {
        psmServicie.registerPsm(psmDto, id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<PsmResDto> getMyPsm() {
        return ResponseEntity.ok(this.psmServicie.getPsmByUserId());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePsm(@Valid @RequestBody PsmReqDto psmDto, @PathVariable Long id) {
        psmServicie.updatePsm(psmDto, id);
        return ResponseEntity.ok().build();
    }
}
