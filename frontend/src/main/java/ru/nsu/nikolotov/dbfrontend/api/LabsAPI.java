package ru.nsu.nikolotov.dbfrontend.api;

import kong.unirest.GenericType;
import kong.unirest.Unirest;
import ru.nsu.nikolotov.dbfrontend.entities.DoneOperationsForPatientEntity;
import ru.nsu.nikolotov.dbfrontend.entities.LaboratoryEntity;
import ru.nsu.nikolotov.dbfrontend.entities.LaboratoryServiceStatisticEntity;

import java.util.List;

public class LabsAPI {
    private static String ENDPOINT = "http://localhost:8080/api/v1/labs";

    public static List<LaboratoryEntity> getAll() {
        return Unirest.get(ENDPOINT + "/getall").asObject(new GenericType<List<LaboratoryEntity>>(){}).getBody();
    }

    public static List<LaboratoryServiceStatisticEntity> getLabServicesStatistic(
            Integer labId,
            String labType,
            String beginDate,
            String endDate,
            Integer institutionId,
            String institutionType) {
        return Unirest.get(ENDPOINT + "/services/statistic")
                .queryString("labId", labId)
                .queryString("labType", labType)
                .queryString("beginDate", beginDate)
                .queryString("endDate", endDate)
                .queryString("institutionId", institutionId)
                .queryString("institutionType", institutionType)
                .asObject(new GenericType<List<LaboratoryServiceStatisticEntity>>(){})
                .getBody();

    }

    public static List<LaboratoryEntity> getLabsWithSuchType(String labType) {
        return Unirest.get(ENDPOINT + "/get/withtype")
                .queryString("labType", labType)
                .asObject(new GenericType<List<LaboratoryEntity>>(){})
                .getBody();
    }
}
