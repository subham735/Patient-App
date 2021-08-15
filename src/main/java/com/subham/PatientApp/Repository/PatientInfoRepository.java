package com.subham.PatientApp.Repository;

import com.subham.PatientApp.Entity.PatientInfoEntity;
import com.subham.PatientApp.Enum.PatientStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientInfoRepository extends JpaRepository<PatientInfoEntity,Long> {
    List<PatientInfoEntity> findAllByStatus(PatientStatus status);
    PatientInfoEntity findByNameAndAge(String name,Integer age);

}
