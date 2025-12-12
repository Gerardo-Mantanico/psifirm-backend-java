package com.pifirm.domain.service;

import com.pifirm.domain.dto.nomina.NominaDTO;
import com.pifirm.domain.dto.nomina.NominaDetalleDTO;
import com.pifirm.domain.dto.nomina.NominaDetalleReqDTO;
import com.pifirm.domain.dto.nomina.NominaSimpleDTO;
import com.pifirm.domain.exception.GeneralException;
import com.pifirm.domain.repository.*;
import com.pifirm.persistence.entity.*;
import com.pifirm.persistence.mapper.NominaMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class NominaService {

    private final NominaRepository nominaRepository;
    private final NominaBonoRepository nominaBonoRepository;
    private final NominaRetencionRepository nominaRetencionRepository;
    private final NominaDescuentoRepository nominaDescuentoRepository;
    private final UserService userService;
    private final MetodoPagoRepository metodoPagoRepository;
    private final TipoBonoRepository tipoBonoRepository;
    private final TipoRetencionRepository tipoRetencionRepository;
    private final TipoDescuentoRepository tipoDescuentoRepository;
    private final NominaMapper nominaMapper;
    private final UserService userRepository;

    @Transactional
    public List<NominaDTO> list() {
        return this.nominaMapper.toDtos(nominaRepository.findAll());
    }

    @Transactional
    public NominaEntity create(NominaSimpleDTO dto) {
        userService.getById(dto.userId());
        this.nominaRepository.findByUser_Id(dto.userId()).ifPresent(n -> {
            throw new GeneralException("error", "El usuario ya tiene una nómina asignada.");
        });

        var nominaEntity = nominaMapper.toEntity(dto);
        var usuer = new UserEntity();
        usuer.setId(dto.userId());
        nominaEntity.setUser(usuer);
        return nominaRepository.save(nominaEntity);
    }

  @Transactional
  public NominaEntity update(Long id, NominaSimpleDTO payload) {
      NominaEntity existing = nominaRepository.findById(id)
          .orElseThrow(() -> new GeneralException("error", "Nómina no encontrada."));

      // Actualizar campos básicos
      existing.setPeriodo(payload.periodo());
      existing.setSalarioBase(payload.salarioBase());
      existing.setDetallePago(payload.detallePago());
      existing.setFechaCierre(payload.fechaCierre());

      // Actualizar usuario si se proporciona
      if (payload.userId() != null) {
          UserEntity user = new UserEntity();
          user.setId(payload.userId());
          existing.setUser(user);
      }

      // Actualizar método de pago si se proporciona
      if (payload.metodoPagoId() != null) {
          MetodoPagoEntity metodo = new MetodoPagoEntity();
          metodo.setId(payload.metodoPagoId());
          existing.setMetodoPago(metodo);
      } else {
          existing.setMetodoPago(null);
      }

      return nominaRepository.save(existing);
  }

    @Transactional
    public void delete(Long id) {
        this.nominaRepository.findById(id).orElseThrow(() -> new GeneralException("error", "Nómina no encontrada."));
        nominaRepository.deleteById(id);
    }



    // Sub-CRUDs usando DTO de detalle (tipoId, monto)
    @Transactional
    public NominaDetalleDTO addBonoFromDto(Long nominaId, NominaDetalleReqDTO bonoDto) {
        this.nominaBonoRepository.findByTipoBono_Id(bonoDto.tipoId()).ifPresent(b -> {
            throw new GeneralException("error", "El bono de este tipo ya ha sido agregado a la nómina.");
        });
        return  nominaMapper.toDetalleDto(nominaBonoRepository.save(this.nominaMapper.toBonoEntity(bonoDto)));
    }

    @Transactional
    public NominaDetalleDTO addRetencionFromDto(Long nominaId, NominaDetalleReqDTO retDto) {
        this.nominaRetencionRepository.findByTipoRetencion_Id(retDto.tipoId()).ifPresent(b -> {
            throw new GeneralException("error", "El bono de este tipo ya ha sido agregado a la nómina.");
        });
        return nominaMapper.toDetalleDto( nominaRetencionRepository.save(this.nominaMapper.toRetencionEntity(retDto)));
    }

    @Transactional
    public NominaDetalleDTO addDescuentoFromDto(Long nominaId, NominaDetalleReqDTO desDto) {
        this.nominaDescuentoRepository.findByTipoDescuento_Id(desDto.tipoId()).ifPresent(b -> {
            throw new GeneralException("error", "El bono de este tipo ya ha sido agregado a la nómina.");
        });
        return nominaMapper.toDetalleDto( nominaDescuentoRepository.save(this.nominaMapper.toDescuentoEntity(desDto)));
    }

    @Transactional
    public void deleteBono(Long id) {
        nominaBonoRepository.deleteById(id);
    }

    @Transactional
    public void deleteRetencion(Long id) {
        nominaRetencionRepository.deleteById(id);
    }

    @Transactional
    public void deleteDescuento(Long id) {
        nominaDescuentoRepository.deleteById(id);
    }

}
