package testMethods;


import com.company.model.Monomial;
import com.company.model.Polynomial;
import com.company.utils.Regex;
import com.company.utils.StringToPolynomConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import testOperations.AbstractValidate;

import java.util.ArrayList;

public class testStringToPolynomConverter implements AbstractValidate {


    @Override
    @RepeatedTest(5)
    public void validate() {
        test1();
        test2();
        test3();
        test4();
    }

    @Test
    private void test1() {

        String input = "2X^2+3";
        Polynomial polynomial = StringToPolynomConverter.regEx(Regex.getRegex(), input);
        ArrayList<Double> coefficients = new ArrayList<>();
        ArrayList<Double> exponents = new ArrayList<>();
        coefficients.add(2.0);
        coefficients.add(3.0);
        exponents.add(2.0);
        exponents.add(0.0);
        int counter = 0;
        for (Monomial monomial : polynomial.getPolynom()) {
            Assertions.assertEquals(monomial.getExponent(), exponents.get(counter));
            Assertions.assertEquals(monomial.getCoefficient(), coefficients.get(counter));
            counter++;
        }
    }

    @Test
    private void test2() {
        String input = "3X^3+4";
        Polynomial polynomial = StringToPolynomConverter.regEx(Regex.getRegex(), input);
        ArrayList<Double> coefficients = new ArrayList<>();
        ArrayList<Double> exponents = new ArrayList<>();
        coefficients.add(3.0);
        coefficients.add(4.0);
        exponents.add(3.0);
        exponents.add(0.0);
        int counter = 0;
        for (Monomial monomial : polynomial.getPolynom()) {
            Assertions.assertEquals(monomial.getExponent(), exponents.get(counter));
            Assertions.assertEquals(monomial.getCoefficient(), coefficients.get(counter));
            counter++;
        }
    }

    @Test
    private void test3() {
        String input = "5X^5+3X+2";
        Polynomial polynomial = StringToPolynomConverter.regEx(Regex.getRegex(), input);
        ArrayList<Double> coefficients = new ArrayList<>();
        ArrayList<Double> exponents = new ArrayList<>();
        coefficients.add(5.0);
        coefficients.add(3.0);
        coefficients.add(2.0);
        exponents.add(5.0);
        exponents.add(1.0);
        exponents.add(0.0);
        int counter = 0;
        for (Monomial monomial : polynomial.getPolynom()) {
            Assertions.assertEquals(monomial.getExponent(), exponents.get(counter));
            Assertions.assertEquals(monomial.getCoefficient(), coefficients.get(counter));
            counter++;
        }
    }

    @Test
    private void test4() {
        String input = "X";
        Polynomial polynomial = StringToPolynomConverter.regEx(Regex.getRegex(), input);
        ArrayList<Double> coefficients = new ArrayList<>();
        ArrayList<Double> exponents = new ArrayList<>();
        coefficients.add(1.0);
        exponents.add(1.0);
        int counter = 0;
        for (Monomial monomial : polynomial.getPolynom()) {
            Assertions.assertEquals(monomial.getExponent(), exponents.get(counter));
            Assertions.assertEquals(monomial.getCoefficient(), coefficients.get(counter));
            counter++;
        }
    }
}