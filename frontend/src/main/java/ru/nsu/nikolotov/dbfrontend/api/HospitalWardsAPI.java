package ru.nsu.nikolotov.dbfrontend.api;

import kong.unirest.GenericType;
import kong.unirest.Unirest;
import ru.nsu.nikolotov.dbfrontend.entities.HospitalDepartmentEntity;
import ru.nsu.nikolotov.dbfrontend.entities.HospitalPlacesStatisticEntity;
import ru.nsu.nikolotov.dbfrontend.entities.HospitalWardEntity;
import ru.nsu.nikolotov.dbfrontend.entities.HospitalWardFullInfoEntity;

import java.util.List;

public class HospitalWardsAPI {
    private static final String ENDPOINT = "http://localhost:8080/api/v1/hospitalwards";

    public static List<HospitalWardEntity> getWardsInDepartment(Integer departmentId) {
      //  System.out.println("Department:" + departmentId);
        return Unirest.get(ENDPOINT + "/indepartment")
                .queryString("departmentId", departmentId)
                .asObject(new GenericType<List<HospitalWardEntity>>(){})
                .getBody();
    }

    public static List<HospitalWardFullInfoEntity> getAll() {
        return Unirest.get(ENDPOINT + "/getall")
                .asObject(new GenericType<List<HospitalWardFullInfoEntity>>(){})
                .getBody();
    }

    public static List<HospitalPlacesStatisticEntity> getPlacesStats(String placeType, String statType, String unitType, Integer unitId) {
        String url = ENDPOINT + "/statistic/" + statType + "/" + placeType + "/" + unitType;
        return Unirest.get(url)
                .queryString("id", unitId)
                .asObject(new GenericType<List<HospitalPlacesStatisticEntity>>(){})
                .getBody();

    }

}
