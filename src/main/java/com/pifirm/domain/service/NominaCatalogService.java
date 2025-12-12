package com.pifirm.domain.service;

import com.pifirm.persistence.entity.MetodoPagoEntity;
import com.pifirm.persistence.entity.TipoBonoEntity;
import com.pifirm.persistence.entity.TipoDescuentoEntity;
import com.pifirm.persistence.entity.TipoRetencionEntity;
import com.pifirm.domain.repository.MetodoPagoRepository;
import com.pifirm.domain.repository.TipoBonoRepository;
import com.pifirm.domain.repository.TipoDescuentoRepository;
import com.pifirm.domain.repository.TipoRetencionRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NominaCatalogService {

    private final MetodoPagoRepository metodoPagoRepository;
    private final TipoBonoRepository tipoBonoRepository;
    private final TipoRetencionRepository tipoRetencionRepository;
    private final TipoDescuentoRepository tipoDescuentoRepository;

    @Transactional
    public List<MetodoPagoEntity> listMetodosPago() {
        return metodoPagoRepository.findAll();
    }

    @Transactional
    public List<TipoBonoEntity> listTiposBono() {
        return tipoBonoRepository.findAll();
    }

    @Transactional
    public List<TipoRetencionEntity> listTiposRetencion() {
        return tipoRetencionRepository.findAll();
    }

    @Transactional
    public List<TipoDescuentoEntity> listTiposDescuento() {
        return tipoDescuentoRepository.findAll();
    }
}
