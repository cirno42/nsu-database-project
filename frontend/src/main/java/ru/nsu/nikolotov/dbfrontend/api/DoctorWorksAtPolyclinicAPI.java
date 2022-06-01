package ru.nsu.nikolotov.dbfrontend.api;

import kong.unirest.GenericType;
import kong.unirest.Unirest;
import ru.nsu.nikolotov.dbfrontend.dtos.CreateDoctorWorksAtHospitalDTO;
import ru.nsu.nikolotov.dbfrontend.dtos.CreateDoctorWorksAtPolyclinicDTO;
import ru.nsu.nikolotov.dbfrontend.entities.DoctorWorksAtHospitalFullInfoEntity;
import ru.nsu.nikolotov.dbfrontend.entities.DoctorWorksAtPolyclinicFullInfo;

import java.util.List;

public class DoctorWorksAtPolyclinicAPI {

    private static String ENDPOINT = "http://localhost:8080/api/v1/doctorworksatpolyclinic";

    public static List<DoctorWorksAtPolyclinicFullInfo> getAll() {
        return Unirest.get(ENDPOINT + "/getall")
                .asObject(new GenericType<List<DoctorWorksAtPolyclinicFullInfo>>(){})
                .getBody();
    }

    public static List<DoctorWorksAtPolyclinicFullInfo> getHistory() {
        return Unirest.get(ENDPOINT + "/history")
                .asObject(new GenericType<List<DoctorWorksAtPolyclinicFullInfo>>(){})
                .getBody();
    }

    public static DoctorWorksAtPolyclinicFullInfo create(CreateDoctorWorksAtPolyclinicDTO dto) {
        return Unirest.post(ENDPOINT + "/create")
                .header("Content-Type", "application/json")
                .body(dto)
                .asObject(DoctorWorksAtPolyclinicFullInfo.class)
                .getBody();
    }

    public static void fireDoctor(Integer id) {
        Unirest.delete(ENDPOINT).queryString("id", id).asEmpty();
    }
}
