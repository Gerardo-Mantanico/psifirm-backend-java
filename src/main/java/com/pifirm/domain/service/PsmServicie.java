package com.pifirm.domain.service;

import com.pifirm.domain.dto.psm.PsmReqDto;
import com.pifirm.domain.dto.psm.PsmResDto;
import com.pifirm.persistence.entity.AreaEntity;
import com.pifirm.persistence.entity.EspecialidadEntity;
import com.pifirm.persistence.entity.UserEntity;
import com.pifirm.persistence.mapper.PsmMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PsmServicie {
    private final UserService userService;
    private final ILEmpleadoService ilempleadoService;
    private final HorarioService horarioService;
    private final PsmMapper psmMapper;
    private final UserUtilsService userUtilsService;

    @Transactional
    public  void registerPsm(PsmReqDto psmDto, Long userId  ) {
         this.userService.getById(userId);

        var info = psmMapper.toILEmpleadoEntity(psmDto.ilempleadoReqDto());

        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        info.setUser(userEntity);

        AreaEntity areaEntity = new AreaEntity();
        areaEntity.setId(psmDto.ilempleadoReqDto().areaId());
        info.setArea(areaEntity);

        EspecialidadEntity especialidadEntity = new EspecialidadEntity();
        especialidadEntity.setId(psmDto.ilempleadoReqDto().especialidadId());
        info.setEspecialidad(especialidadEntity);


        this.ilempleadoService.add(info);

        this.horarioService.save(psmMapper.toListHorarioEntity(psmDto.horarioReqDto()),userEntity);

    }

    @Transactional
    public void updatePsm(PsmReqDto psmDto, Long userId) {
        this.userService.getById(userId);

        var info = psmMapper.toILEmpleadoEntity(psmDto.ilempleadoReqDto());

        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        info.setUser(userEntity);

        AreaEntity areaEntity = new AreaEntity();
        areaEntity.setId(psmDto.ilempleadoReqDto().areaId());
        info.setArea(areaEntity);

        EspecialidadEntity especialidadEntity = new EspecialidadEntity();
        especialidadEntity.setId(psmDto.ilempleadoReqDto().especialidadId());
        info.setEspecialidad(especialidadEntity);
    }

    public PsmResDto getPsmByUserId() {
        Long userId = this.userUtilsService.getCurrent().getId();
        var ilempleadoResDto = this.ilempleadoService.getByUserId(userId);
        var horarioReqDto= this.horarioService.getByUserId(userId);



        return new PsmResDto(ilempleadoResDto, horarioReqDto);
    }
}
