package ru.nsu.nikolotov.dbproject.backend.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nsu.nikolotov.dbproject.backend.entities.HospitalDepartmentEntity;
import ru.nsu.nikolotov.dbproject.backend.entities.HospitalDepartmentFullInfo;
import ru.nsu.nikolotov.dbproject.backend.repositories.HospitalDepartmentRepository;

import java.util.List;

@Service
public class HospitalDepartmentService {
    @Autowired
    HospitalDepartmentRepository departmentRepository;

    public List<HospitalDepartmentEntity> getDepartmentsInHospital(Integer hospitalId) {
        return departmentRepository.getDepartmentsInHospital(hospitalId);
    }

    public HospitalDepartmentEntity createHospitalDepartment(HospitalDepartmentEntity department) {
        return departmentRepository.createHospitalDepartment(department);
    }

    public HospitalDepartmentEntity getHospitalDepartmentById(int id) {
        return departmentRepository.getHospitalDepById(id);
    }

    public HospitalDepartmentEntity getNextHospitalDepartmentById(int id) {
        return departmentRepository.getNextHospitalDep(id);
    }

    public void deleteHospitalDepartment(int id) {
        departmentRepository.deleteHospitalDepById(id);
    }

    public void updateHospitalDepartment(HospitalDepartmentEntity department) {
        departmentRepository.updateHospitalDepartment(department);
    }

    public List<HospitalDepartmentFullInfo> getAll() {
        return departmentRepository.getAll();
    }

}
