package ru.nsu.nikolotov.dbfrontend.api;

import kong.unirest.GenericType;
import kong.unirest.Unirest;
import ru.nsu.nikolotov.dbfrontend.entities.DoctorStatisticEntity;
import ru.nsu.nikolotov.dbfrontend.entities.DoctorTypeStatisticEntity;
import ru.nsu.nikolotov.dbfrontend.entities.DoctorWorksAtInstitutionEntity;

import java.util.List;

public class DoctorsAPI {
    private static final String ENDPOINT = "http://localhost:8080/api/v1/doctors";

    public static List<DoctorWorksAtInstitutionEntity> getDoctorWorksAtInstitution(String doctorType, String instType, Integer instId) {
        return Unirest.get(ENDPOINT + "/doctorworks/info").
                queryString("instId", instId).
                queryString("doctorType", doctorType).
                queryString("institutionType", instType).
                asObject(new GenericType<List<DoctorWorksAtInstitutionEntity>>(){}).getBody();
    }

    public static DoctorTypeStatisticEntity getDoctorWorksAtInstitutionCount(String doctorType, String instType, Integer instId) {
        return Unirest.get(ENDPOINT + "/doctorworks/info/count").
                queryString("instId", instId).
                queryString("doctorType", doctorType).
                queryString("institutionType", instType).
                asObject(DoctorTypeStatisticEntity.class).getBody();
    }

}
