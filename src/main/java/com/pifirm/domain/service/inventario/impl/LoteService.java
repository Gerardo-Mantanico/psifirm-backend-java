package com.pifirm.domain.service.inventario.impl;

import com.pifirm.domain.dto.inventario.LoteDTO;
import com.pifirm.domain.repository.LoteRepository;
import com.pifirm.domain.repository.MedicamentoRepository;
import com.pifirm.domain.repository.ProveedorRepository;
import com.pifirm.domain.repository.UbicacionRepository;
import com.pifirm.persistence.entity.inventario.LoteEntidad;
import com.pifirm.persistence.entity.inventario.MedicamentoEntidad;
import com.pifirm.persistence.entity.inventario.ProveedorEntidad;
import com.pifirm.persistence.entity.inventario.UbicacionEntidad;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LoteService {

    private final LoteRepository loteRepository;
    private final MedicamentoRepository medicamentoRepository;
    private final ProveedorRepository proveedorRepository;
    private final UbicacionRepository ubicacionRepository;

    public LoteService(LoteRepository loteRepository, MedicamentoRepository medicamentoRepository, ProveedorRepository proveedorRepository, UbicacionRepository ubicacionRepository) {
        this.loteRepository = loteRepository;
        this.medicamentoRepository = medicamentoRepository;
        this.proveedorRepository = proveedorRepository;
        this.ubicacionRepository = ubicacionRepository;
    }

    public LoteDTO create(LoteDTO dto) {
        LoteEntidad e = toEntity(dto);
        LoteEntidad saved = loteRepository.save(e);
        return toDto(saved);
    }

    public LoteDTO update(Long id, LoteDTO dto) {
        LoteEntidad existing = loteRepository.findById(id).orElseThrow(() -> new RuntimeException("Lote no encontrado"));
        existing.setNumeroLote(dto.getNumeroLote());
        existing.setFechaVencimiento(dto.getFechaVencimiento());
        existing.setCantidad(dto.getCantidad());
        existing.setPrecioCompra(dto.getPrecioCompra());
        if (dto.getMedicamentoId() != null) {
            MedicamentoEntidad m = medicamentoRepository.findById(dto.getMedicamentoId()).orElse(null);
            existing.setMedicamento(m);
        }
        if (dto.getProveedorId() != null) {
            ProveedorEntidad p = proveedorRepository.findById(dto.getProveedorId()).orElse(null);
            existing.setProveedor(p);
        }
        if (dto.getUbicacionId() != null) {
            UbicacionEntidad u = ubicacionRepository.findById(dto.getUbicacionId()).orElse(null);
            existing.setUbicacion(u);
        }
        LoteEntidad saved = loteRepository.save(existing);
        return toDto(saved);
    }

    public void delete(Long id) {
        loteRepository.deleteById(id);
    }

    public LoteDTO findById(Long id) {
        return loteRepository.findById(id).map(this::toDto).orElse(null);
    }

    public List<LoteDTO> findAll() {
        return loteRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    private LoteDTO toDto(LoteEntidad e) {
        LoteDTO d = new LoteDTO();
        d.setId(e.getId());
        if (e.getMedicamento() != null) d.setMedicamentoId(e.getMedicamento().getId());
        if (e.getProveedor() != null) d.setProveedorId(e.getProveedor().getId());
        if (e.getUbicacion() != null) d.setUbicacionId(e.getUbicacion().getId());
        d.setNumeroLote(e.getNumeroLote());
        d.setFechaVencimiento(e.getFechaVencimiento());
        d.setCantidad(e.getCantidad());
        d.setPrecioCompra(e.getPrecioCompra());
        return d;
    }

    private LoteEntidad toEntity(LoteDTO d) {
        LoteEntidad e = new LoteEntidad();
        e.setNumeroLote(d.getNumeroLote());
        e.setFechaVencimiento(d.getFechaVencimiento());
        e.setCantidad(d.getCantidad());
        e.setPrecioCompra(d.getPrecioCompra());
        if (d.getMedicamentoId() != null) {
            MedicamentoEntidad m = medicamentoRepository.findById(d.getMedicamentoId()).orElse(null);
            e.setMedicamento(m);
        }
        if (d.getProveedorId() != null) {
            ProveedorEntidad p = proveedorRepository.findById(d.getProveedorId()).orElse(null);
            e.setProveedor(p);
        }
        if (d.getUbicacionId() != null) {
            UbicacionEntidad u = ubicacionRepository.findById(d.getUbicacionId()).orElse(null);
            e.setUbicacion(u);
        }
        return e;
    }
}
