package ru.nsu.nikolotov.dbproject.backend.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class DoneOperationsForPatientEntity {
    private Integer id;
    private String name;
    private String operationName;
    private Date dateOfOperation;
    private Boolean isResultLethal;
}
