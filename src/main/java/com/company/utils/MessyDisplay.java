package com.company.utils;

import com.company.model.Monomial;
import com.company.model.Polynomial;

import java.math.BigDecimal;
import java.math.RoundingMode;


public class MessyDisplay {

    public static String constructFromStringToPolynom(Polynomial polynom) {

        StringBuilder myPolynom = new StringBuilder();
        for (Monomial monomial : polynom.getPolynom()) {
            BigDecimal coefDecimal = new BigDecimal(String.valueOf(monomial.getCoefficient()));
            BigDecimal expDecimal = new BigDecimal(String.valueOf(monomial.getExponent()));
            if (coefDecimal.doubleValue() != 1 && expDecimal.doubleValue() != 0) {
                if (expDecimal.doubleValue() != 1) {
                    if (coefDecimal.doubleValue() >= 0) {
                        myPolynom.append("+").append(coefDecimal.setScale(2, RoundingMode.HALF_UP)).append("x^").append(expDecimal.doubleValue());
                    } else {
                        if (coefDecimal.doubleValue() == -1) {
                            myPolynom.append("-").append("X^").append(expDecimal.doubleValue());
                        } else {
                            myPolynom.append(coefDecimal.setScale(2, RoundingMode.HALF_UP)).append("X^").append(expDecimal.doubleValue());
                        }
                    }
                } else {
                    if (coefDecimal.doubleValue() >= 0) {
                        myPolynom.append("+").append(coefDecimal.setScale(2, RoundingMode.HALF_UP)).append("X");
                    } else {
                        myPolynom.append(coefDecimal.setScale(2, RoundingMode.HALF_UP)).append("X");
                    }
                }
            } else if (coefDecimal.doubleValue() == 1 && expDecimal.doubleValue() != 0) {
                if (expDecimal.doubleValue() != 1) {
                    myPolynom.append("+").append("X^").append(expDecimal.doubleValue());
                } else {
                    myPolynom.append("+").append("X");
                }
            } else {
                if (coefDecimal.doubleValue() >= 0) {
                    myPolynom.append("+").append(coefDecimal.setScale(2, RoundingMode.HALF_UP));

                } else {
                    if (coefDecimal.doubleValue() != -1) {
                        myPolynom.append(coefDecimal.setScale(2, RoundingMode.HALF_UP));
                    }
                }
            }
        }
        if (myPolynom.length() > 0 && myPolynom.charAt(myPolynom.toString().length() - 1) == '+') {
            myPolynom.setLength(myPolynom.length() - 1);
        }
        if (myPolynom.length() == 0) {
            return "0";
        } else if (myPolynom.charAt(0) == '+') {
            return myPolynom.substring(1, myPolynom.length());
        } else return myPolynom.toString();
    }
}
