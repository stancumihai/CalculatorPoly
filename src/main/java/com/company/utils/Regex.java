package com.company.utils;

public class Regex {

    private static final String regex = "([-+]?)(\\d*\\.?\\d*)?([xX](\\^-?\\d*\\.?\\d*)?)?";

    public static String getRegex() {
        return regex;
    }
}
