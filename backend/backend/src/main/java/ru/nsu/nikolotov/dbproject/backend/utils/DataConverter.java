package ru.nsu.nikolotov.dbproject.backend.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.Date;
import java.util.Locale;

public class DataConverter {
    public static Date getDateFromString(String s) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);
        LocalDate localDate = LocalDate.parse(s, formatter);
        return Date.valueOf(localDate);
    }
}
