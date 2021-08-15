package com.subham.PatientApp.Service;

import com.subham.PatientApp.Cosntants.RedisConstants;
import com.subham.PatientApp.Dto.PatientInfoDto;
import com.subham.PatientApp.Entity.PatientInfoEntity;
import com.subham.PatientApp.Enum.PatientStatus;
import com.subham.PatientApp.Repository.PatientInfoRepository;
import com.subham.PatientApp.Utilities.StructMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
@Slf4j
@Service
public class PatientService {

    @Autowired
    private PatientInfoRepository patientInfoRepository;

    @Cacheable(value = RedisConstants.PATIENT_INFO_CACHE, key= "'status' + #patientStatus.name()",unless = "#result ==null")
    public List<PatientInfoDto> getPatientsInfoByStatus(PatientStatus patientStatus,String logToken){
        List<PatientInfoEntity> entities = patientInfoRepository.findAllByStatus(patientStatus);
        if(CollectionUtils.isEmpty(entities)){
            log.info("[{}] no entry found for status:{}",logToken,patientStatus);
            return null;
        }

        List<PatientInfoDto> dtos= StructMapper.ENTITYDTO.map(entities);
        return dtos;
    }

    public PatientInfoDto getPatientByNameAndAge(String name,Integer age, String logToken){
        PatientInfoEntity entity = patientInfoRepository.findByNameAndAge(name,age);
        if(entity == null){
            log.info("[{}] no entry found for name:{} and age:{}", logToken,name,age);
            return null;
        }
        PatientInfoDto dto = StructMapper.ENTITYDTO.map(entity);
        return dto;
    }

    @CacheEvict(value = RedisConstants.PATIENT_INFO_CACHE, allEntries = true)
    public String createOrUpdatePatientInfo(PatientInfoDto dto,String logToken){
        try {
            PatientInfoEntity entity = patientInfoRepository.findByNameAndAge(dto.getName(), dto.getAge());
            if (entity == null) {
                entity = new PatientInfoEntity();
                entity.setAdmissionDate(new Date());
            }
            entity = StructMapper.ENTITYDTO.map(dto);
            if (dto.getPatientStatus() == PatientStatus.DISCHARGED)
                entity.setReleaseDate(new Date());
            patientInfoRepository.save(entity);

            return "updated successfully";
        }catch(Exception e){
            log.error("[{}] update failed due to :{}",logToken,e.getStackTrace());
            return null;
        }
    }

    @CacheEvict(value = RedisConstants.PATIENT_INFO_CACHE, allEntries = true)
    public String deletePatientInfo(String name, Integer age, String logToken){
        PatientInfoEntity entity = patientInfoRepository.findByNameAndAge(name,age);
        if(entity==null){
            log.info("[{}] no data available to delete",logToken);
            return null;
        }
        patientInfoRepository.delete(entity);
        return "data deleted successfully";
    }


}
