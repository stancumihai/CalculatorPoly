package com.company.validator;

import com.company.exception.NotValidDataEntered;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PolynomValidator {

    private StringBuilder exponents;
    private final String meineRegex = "^([-+](?![-+])([0-9]*\\.?[0-9]+)?[*]?([xX](\\^[0-9]+)?)?)+";
    private final Pattern pattern = Pattern.compile(meineRegex);

    public void validate(String theRegex, String polynom) {

        if (checkIfExponentsAreNotCorrect(theRegex, polynom)) {
            throw new NotValidDataEntered("Invalid exponents");
        }
        if (checkIfNotCorrect(polynom)) {
            throw new NotValidDataEntered("Invalid arrangement of the polynom");
        }
    }

    public boolean checkIfNotCorrect(String stringToCheck) {

        if (stringToCheck.charAt(0) != '-' && stringToCheck.charAt(0) != '+') {
            stringToCheck = stringToCheck.replace(stringToCheck, "+" + stringToCheck);
        }
        Matcher matcher = this.pattern.matcher(stringToCheck);
        boolean validate = matcher.matches();
        return !validate;
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
            if (charExists(character, toSort)) {
                return true;
            }
        }

        toSort.sort((o1, o2) -> -o1.compareTo(o2));
        for (int i = 0; i < exponents.toString().length(); i++) {
            if (exponents.toString().charAt(i) != toSort.get(i))
                return true;
        }
        return false;
    }

    boolean charExists(Character character, ArrayList<Character> charList) {
        for (Character myCharacter : charList) {
            if (myCharacter.equals(character)) {
                return true;
            }
        }
        return false;
    }
}
