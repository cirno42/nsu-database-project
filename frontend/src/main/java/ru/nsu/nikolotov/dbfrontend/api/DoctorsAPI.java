package ru.nsu.nikolotov.dbfrontend.api;

import kong.unirest.GenericType;
import kong.unirest.Unirest;
import ru.nsu.nikolotov.dbfrontend.dtos.DoctorDTO;
import ru.nsu.nikolotov.dbfrontend.entities.DoctorEntity;
import ru.nsu.nikolotov.dbfrontend.entities.DoctorStatisticEntity;
import ru.nsu.nikolotov.dbfrontend.entities.DoctorTypeStatisticEntity;
import ru.nsu.nikolotov.dbfrontend.entities.DoctorWorksAtInstitutionEntity;

import java.util.ArrayList;
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

    public static List<DoctorEntity> getDoctorsWhoMadeSuchCountOfOperations(String doctorType, String instType, Integer instId, Integer count) {
        var dtos =  Unirest.get(ENDPOINT + "/doctorworks/operationsinfo").
                queryString("instId", instId).
                queryString("doctorType", doctorType).
                queryString("institutionType", instType)
                .queryString("count", count)
                .asObject(new GenericType<List<DoctorDTO>>(){}).getBody();
        List<DoctorEntity> entities = new ArrayList<>(dtos.size());
        dtos.forEach(el -> entities.add(DoctorDTO.DTOToEntity(el)));
        return entities;
    }

    public static DoctorTypeStatisticEntity getCountOfDoctorsWhoMadeSuchCountOfOperations(String doctorType, String instType, Integer instId, Integer count) {
        return Unirest.get(ENDPOINT + "/doctorworks/operationsinfo/count").
                queryString("instId", instId).
                queryString("doctorType", doctorType).
                queryString("institutionType", instType)
                .queryString("count", count)
                .asObject(DoctorTypeStatisticEntity.class).getBody();
    }

}
