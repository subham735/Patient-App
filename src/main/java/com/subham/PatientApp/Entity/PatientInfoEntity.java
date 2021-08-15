package com.subham.PatientApp.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.subham.PatientApp.Enum.PatientStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientInfoEntity extends AbstractEntity {

    @JsonProperty("name")
    private String name;

    @JsonProperty("age")
    private Integer age;

    @JsonProperty("status")
    private PatientStatus status;

    @JsonProperty("admission_date")
    private Date admissionDate;

    @JsonProperty("release_date")
    private Date releaseDate;

}
