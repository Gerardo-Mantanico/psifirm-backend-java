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

    @Transactional
    public NominaDTO search(String data) {
        var nomina =  this.nominaRepository.findByUser_Email(data).orElseThrow(()-> new GeneralException("error","no existe una nomina asociada con este correo"));
        return this.nominaMapper.toDto(nomina);
    }

    @Transactional
    public NominaDTO create(NominaSimpleDTO dto) {
        userService.getById(dto.userId());
        this.nominaRepository.findByUser_Id(dto.userId()).ifPresent(n -> {
            throw new GeneralException("error", "El usuario ya tiene una nómina asignada.");
        });

        var nominaEntity = nominaMapper.toEntity(dto);
        var usuer = new UserEntity();
        usuer.setId(dto.userId());
        nominaEntity.setUser(usuer);
        NominaEntity saved = nominaRepository.save(nominaEntity);
        return nominaMapper.toDto(saved);
    }

  @Transactional
  public NominaDTO update(Long id, NominaSimpleDTO payload) {
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

      NominaEntity saved = nominaRepository.save(existing);
      return nominaMapper.toDto(saved);
  }

    @Transactional
    public void delete(Long id) {
        this.nominaRepository.findById(id).orElseThrow(() -> new GeneralException("error", "Nómina no encontrada."));
        nominaRepository.deleteById(id);
    }



    // Sub-CRUDs usando DTO de detalle (tipoId, monto)
    @Transactional
    public NominaDetalleDTO addBonoFromDto( NominaDetalleReqDTO bonoDto) {
        this.nominaBonoRepository.findByTipoBono_Id(bonoDto.tipoId()).ifPresent(b -> {
            throw new GeneralException("error", "El bono de este tipo ya ha sido agregado a la nómina.");
        });
        NominaBonoEntity nominaBonoEntity = new NominaBonoEntity();
        nominaBonoEntity.setMonto(bonoDto.monto());
        nominaBonoEntity.setNomina(getById(bonoDto.nominaId()));

        TipoBonoEntity tipoBono = new TipoBonoEntity();
        tipoBono.setId(bonoDto.tipoId());

        nominaBonoEntity.setTipoBono(tipoBono);
        return  nominaMapper.toDetalleDto(nominaBonoRepository.save(nominaBonoEntity));
    }

    @Transactional
    public NominaDetalleDTO addRetencionFromDto( NominaDetalleReqDTO retDto) {
        this.nominaRetencionRepository.findByTipoRetencion_Id(retDto.tipoId()).ifPresent(b -> {
            throw new GeneralException("error", "El bono de este tipo ya ha sido agregado a la nómina.");
        });

        NominaRetencionEntity nominaRetencionEntity = new NominaRetencionEntity();
        nominaRetencionEntity.setMonto(retDto.monto());
        nominaRetencionEntity.setNomina(getById(retDto.nominaId()));

        TipoRetencionEntity tipoRetencion = new TipoRetencionEntity();
        tipoRetencion.setId(retDto.tipoId());

        nominaRetencionEntity.setTipoRetencion(tipoRetencion);

        return nominaMapper.toDetalleDto( nominaRetencionRepository.save(nominaRetencionEntity));
    }

    @Transactional
    public NominaDetalleDTO addDescuentoFromDto( NominaDetalleReqDTO desDto) {
        this.nominaDescuentoRepository.findByTipoDescuento_Id(desDto.tipoId()).ifPresent(b -> {
            throw new GeneralException("error", "El bono de este tipo ya ha sido agregado a la nómina.");
        });

        NominaDescuentoEntity nominaDescuentoEntity = new NominaDescuentoEntity();
        nominaDescuentoEntity.setMonto(desDto.monto());
        nominaDescuentoEntity.setNomina(getById(desDto.nominaId()));

        TipoDescuentoEntity tipoDescuento = new TipoDescuentoEntity();
        tipoDescuento.setId(desDto.tipoId());

        nominaDescuentoEntity.setTipoDescuento(tipoDescuento);
        return nominaMapper.toDetalleDto( nominaDescuentoRepository.save(nominaDescuentoEntity));
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

    public NominaEntity getById(Long id) {
        return nominaRepository.findById(id)
                .orElseThrow(() -> new GeneralException("error", "Nómina no encontrada."));
    }
}
