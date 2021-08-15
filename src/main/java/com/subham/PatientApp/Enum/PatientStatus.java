package com.subham.PatientApp.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum PatientStatus {
    ADMITTED("admitted"),
    NOT_CURED("not cured"),
    CURED("cured"),
    DISCHARGED("discharged");
    private String description;


}
