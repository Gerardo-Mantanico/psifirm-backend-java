package com.pifirm.domain.service;

import com.pifirm.domain.dto.psm.PsmReqDto;
import com.pifirm.persistence.entity.AreaEntity;
import com.pifirm.persistence.entity.EspecialidadEntity;
import com.pifirm.persistence.entity.UserEntity;
import com.pifirm.persistence.mapper.PsmMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PsmServicie {
    private final UserService userService;
    private final ILEmpleadoService ilempleadoService;
    private final HorarioService horarioService;
    private final PsmMapper psmMapper;

    public  void registerPsm(PsmReqDto psmDto) {
        var user = this.userService.add(psmDto.user());

        var info = psmMapper.toILEmpleadoEntity(psmDto.ilempleadoReqDto());

        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.id());
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
}
