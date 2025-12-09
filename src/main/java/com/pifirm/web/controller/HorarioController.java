package com.pifirm.web.controller;

import com.pifirm.domain.dto.horario.HorarioReqDto;
import com.pifirm.domain.dto.horario.HorarioResDto;
import com.pifirm.domain.service.HorarioService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("horario")
public class HorarioController {
    @Autowired
    private HorarioService horarioService;

    @PutMapping("/{userId}")
     public ResponseEntity<HorarioResDto> updateHorario(){
        return null;
    }
}
