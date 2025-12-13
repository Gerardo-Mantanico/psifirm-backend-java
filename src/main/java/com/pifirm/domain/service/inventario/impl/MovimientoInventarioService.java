package com.pifirm.domain.service.inventario.impl;

import com.pifirm.domain.dto.inventario.MovimientoInventarioDTO;
import com.pifirm.domain.repository.MovimientoInventarioRepository;
import com.pifirm.domain.repository.LoteRepository;
import com.pifirm.persistence.entity.inventario.MovimientoInventarioEntidad;
import com.pifirm.persistence.entity.inventario.LoteEntidad;
import com.pifirm.domain.enums.TipoMovimientoEntidad;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MovimientoInventarioService {

    private final MovimientoInventarioRepository movimientoRepository;
    private final LoteRepository loteRepository;

    public MovimientoInventarioService(MovimientoInventarioRepository movimientoRepository, LoteRepository loteRepository) {
        this.movimientoRepository = movimientoRepository;
        this.loteRepository = loteRepository;
    }

    public MovimientoInventarioDTO create(MovimientoInventarioDTO dto) {
        MovimientoInventarioEntidad e = new MovimientoInventarioEntidad();
        if (dto.getLoteId() != null) {
            LoteEntidad lote = loteRepository.findById(dto.getLoteId()).orElseThrow(() -> new RuntimeException("Lote no encontrado"));
            e.setLote(lote);
        }
        e.setTipoMovimiento(dto.getTipoMovimiento() == null ? null : TipoMovimientoEntidad.valueOf(dto.getTipoMovimiento()));
        e.setCantidad(dto.getCantidad());
        e.setFecha(dto.getFecha() == null ? LocalDateTime.now() : dto.getFecha());
        e.setObservacion(dto.getObservacion());
        MovimientoInventarioEntidad saved = movimientoRepository.save(e);
        return toDto(saved);
    }

    public MovimientoInventarioDTO findById(Long id) {
        return movimientoRepository.findById(id).map(this::toDto).orElse(null);
    }

    public List<MovimientoInventarioDTO> findByLoteId(Long loteId) {
        return movimientoRepository.findByLoteId(loteId).stream().map(this::toDto).collect(Collectors.toList());
    }

    private MovimientoInventarioDTO toDto(MovimientoInventarioEntidad e) {
        MovimientoInventarioDTO d = new MovimientoInventarioDTO();
        d.setId(e.getId());
        if (e.getLote() != null) d.setLoteId(e.getLote().getId());
        d.setTipoMovimiento(e.getTipoMovimiento() == null ? null : e.getTipoMovimiento().name());
        d.setCantidad(e.getCantidad());
        d.setFecha(e.getFecha());
        d.setObservacion(e.getObservacion());
        return d;
    }
}
