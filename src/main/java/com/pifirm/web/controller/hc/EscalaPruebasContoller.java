package com.pifirm.web.controller.hc;

import com.pifirm.domain.service.hc.EscalaPruebasService;
import com.pifirm.persistence.entity.diagnostico.PruebasAplicadasEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/escala-pruebas")
public class EscalaPruebasContoller {

    @Autowired
    private EscalaPruebasService escalaPruebasService;
    @PostMapping(
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    @Operation(summary = "Guardar escala con archivo")
    public ResponseEntity<?> guardarEscalaPruebas(
            @RequestPart("body")
            @Parameter(
                    description = "Datos en JSON",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PruebasAplicadasEntity.class)
                    )
            )
            PruebasAplicadasEntity body,

            @RequestPart("file")
            MultipartFile file
    ) {
        return ResponseEntity.ok(
                escalaPruebasService.guardarPruebaAplicada(body, file)
        );
    }


    @GetMapping("/{id}")
    public ResponseEntity<List<PruebasAplicadasEntity>> getList(@PathVariable Long id) {
        return  ResponseEntity.ok(this.escalaPruebasService.getPruebaAplicadaById(id));
    }

 }
