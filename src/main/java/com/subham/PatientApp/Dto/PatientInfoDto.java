package com.subham.PatientApp.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.subham.PatientApp.Enum.PatientStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PatientInfoDto {

    @NonNull
    @JsonProperty("name")
    private String name;

    @NonNull
    @JsonProperty("age")
    private Integer age;

    @NonNull
    @JsonProperty("status")
    private PatientStatus patientStatus;

}
