package com.company.validator;

import com.company.exception.NotValidDataEntered;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PolynomValidator {

    private StringBuilder exponents;

    public void validate(String theRegex, String polynom) {

        if (checkIfExponentsAreNotCorrect(theRegex, polynom)) {
            throw new NotValidDataEntered("Invalid exponents");
        }
        if (checkIfNotCorrect(theRegex, polynom)) {
            throw new NotValidDataEntered("Invalid arrangement of the polynom");
        }
        if (checkIfStringIsEmpty(polynom)) {
            throw new NotValidDataEntered("String should not be empty");
        }
    }

    public boolean checkIfNotCorrect(String theRegex, String stringToCheck) {

        Pattern checkRegEx = Pattern.compile(theRegex);
        Matcher regexMatcher = checkRegEx.matcher(stringToCheck);
        StringBuilder stringBuilder = new StringBuilder();
        if (stringToCheck.isEmpty()) {
            return true;
        }
        while (regexMatcher.find()) {
            if (regexMatcher.group().length() != 0) {
                if (regexMatcher.group(3) != null) {
                    stringBuilder.append(regexMatcher.group(1)).append(regexMatcher.group(2)).append(regexMatcher.group(3));
                } else stringBuilder.append(regexMatcher.group(1)).append(regexMatcher.group(2));
            }
        }

        return !stringBuilder.toString().equals(stringToCheck);
    }

    public boolean checkIfExponentsAreNotCorrect(String theRegex, String stringToCheck) {
        Pattern checkRegEx = Pattern.compile(theRegex);
        Matcher regexMatcher = checkRegEx.matcher(stringToCheck);
        StringBuilder exponents = new StringBuilder();
        if (stringToCheck.isEmpty())
            return true;
        while (regexMatcher.find()) {
            if (regexMatcher.group().length() != 0) {

                if (regexMatcher.group(4) == null) {
                    if (regexMatcher.group(3) != null) {
                        exponents.append("1");
                    } else {
                        exponents.append("0");
                    }
                } else {
                    exponents.append(regexMatcher.group(4).charAt(1));
                }
            }
        }

        ArrayList<Character> toSort = new ArrayList<>();
        for (Character character : exponents.toString().toCharArray()) {
            toSort.add(character);
        }

        toSort.sort((o1, o2) -> -o1.compareTo(o2));
        for (int i = 0; i < exponents.toString().length(); i++) {
            if (exponents.toString().charAt(i) != toSort.get(i))
                return true;
        }
        return false;
    }

    public boolean checkIfStringIsEmpty(String polynom) {
        return polynom.isEmpty();
    }
}
