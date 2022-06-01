package ru.nsu.nikolotov.dbfrontend.api;

import kong.unirest.GenericType;
import kong.unirest.Unirest;
import ru.nsu.nikolotov.dbfrontend.dtos.CreateDoctorWorksAtHospitalDTO;
import ru.nsu.nikolotov.dbfrontend.entities.DoctorWorksAtHospitalFullInfoEntity;
import ru.nsu.nikolotov.dbfrontend.entities.HospitalEntity;

import java.util.List;

public class DoctorWorksAtHospitalAPI {

    private static String ENDPOINT = "http://localhost:8080/api/v1/doctorworksathospital";

    public static List<DoctorWorksAtHospitalFullInfoEntity> getAll() {
        return Unirest.get(ENDPOINT + "/getall")
                .asObject(new GenericType<List<DoctorWorksAtHospitalFullInfoEntity>>(){})
                .getBody();
    }

    public static List<DoctorWorksAtHospitalFullInfoEntity> getHistory() {
        return Unirest.get(ENDPOINT + "/history")
                .asObject(new GenericType<List<DoctorWorksAtHospitalFullInfoEntity>>(){})
                .getBody();
    }

    public static DoctorWorksAtHospitalFullInfoEntity create(CreateDoctorWorksAtHospitalDTO dto) {
        return Unirest.post(ENDPOINT + "/create")
                .header("Content-Type", "application/json")
                .body(dto)
                .asObject(DoctorWorksAtHospitalFullInfoEntity.class)
                .getBody();
    }

    public static void fireDoctor(Integer id) {
        Unirest.delete(ENDPOINT).queryString("id", id).asEmpty();
    }
}
