package com.subham.PatientApp.Entity;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@Data
public abstract class AbstractEntity implements Serializable {

    @Id
    @Column(name="id", updatable=false, nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
