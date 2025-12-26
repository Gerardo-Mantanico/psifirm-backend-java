package com.pifirm.domain.service;

import com.pifirm.domain.dto.pagoSesion.PagoSesionDto;
import com.pifirm.domain.exception.GeneralException;
import com.pifirm.domain.repository.PagoSesionRepository;
import com.pifirm.persistence.entity.PagoSesionEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PagoSesionService {

    private final PagoSesionRepository pagoSesionRepository;
    private final S3Service s3Service;

    public List<PagoSesionEntity> getAllPagoSesiones(String estado) {
        List<PagoSesionEntity> entidades;

        if (estado == null || estado.trim().isEmpty()) {
            entidades = pagoSesionRepository.findAll();
        } else {
            String e = estado.trim().toLowerCase();
            if (e.equals("pagado") || e.equals("true") || e.equals("1")) {
                entidades = pagoSesionRepository.findAllByPagado(Boolean.TRUE);
            } else if (e.equals("no_pagado") || e.equals("nopagado") || e.equals("no-pagado") || e.equals("false") || e.equals("0")) {
                entidades = pagoSesionRepository.findAllByPagado(Boolean.FALSE);
            } else {
                // valor no reconocido: devolver todos (o ajustar según necesidad)
                entidades = pagoSesionRepository.findAll();
            }
        }
        return entidades;
    }


    public void  updateStatusPagoSesion(PagoSesionDto dto) {
            var pago =  this.pagoSesionRepository.findById(dto.id()).orElseThrow();
            pago.setMonto(dto.monto());
            pago.setFechaPago(LocalDateTime.now());
            pago.setNotas(dto.nota());
            pago.setPagado(true);
            pago.setComprobante(this.s3Service.uploadBase64(dto.comprobante()));
            this.pagoSesionRepository.save(pago);
    }
}
