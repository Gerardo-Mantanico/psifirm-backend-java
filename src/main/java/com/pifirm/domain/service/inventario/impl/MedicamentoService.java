package com.pifirm.domain.service.inventario.impl;

import com.pifirm.domain.dto.inventario.MedicamentoDTO;
import com.pifirm.domain.dto.inventario.MedicamentoDetalleDTO;
import com.pifirm.domain.repository.MedicamentoRepository;
import com.pifirm.persistence.entity.inventario.MedicamentoEntidad;
import com.pifirm.persistence.entity.inventario.FormaFarmaceuticaEntidad;
import com.pifirm.persistence.entity.inventario.CategoriaProductoEntidad;
import com.pifirm.domain.repository.FormaFarmaceuticaRepository;
import com.pifirm.domain.repository.CategoriaProductoRepository;
import com.pifirm.domain.repository.UnidadMedidaRepository;
import com.pifirm.persistence.entity.inventario.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class MedicamentoService {

    private final MedicamentoRepository medicamentoRepository;
    private final FormaFarmaceuticaRepository formaRepo;
    private final CategoriaProductoRepository categoriaRepo;
    private final PrincipioActivoService principioActivoService;
    private final UnidadMedidaRepository unidadMedidaRepository;


    public MedicamentoDTO create(MedicamentoDTO dto) {
        MedicamentoEntidad e = toEntity(dto);
        MedicamentoEntidad saved = medicamentoRepository.save(e);

        if (dto.getPrincipioActivoId() != null && dto.getUnidadMedidaId() != null && dto.getConcentracion() != null) {
            MedicamentoPrincipioActivoEntidad mpa = new MedicamentoPrincipioActivoEntidad();
            mpa.setMedicamento(saved);

            PrincipioActivoEntidad pa = principioActivoService.findById(dto.getPrincipioActivoId())
                .orElseThrow(() -> new RuntimeException("Principio Activo no encontrado"));
            mpa.setPrincipioActivo(pa);

            UnidadMedidaEntidad um = unidadMedidaRepository.findById(dto.getUnidadMedidaId())
                .orElseThrow(() -> new RuntimeException("Unidad de Medida no encontrada"));
            mpa.setUnidadMedida(um);

            mpa.setConcentracion(dto.getConcentracion());
            saved.getPrincipios().add(mpa);
            medicamentoRepository.save(saved);
        }

        return toDto(saved);
    }

    public MedicamentoDTO update(Long id, MedicamentoDTO dto) {
        MedicamentoEntidad existing = medicamentoRepository.findById(id).orElseThrow(() -> new RuntimeException("Medicamento no encontrado"));
        existing.setNombreComercial(dto.getNombreComercial());
        existing.setUnidadesPorEmpaque(dto.getUnidadesPorEmpaque());
        existing.setStockMinimo(dto.getStockMinimo());
        existing.setPrecioVenta(dto.getPrecioVenta());
        existing.setActivo(dto.getActivo());
        // Nuevo campo stockTotal
        existing.setStockTotal(dto.getStockTotal());

        if (dto.getFormaFarmaceuticaId() != null) {
            FormaFarmaceuticaEntidad f = formaRepo.findById(dto.getFormaFarmaceuticaId()).orElse(null);
            existing.setFormaFarmaceutica(f);
        }
        if (dto.getCategoriaId() != null) {
            CategoriaProductoEntidad c = categoriaRepo.findById(dto.getCategoriaId()).orElse(null);
            existing.setCategoria(c);
        }

        // Limpiar principios activos existentes y agregar los nuevos
        existing.getPrincipios().clear();
        medicamentoRepository.save(existing); // Guardar para remover las relaciones viejas

        if (dto.getPrincipioActivoId() != null && dto.getUnidadMedidaId() != null && dto.getConcentracion() != null) {
            MedicamentoPrincipioActivoEntidad mpa = new MedicamentoPrincipioActivoEntidad();
            mpa.setMedicamento(existing);

            PrincipioActivoEntidad pa = principioActivoService.findById(dto.getPrincipioActivoId())
                    .orElseThrow(() -> new RuntimeException("Principio Activo no encontrado"));
            mpa.setPrincipioActivo(pa);

            UnidadMedidaEntidad um = unidadMedidaRepository.findById(dto.getUnidadMedidaId())
                    .orElseThrow(() -> new RuntimeException("Unidad de Medida no encontrada"));
            mpa.setUnidadMedida(um);

            mpa.setConcentracion(dto.getConcentracion());
            existing.getPrincipios().add(mpa);
        }

        MedicamentoEntidad saved = medicamentoRepository.save(existing);
        return toDto(saved);
    }

    public void delete(Long id) {
        medicamentoRepository.deleteById(id);
    }

    public MedicamentoDTO findById(Long id) {
        return medicamentoRepository.findById(id).map(this::toDto).orElse(null);
    }

    public List<MedicamentoDTO> findAll() {
        return medicamentoRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public MedicamentoDetalleDTO findByIdDetalle(Long id) {
        return medicamentoRepository.findById(id).map(this::toDtoDetalle).orElse(null);
    }

    public List<MedicamentoDetalleDTO> findAllDetalle() {
        return medicamentoRepository.findAll().stream().map(this::toDtoDetalle).collect(Collectors.toList());
    }

    private MedicamentoDTO toDto(MedicamentoEntidad e) {
        MedicamentoDTO d = new MedicamentoDTO();
        d.setId(e.getId());
        d.setNombreComercial(e.getNombreComercial());
        if (e.getFormaFarmaceutica() != null) d.setFormaFarmaceuticaId(e.getFormaFarmaceutica().getId());
        if (e.getCategoria() != null) d.setCategoriaId(e.getCategoria().getId());
        d.setUnidadesPorEmpaque(e.getUnidadesPorEmpaque());
        d.setStockMinimo(e.getStockMinimo());
        d.setPrecioVenta(e.getPrecioVenta());
        d.setActivo(e.getActivo());
        // Mapear nuevo campo stockTotal
        d.setStockTotal(e.getStockTotal());

        if (e.getPrincipios() != null && !e.getPrincipios().isEmpty()) {
            // Asumiendo que solo hay un principio activo por la estructura del DTO
            e.getPrincipios().stream().findFirst().ifPresent(mpa -> {
                d.setPrincipioActivoId(mpa.getPrincipioActivo().getId());
                d.setConcentracion(mpa.getConcentracion());
                d.setUnidadMedidaId(mpa.getUnidadMedida().getId());
            });
        }

        return d;
    }

    private MedicamentoDetalleDTO toDtoDetalle(MedicamentoEntidad e) {
        MedicamentoDetalleDTO d = new MedicamentoDetalleDTO();
        d.setId(e.getId());
        d.setNombreComercial(e.getNombreComercial());
        if (e.getFormaFarmaceutica() != null) {
            d.setFormaFarmaceutica(new MedicamentoDetalleDTO.FormaFarmaceuticaDetalleDTO(
                    e.getFormaFarmaceutica().getId(),
                    e.getFormaFarmaceutica().getNombre()
            ));
        }
        if (e.getCategoria() != null) {
            d.setCategoria(new MedicamentoDetalleDTO.CategoriaProductoDetalleDTO(
                    e.getCategoria().getId(),
                    e.getCategoria().getNombre()
            ));
        }
        d.setUnidadesPorEmpaque(e.getUnidadesPorEmpaque());
        d.setStockMinimo(e.getStockMinimo());
        d.setPrecioVenta(e.getPrecioVenta());
        d.setActivo(e.getActivo());
        // Mapear nuevo campo stockTotal en detalle
        d.setStockTotal(e.getStockTotal());

        // Agregar principios activos con unidad de medida
        if (e.getPrincipios() != null && !e.getPrincipios().isEmpty()) {
            e.getPrincipios().forEach(principio -> {
                MedicamentoDetalleDTO.PrincipioActivoDetalleDTO principioDTO =
                    new MedicamentoDetalleDTO.PrincipioActivoDetalleDTO();
                principioDTO.setId(principio.getPrincipioActivo().getId());
                principioDTO.setNombre(principio.getPrincipioActivo().getNombre());
                principioDTO.setConcentracion(principio.getConcentracion());

                if (principio.getUnidadMedida() != null) {
                    principioDTO.setUnidadMedida(new MedicamentoDetalleDTO.UnidadMedidaDetalleDTO(
                            principio.getUnidadMedida().getId(),
                            principio.getUnidadMedida().getNombre(),
                            principio.getUnidadMedida().getSimbolo()
                    ));
                }
                d.getPrincipiosActivos().add(principioDTO);
            });
        }

        return d;
    }

    private MedicamentoEntidad toEntity(MedicamentoDTO d) {
        MedicamentoEntidad e = new MedicamentoEntidad();
        e.setNombreComercial(d.getNombreComercial());
        e.setUnidadesPorEmpaque(d.getUnidadesPorEmpaque());
        e.setStockMinimo(d.getStockMinimo());
        e.setPrecioVenta(d.getPrecioVenta());
        e.setActivo(d.getActivo());
        // Mapear nuevo campo stockTotal desde DTO a entidad
        e.setStockTotal(d.getStockTotal());
        if (d.getFormaFarmaceuticaId() != null) {
            FormaFarmaceuticaEntidad f = formaRepo.findById(d.getFormaFarmaceuticaId()).orElse(null);
            e.setFormaFarmaceutica(f);
        }
        if (d.getCategoriaId() != null) {
            CategoriaProductoEntidad c = categoriaRepo.findById(d.getCategoriaId()).orElse(null);
            e.setCategoria(c);
        }
        // La lógica para añadir principios activos se maneja en create/update después de guardar la entidad.
        return e;
    }
}
