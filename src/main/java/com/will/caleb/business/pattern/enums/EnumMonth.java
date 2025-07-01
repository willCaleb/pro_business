package com.will.caleb.business.pattern.enums;

import java.util.Arrays;

public enum EnumMonth {

    JANUARY("January", "Janeiro", 1),
    FEBRUARY("february", "Fevereiro", 2),
    MARCH("March", "Março", 3),
    APRIL("April", "Abril", 4),
    MAY("May", "Maio", 5),
    JUNE("June", "Junho", 6),
    JULY("July", "Julho", 7),
    AUGUST("August", "Agosto", 8),
    SEPTEMBER("September", "Setembro", 9),
    OCTOBER("October", "Outubro", 10),
    NOVEMBER("November", "Novembro", 11),
    DECEMBER("December", "Dezembro", 12);

    private final String defaultValue;
    private final String localValue;
    private final Integer sequence;

    EnumMonth(String defaultValue, String localValue, Integer sequence ) {
        this.defaultValue = defaultValue;
        this.localValue = localValue;
        this.sequence = sequence;
    }

    public String getDefaultValue() {
        return this.defaultValue;
    }

    public String getLocalValue() {
        return this.localValue;
    }

    public Integer getSequence() {
        return this.sequence;
    }

    public static String getLocalValue(String defaultValue) {

        String[] parts = defaultValue.split("\\s+");

        String month = parts[0];
        String year = parts[1];

        String convertedMonth = Arrays.stream(values())
                .filter(enumMonth -> enumMonth.defaultValue.equalsIgnoreCase(month))
                .map(EnumMonth::getLocalValue)
                .findFirst()
                .orElse(null);

        return convertedMonth + " " + year;
    }
}
