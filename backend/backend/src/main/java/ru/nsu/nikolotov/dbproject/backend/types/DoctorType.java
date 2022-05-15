package ru.nsu.nikolotov.dbproject.backend.types;

public enum DoctorType {
    NONE(null),
    DENTIST("dentist"),
    NEUROPATHOLOGIST("neuropathologist"),
    OPHTHALMOLOGIST("ophthalmologist"),
    RADIOLOGIST("radiologist"),
    SURGEON("surgeon"),
    THERAPIST("therapist");

    private final String type;
    private DoctorType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }

    public static DoctorType fromString(String typeString) {
        typeString = typeString.toLowerCase();
        if (typeString.equals(DENTIST.toString())) {
            return DENTIST;
        } else if (typeString.equals(NEUROPATHOLOGIST.toString())) {
            return NEUROPATHOLOGIST;
        } else if (typeString.equals(OPHTHALMOLOGIST.toString())) {
            return OPHTHALMOLOGIST;
        } else if (typeString.equals(RADIOLOGIST.toString())) {
            return RADIOLOGIST;
        } else if (typeString.equals(SURGEON.toString())) {
            return SURGEON;
        } else if (typeString.equals(THERAPIST.toString())) {
            return THERAPIST;
        }
        return NONE;
    }
}

