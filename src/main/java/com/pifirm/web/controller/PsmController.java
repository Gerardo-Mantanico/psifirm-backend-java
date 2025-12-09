package com.pifirm.web.controller;

import com.pifirm.domain.dto.psm.PsmReqDto;
import com.pifirm.domain.service.PsmServicie;
import jakarta.validation.Valid;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/psm")
public class PsmController {
    @Autowired
    private PsmServicie psmServicie;

    @PostMapping
    public ResponseEntity<?> register(@Valid @RequestBody PsmReqDto psmDto) {
        psmServicie.registerPsm(psmDto);
        return ResponseEntity.ok().build();
    }
}
