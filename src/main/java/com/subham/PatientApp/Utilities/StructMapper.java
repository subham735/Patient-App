package com.subham.PatientApp.Utilities;

import com.subham.PatientApp.Dto.PatientInfoDto;
import com.subham.PatientApp.Entity.PatientInfoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface StructMapper {

    //Entity Dto Mapper
    StructMapper ENTITYDTO = Mappers.getMapper(StructMapper.class);

    PatientInfoEntity map(PatientInfoDto dto);

    PatientInfoDto map(PatientInfoEntity entity);

    List<PatientInfoDto> map(List<PatientInfoEntity> list);

}
