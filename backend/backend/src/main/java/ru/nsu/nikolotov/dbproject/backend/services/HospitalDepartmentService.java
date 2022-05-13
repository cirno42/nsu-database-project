package ru.nsu.nikolotov.dbproject.backend.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nsu.nikolotov.dbproject.backend.entities.HospitalDepartmentEntity;
import ru.nsu.nikolotov.dbproject.backend.repositories.HospitalDepartmentRepository;

@Service
public class HospitalDepartmentService {
    @Autowired
    HospitalDepartmentRepository departmentRepository;

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

}
