package com.subham.PatientApp.Controller;

import com.subham.PatientApp.Dto.PatientInfoDto;
import com.subham.PatientApp.Enum.PatientStatus;
import com.subham.PatientApp.Service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class PatientInfoController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/status")
    public ResponseEntity<?>getPatientsByStatus(@RequestParam("status")PatientStatus patientStatus){
        String logToken = UUID.randomUUID().toString();
        try{
            return new ResponseEntity<>(patientService.getPatientsInfoByStatus(patientStatus,logToken), HttpStatus.OK);
        }catch (Exception e){
            log.error("[{}] unable get patient data by status due to :{}",logToken,e.getStackTrace());
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/patient")
    public ResponseEntity<?>getPatientByNameAndAge(@RequestParam("name") String name,
                                                   @RequestParam("age") Integer age){
        String logToken = UUID.randomUUID().toString();
        try {
            return new ResponseEntity<>(patientService.getPatientByNameAndAge(name,age,logToken),HttpStatus.OK);
        }catch (Exception e){
            log.error("[{}] unable to get patient info with name:{} and age:{} due to {}"
                    ,logToken,name,age,e.getStackTrace());
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    @PutMapping("/patient")
    public ResponseEntity<?> createOrUpdatePatientInfo(@RequestBody PatientInfoDto dto){
        String logToken = UUID.randomUUID().toString();
        try{
            return new ResponseEntity<>(patientService.createOrUpdatePatientInfo(dto,logToken),HttpStatus.OK);
        }catch (Exception e ){
            log.error("[{}] unable to update patient info due to{}",logToken,e.getStackTrace());
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/patient")
    public ResponseEntity<?> deletePatientInfo(@RequestParam("name") String name,
                                               @RequestParam("age") Integer age){
        String logToken = UUID.randomUUID().toString();
        try{
            return new ResponseEntity<>(patientService.deletePatientInfo(name,age,logToken),HttpStatus.OK);
        }catch (Exception e){
            log.error("[{}] unable to delete patient info due to:{}",logToken,e.getStackTrace());
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
