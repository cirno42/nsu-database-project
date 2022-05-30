package ru.nsu.nikolotov.dbfrontend.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class DoneOperationsForPatientEntity implements EntityForInsertIntoJTable{
    private Integer id;
    private String name;
    private String operationName;
    private Date dateOfOperation;
    private Boolean isResultLethal;

    @Override
    public String[] getHeaders() {
        return new String[] {"ID", "Name", "Operation name", "Date of operation", "Is result lethal"};
    }

    @Override
    public String[] getValues() {
        return new String[] {id.toString(), name, operationName, dateOfOperation.toString(), isResultLethal.toString()};
    }
}
