package ru.nsu.nikolotov.dbfrontend.api;

import kong.unirest.GenericType;
import kong.unirest.Unirest;
import ru.nsu.nikolotov.dbfrontend.entities.DoctorWorksAtInstitutionEntity;
import ru.nsu.nikolotov.dbfrontend.entities.HospitalEntity;
import ru.nsu.nikolotov.dbfrontend.entities.PolyclinicEntity;

import java.util.List;

public class HospitalAPI {
    private static final String ENDPOINT = "http://localhost:8080/api/v1/hospitals";
    public static List<HospitalEntity> getAll() {
        return Unirest.get(ENDPOINT + "/getall").asObject(new GenericType<List<HospitalEntity>>(){}).getBody();
    }


}
