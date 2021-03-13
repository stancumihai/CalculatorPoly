package com.company.utils;

import com.company.model.Monomial;
import com.company.model.Polynomial;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * AM luat pe cazurile:
 * 1) exponentul nu e 0 si coeficientul nu e 1
 * - daca exponentul e 1
 * - daca coeficientul e mai are sau nu decat 0(asta o verific pentru cazul in care avem un nr cu - si sa nu avem +- la afisare
 * 2) exponentul nu e 0 si coeficientul e 1
 * - daca exponentul e 1
 * 3) exponentul e 0 si coeficentul e oricat
 * - daca coeficientul e mai are sau nu decat 0
 **/
public class PolynomDisplay {

    public static String constructFromStringToPolynom(Polynomial polynom) {

        StringBuilder myPolynom = new StringBuilder();
        for (Monomial monomial : polynom.getPolynom()) {

            BigDecimal coefDecimal = new BigDecimal(String.valueOf(monomial.getCoefficient()));
            BigDecimal expDecimal = new BigDecimal(String.valueOf(monomial.getExponent()));

            if (coefDecimal.doubleValue() != 1 && expDecimal.doubleValue() != 0) {
                case1(myPolynom, coefDecimal, expDecimal);
            } else if (coefDecimal.doubleValue() == 1 && expDecimal.doubleValue() != 0) {
                case2(myPolynom, coefDecimal, expDecimal);
            } else {
                case3(myPolynom, coefDecimal, expDecimal);
            }
        }
        if (myPolynom.length() > 0 && myPolynom.charAt(myPolynom.toString().length() - 1) == '+') {
            //** aici anulez plusul de la final**//
            myPolynom.setLength(myPolynom.length() - 1);
        }
        if (myPolynom.length() == 0) {
            return "0";
        } else if (myPolynom.charAt(0) == '+') {
            return myPolynom.substring(1, myPolynom.length());/** Cu substring (1,lenght anulez acel plus din fata)**/
        } else return myPolynom.toString();
    }


    private static void case1(StringBuilder myPolynom, BigDecimal coefDecimal, BigDecimal expDecimal) {
        if (expDecimal.doubleValue() != 1) {
            case11(myPolynom, coefDecimal, expDecimal);
        } else {
            case12(myPolynom, coefDecimal, expDecimal);
        }
    }

    private static void case11(StringBuilder myPolynom, BigDecimal coefDecimal, BigDecimal expDecimal) {
        if (coefDecimal.doubleValue() >= 0) {
            if (coefDecimal.intValue() != coefDecimal.doubleValue()) {
                if (expDecimal.intValue() != expDecimal.doubleValue()) {
                    myPolynom.append("+").append(coefDecimal.setScale(2, RoundingMode.HALF_UP)).append("x^").append(expDecimal.doubleValue());
                } else {
                    myPolynom.append("+").append(coefDecimal.setScale(2, RoundingMode.HALF_UP)).append("X^").append(expDecimal.intValue());
                }
            } else {
                if (expDecimal.intValue() != expDecimal.doubleValue()) {
                    myPolynom.append("+").append(coefDecimal.intValue()).append("X^").append(expDecimal.doubleValue());
                } else {
                    myPolynom.append("+").append(coefDecimal.intValue()).append("X^").append(expDecimal.intValue());
                }
            }
        } else {
            if (coefDecimal.doubleValue() == -1) {
                if (expDecimal.intValue() != expDecimal.doubleValue()) {
                    myPolynom.append("-").append("X^").append(expDecimal.doubleValue());
                } else {
                    myPolynom.append("-").append("X^").append(expDecimal.intValue());
                }
            } else {
                if (coefDecimal.doubleValue() != coefDecimal.intValue()) {
                    if (expDecimal.intValue() != expDecimal.doubleValue()) {
                        myPolynom.append(coefDecimal.setScale(2, RoundingMode.HALF_UP)).append("X^").append(expDecimal.doubleValue());
                    } else {
                        myPolynom.append(coefDecimal.doubleValue()).append("X^").append(expDecimal.intValue());
                    }
                } else {
                    if (expDecimal.intValue() != expDecimal.doubleValue()) {
                        myPolynom.append(coefDecimal.intValue()).append("X^").append(expDecimal.doubleValue());
                    } else {
                        myPolynom.append(coefDecimal.intValue()).append("X^").append(expDecimal.intValue());
                    }
                }
            }
        }
    }

    private static void case12(StringBuilder myPolynom, BigDecimal coefDecimal, BigDecimal expDecimal) {
        if (coefDecimal.doubleValue() >= 0) {
            if (coefDecimal.intValue() != coefDecimal.doubleValue()) {
                myPolynom.append("+")
                        .append(coefDecimal.setScale(2, RoundingMode.HALF_UP))
                        .append("X");
            } else {
                myPolynom.append("+")
                        .append(coefDecimal.intValue())
                        .append("X");
            }
        } else {
            if (coefDecimal.intValue() != coefDecimal.doubleValue()) {
                myPolynom.append(coefDecimal.setScale(2, RoundingMode.HALF_UP))
                        .append("X");
            } else {
                if (coefDecimal.intValue() == -1) {
                    myPolynom.append("-X");
                } else {
                    myPolynom.append(coefDecimal.intValue())
                            .append("X");
                }
            }
        }
    }

    private static void case2(StringBuilder myPolynom, BigDecimal coefDecimal, BigDecimal expDecimal) {
        if (expDecimal.doubleValue() != 1) {
            if (expDecimal.doubleValue() != expDecimal.intValue()) {
                myPolynom.append("+")
                        .append("X^")
                        .append(expDecimal.doubleValue());
            } else {
                myPolynom.append("+")
                        .append("X^")
                        .append(expDecimal.intValue());
            }
        } else {
            myPolynom.append("+")
                    .append("X");
        }
    }

    private static void case3(StringBuilder myPolynom, BigDecimal coefDecimal, BigDecimal expDecimal) {
        if (coefDecimal.doubleValue() >= 0) {
            if (coefDecimal.doubleValue() != coefDecimal.intValue()) {
                myPolynom.append("+")
                        .append(coefDecimal.setScale(2, RoundingMode.HALF_UP));
            } else {
                myPolynom.append("+")
                        .append(coefDecimal.intValue());
            }

        } else {
            if (coefDecimal.doubleValue() != -1) {
                if (coefDecimal.doubleValue() != coefDecimal.intValue()) {
                    myPolynom.append(coefDecimal.setScale(2, RoundingMode.HALF_UP));
                } else {
                    myPolynom.append(coefDecimal.intValue());
                }
            } else myPolynom.append("-1");
        }
    }
}
