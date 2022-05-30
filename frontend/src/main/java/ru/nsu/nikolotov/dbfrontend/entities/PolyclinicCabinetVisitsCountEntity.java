package ru.nsu.nikolotov.dbfrontend.entities;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PolyclinicCabinetVisitsCountEntity implements EntityForInsertIntoJTable{
    Integer polyclinicId;
    String polyclinicName;
    Integer cabinetId;
    String cabinetName;
    Integer count;

    @Override
    public String[] getHeaders() {
        return new String[] {
                "polyclinic ID", "Polyclinic Name", "Cabinet ID", "Cabinet Name", "Visits count"
        };
    }

    @Override
    public String[] getValues() {
        return new String[] {polyclinicId.toString(), polyclinicName, cabinetId.toString(), cabinetName, count.toString()};
    }
}
