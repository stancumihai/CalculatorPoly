package com.company.utils;

import com.company.model.Monomial;
import com.company.model.Polynomial;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 3 si 4 sunt nule cand am constante
 * 4 e null cand nu am exponent(gen X
 */
public class StringToPolynomConverter {


    public static Polynomial regEx(String theRegex, String stringToCheck) {

        Pattern checkRegEx = Pattern.compile(theRegex);
        Matcher regexMatcher = checkRegEx.matcher(stringToCheck);
        ArrayList<Double> coefficients = new ArrayList<>();
        ArrayList<Double> exponents = new ArrayList<>();
        while (regexMatcher.find()) {
            if (regexMatcher.group().length() != 0) {
                if (regexMatcher.group(4) == null) {
                    case1(coefficients, exponents, regexMatcher);
                } else {
                    case2(coefficients, exponents, regexMatcher);
                }
            }
        }

        return constructPol(coefficients, exponents);
    }

    private static void case1(ArrayList<Double> coefficients, ArrayList<Double> exponents, Matcher regexMatcher) {
        if (regexMatcher.group(3) == null) {
            if (regexMatcher.group(1).equals("-")) {
                if (regexMatcher.group(2).isEmpty()) {
                    coefficients.add(-Double.parseDouble("1"));
                } else {
                    coefficients.add(-Double.parseDouble(regexMatcher.group(2)));
                }
            } else {
                if (regexMatcher.group(2).isEmpty()) {
                    coefficients.add(Double.parseDouble("1"));
                } else coefficients.add(Double.parseDouble(regexMatcher.group(2)));
            }
            exponents.add(Double.parseDouble("0"));
        } else {
            if (regexMatcher.group(1).equals("-")) {
                if (regexMatcher.group(2).isEmpty()) {
                    coefficients.add(-Double.parseDouble("1"));
                } else {
                    coefficients.add(-Double.parseDouble(regexMatcher.group(2)));
                }
            } else {
                if (regexMatcher.group(2).isEmpty()) {
                    coefficients.add(Double.parseDouble("1"));
                } else {
                    coefficients.add(Double.parseDouble(regexMatcher.group(2)));
                }
            }
            exponents.add(Double.parseDouble("1"));
        }
    }

    private static void case2(ArrayList<Double> coefficients, ArrayList<Double> exponents, Matcher regexMatcher) {
        if (regexMatcher.group(1).equals("-")) {
            if (regexMatcher.group(2).isEmpty()) {
                coefficients.add(Double.parseDouble("-1"));
            } else {
                coefficients.add(Double.parseDouble("-" + regexMatcher.group(2)));
            }
        } else {
            if (regexMatcher.group(2).isEmpty()) {
                coefficients.add(Double.parseDouble("1"));
            } else {
                coefficients.add(Double.parseDouble(regexMatcher.group(2)));
            }
        }
        exponents.add(Double.parseDouble(String.valueOf(regexMatcher.group(4)).substring(1)));
    }


    private static Polynomial constructPol(ArrayList<Double> coefficients, ArrayList<Double> exponents) {
        Polynomial polynom = new Polynomial(new ArrayList<>(100));
        for (int i = 0; i < coefficients.size(); i++) {
            polynom.getPolynom().add(new Monomial(coefficients.get(i), exponents.get(i)));
        }
        return polynom;
    }
}
