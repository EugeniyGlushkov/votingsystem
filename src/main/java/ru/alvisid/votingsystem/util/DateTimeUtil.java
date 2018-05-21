package ru.alvisid.votingsystem.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static LocalTime OVER_TIME = LocalTime.of(11, 0, 0);

    private DateTimeUtil(){}

    public static <T extends Comparable<? super T>> boolean isBetween(T value, T start, T end) {
        return value.compareTo(start) >= 0 && value.compareTo(end) <= 0;
    }

    public static String toString(LocalDate ld) {
        return ld == null ? "" : ld.format(DATE_TIME_FORMATTER);
    }
}
