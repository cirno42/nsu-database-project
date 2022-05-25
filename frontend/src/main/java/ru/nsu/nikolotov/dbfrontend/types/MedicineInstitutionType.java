package ru.nsu.nikolotov.dbfrontend.types;


public enum MedicineInstitutionType {
    HOSPITAL("hospital"),
    POLYCLINIC("polyclinic"),
    ALL("all"),
    NONE(null);

    private final String typeString;
    private MedicineInstitutionType(String type) {
        this.typeString = type;
    }

    public String toString() {
        return typeString;
    }

    public static MedicineInstitutionType fromString(String type) {
        if (type == null) {
            return NONE;
        }
        if (type.toLowerCase().equals(HOSPITAL.typeString)) {
            return HOSPITAL;
        } else if (type.toLowerCase().equals(POLYCLINIC.typeString)) {
            return POLYCLINIC;
        } else if (type.toLowerCase().equals(ALL.typeString)) {
            return ALL;
        }
        return NONE;
    }
}
