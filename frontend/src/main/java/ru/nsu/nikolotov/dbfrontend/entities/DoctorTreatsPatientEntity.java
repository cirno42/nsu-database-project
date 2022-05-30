package ru.nsu.nikolotov.dbfrontend.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class DoctorTreatsPatientEntity implements EntityForInsertIntoJTable{
    private Integer patientId;
    private String patientName;
    private Integer doctorId;
    private String doctorName;
    private Date admissionDate;
    private Double admissionTemperature;

    @Override
    public String[] getHeaders() {
        return new String[] {"Patient ID", "Patient Name", "Doctor ID", "Doctor Name", "Addmision Date", "Admission Temp."};
    }

    @Override
    public String[] getValues() {
        return new String[] {patientId.toString(), patientName, doctorId.toString(), doctorName, admissionDate.toString(), admissionTemperature.toString()};
    }
}
