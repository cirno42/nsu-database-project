package ru.nsu.nikolotov.dbfrontend.api;

import kong.unirest.GenericType;
import kong.unirest.Unirest;
import ru.nsu.nikolotov.dbfrontend.dtos.DoctorDTO;
import ru.nsu.nikolotov.dbfrontend.entities.*;

import java.util.ArrayList;
import java.util.List;

public class DoctorsAPI {
    private static final String ENDPOINT = "http://localhost:8080/api/v1/doctors";

    public static List<DoctorDTO> getAllDTO() {
        var doctors = Unirest.get("http://localhost:8080/api/v1/doctors/getall").asObject(new GenericType<List<DoctorDTO>>(){}).getBody();
        return doctors;
    }

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

    public static List<DoctorExperienceEntity> getDoctorsWithSuchExperience(String doctorType, String instType, Integer instId, Integer experience) {
        return Unirest.get(ENDPOINT + "/doctorworks/experinced").
                queryString("instId", instId).
                queryString("doctorType", doctorType).
                queryString("institutionType", instType)
                .queryString("experience", experience)
                .asObject(new GenericType<List<DoctorExperienceEntity>>(){}).getBody();
    }


    public static DoctorTypeStatisticEntity getCountDoctorsWithSuchExperience(String doctorType, String instType, Integer instId, Integer experience) {
        return Unirest.get(ENDPOINT + "/doctorworks/experinced/count").
                queryString("instId", instId).
                queryString("doctorType", doctorType).
                queryString("institutionType", instType)
                .queryString("experience", experience)
                .asObject(DoctorTypeStatisticEntity.class).getBody();
    }

    public static List<DoctorEntity> getDoctorsWithSuchRankAndPosition(String doctorType, String instType, Integer instId, String rank, String position) {
        var dtos =  Unirest.get(ENDPOINT + "/doctorranks").
                queryString("instId", instId).
                queryString("doctorType", doctorType).
                queryString("institutionType", instType)
                .queryString("rank", rank)
                .queryString("position", position)
                .asObject(new GenericType<List<DoctorDTO>>(){}).getBody();
        List<DoctorEntity> entities = new ArrayList<>(dtos.size());
        dtos.forEach(el -> entities.add(DoctorDTO.DTOToEntity(el)));
        return entities;
    }


    public static DoctorTypeStatisticEntity getCountOfDoctorsWithSuchRankAndPosition(String doctorType, String instType, Integer instId, String rank, String position) {
        return Unirest.get(ENDPOINT + "/doctorranks/count").
                queryString("instId", instId).
                queryString("doctorType", doctorType).
                queryString("institutionType", instType)
                .queryString("rank", rank)
                .queryString("position", position)
                .asObject(DoctorTypeStatisticEntity.class).getBody();
    }

    public static List<DoctorStatisticEntity> getDoctorHospitalWorkStats(Integer doctorId, Integer hospitalId, String doctorType) {
        return Unirest.get(ENDPOINT + "/doctorstatistic/hospitals")
                .queryString("doctorId", doctorId)
                .queryString("hospitalId", hospitalId)
                .queryString("doctorType", doctorType)
                .asObject(new GenericType<List<DoctorStatisticEntity>>(){})
                .getBody();
    }

    public static List<DoctorPolyclinicWorkStatistic> getDoctorPolyclinicWorksStats(Integer doctorId, Integer polyclinicId, String doctorType, String beginDate, String endDate) {
        return Unirest.get(ENDPOINT + "/doctorstatistic/polyclinics")
                .queryString("doctorId", doctorId)
                .queryString("polyclinicId", polyclinicId)
                .queryString("doctorType", doctorType)
                .queryString("beginDate", beginDate)
                .queryString("endDate", endDate)
                .asObject(new GenericType<List<DoctorPolyclinicWorkStatistic>>(){})
                .getBody();
    }
}
