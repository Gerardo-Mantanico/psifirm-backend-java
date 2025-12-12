package com.pifirm.web.controller;

import com.pifirm.domain.service.NominaCatalogService;
import com.pifirm.persistence.entity.MetodoPagoEntity;
import com.pifirm.persistence.entity.TipoBonoEntity;
import com.pifirm.persistence.entity.TipoDescuentoEntity;
import com.pifirm.persistence.entity.TipoRetencionEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/nomina")
public class NominaCatalogController {

    private final NominaCatalogService nominaCatalogService;

    public NominaCatalogController(NominaCatalogService nominaCatalogService) {
        this.nominaCatalogService = nominaCatalogService;
    }

    @GetMapping("/metodos-pago")
    public List<MetodoPagoEntity> listMetodosPago() {
        return nominaCatalogService.listMetodosPago();
    }

    @GetMapping("/tipos-bono")
    public List<TipoBonoEntity> listTiposBono() {
        return nominaCatalogService.listTiposBono();
    }

    @GetMapping("/tipos-retencion")
    public List<TipoRetencionEntity> listTiposRetencion() {
        return nominaCatalogService.listTiposRetencion();
    }

    @GetMapping("/tipos-descuento")
    public List<TipoDescuentoEntity> listTiposDescuento() {
        return nominaCatalogService.listTiposDescuento();
    }
}
