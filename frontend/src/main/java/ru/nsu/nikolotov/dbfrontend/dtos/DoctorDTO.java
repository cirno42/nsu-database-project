package ru.nsu.nikolotov.dbfrontend.dtos;


import lombok.Getter;
import lombok.Setter;
import ru.nsu.nikolotov.dbfrontend.entities.DoctorEntity;
import ru.nsu.nikolotov.dbfrontend.types.DoctorSciencePosition;
import ru.nsu.nikolotov.dbfrontend.types.DoctorScienceRank;
import ru.nsu.nikolotov.dbfrontend.types.DoctorType;


import java.util.Date;

@Getter
@Setter
public class DoctorDTO {
    private Integer id;
    private String name;
    private Date vacationStart;
    private Date vacationEnd;
    private Double salaryCoefficient;
    private String doctorTypeString;
    private String doctorSciencePositionString;
    private String doctorScienceRankString;

    public static DoctorEntity DTOToEntity(DoctorDTO dto) {
        var entity = new DoctorEntity();
        entity.setId(dto.id);
        entity.setName(dto.name);
        entity.setVacationStart(dto.vacationStart);
        entity.setVacationEnd(dto.vacationEnd);
        entity.setSalaryCoefficient(dto.salaryCoefficient);
        entity.setDoctorType(DoctorType.fromString(dto.doctorTypeString));
        entity.setDoctorSciencePosition(DoctorSciencePosition.fromString(dto.doctorSciencePositionString));
        entity.setDoctorScienceRank(DoctorScienceRank.fromString(dto.doctorScienceRankString));
        entity.setCanDoOperation(entity.getDoctorType() == DoctorType.SURGEON || entity.getDoctorType() == DoctorType.DENTIST);
        return entity;
    }

    public static DoctorDTO EntityToDTO(DoctorEntity entity) {
        var dto = new DoctorDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setVacationStart(entity.getVacationStart());
        dto.setVacationEnd(entity.getVacationEnd());
        dto.setSalaryCoefficient(entity.getSalaryCoefficient());
        if (entity.getDoctorType() != null) {
            dto.setDoctorTypeString(entity.getDoctorType().toString());
        }
        if (entity.getDoctorSciencePosition() != null) {
            dto.setDoctorSciencePositionString(entity.getDoctorSciencePosition().toString());
        }
        if (entity.getDoctorScienceRank() != null) {
            dto.setDoctorScienceRankString(entity.getDoctorScienceRank().toString());
        }
        return dto;
    }
}
