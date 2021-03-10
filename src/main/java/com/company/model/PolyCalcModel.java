package com.company.model;

import com.company.model.operations.Multiplication;
import com.company.model.operations.Subtraction;
import com.company.utils.PolynomDisplay;
import com.company.view.PolyCalcView;

import javax.swing.*;
import java.util.ArrayList;

public class PolyCalcModel {

    private Polynomial result;

    public void addition(Polynomial polynom1, Polynomial polynom2) {
        int i, j;
        i = j = 0;
        while (i < polynom1.getPolynom().size() && j < polynom2.getPolynom().size()) {
            if (polynom1.getPolynom().get(i).getExponent() > polynom2.getPolynom().get(j).getExponent()) {
                result.getPolynom().add(polynom1.getPolynom().get(i++));
            } else if (polynom1.getPolynom().get(i).getExponent() < polynom2.getPolynom().get(j).getExponent()) {
                result.getPolynom().add(polynom2.getPolynom().get(j++));
            } else {
                double coef = polynom1.getPolynom().get(i).getCoefficient() + polynom2.getPolynom().get(j).getCoefficient();
                if (coef != 0) {
                    result.getPolynom().add(new Monomial(polynom1.getPolynom().get(i).getCoefficient() +
                            polynom2.getPolynom().get(j).getCoefficient(), polynom1.getPolynom().get(i).getExponent()));
                }
                i++;
                j++;
            }
        }
        for (; i < polynom1.getPolynom().size(); i++) {
            result.getPolynom().add(new Monomial(polynom1.getPolynom().get(i).getCoefficient(),
                    polynom1.getPolynom().get(i).getExponent()));
        }

        for (; j < polynom2.getPolynom().size(); j++) {

            result.getPolynom().add(new Monomial(polynom2.getPolynom().get(j).getCoefficient(),
                    polynom2.getPolynom().get(j).getExponent()));
        }
    }

    public void subtraction(Polynomial polynom1, Polynomial polynom2) {

        invertCoefficients(polynom2);
        addition(polynom1, polynom2);

    }

    public void multiplication(Polynomial polynom1, Polynomial polynom2) {
        int i, j;
        init(polynom1, polynom2);
        for (i = 0; i < polynom1.getPolynom().size(); i++) {
            for (j = 0; j < polynom2.getPolynom().size(); j++) {
                double coef = result.getPolynom().get(i + j).getCoefficient() + polynom1.getPolynom().get(i).getCoefficient() *
                        polynom2.getPolynom().get(j).getCoefficient();
                double exp = polynom1.getPolynom().get(i).getExponent() +
                        polynom2.getPolynom().get(j).getExponent();
                if (exp == result.getPolynom().get(result.getPolynom().size() - 1).getExponent()) {
                    result.getPolynom().get(result.getPolynom().size() - 1).setCoefficient(
                            result.getPolynom().get(result.getPolynom().size() - 1).getCoefficient() + coef);
                } else {
                    result.getPolynom().add(new Monomial(coef, exp));
                }
            }
        }
        int mySize = result.getPolynom().size();
        for (i = 0; i < mySize; i++) {
            if (verify(i)) {
                result.getPolynom().remove(i);
                mySize--;
                i--;
            }
        }
    }

    private void preDivision(Polynomial polynom1, Polynomial polynom2) {

        while (polynom1.getPolynom().get(0).getExponent() >= polynom2.getPolynom().get(0).getExponent()) {
            double exponent = polynom1.getPolynom().get(0).getExponent() - polynom2.getPolynom().get(0).getExponent();
            double coefficient = polynom1.getPolynom().get(0).getCoefficient() /
                    polynom2.getPolynom().get(0).getCoefficient();

            Polynomial polynomForMultiplication = new Polynomial(new ArrayList<>(2));
            polynomForMultiplication.getPolynom().add(new Monomial(coefficient, exponent));

            result.getPolynom().add(new Monomial(coefficient, exponent));//aici dau append la cat

            Polynomial firstAux = new Polynomial(new ArrayList<>(100));
            Multiplication multiplication = new Multiplication(firstAux);
            multiplication.calculate(polynomForMultiplication, polynom2);

            Polynomial secondAux = new Polynomial(new ArrayList<>(100));
            Subtraction subtraction = new Subtraction(secondAux);
            subtraction.calculate(polynom1, firstAux);

            polynom1 = new Polynomial(new ArrayList<>(100));
            for (Monomial monomial : secondAux.getPolynom()) {
                polynom1.getPolynom().add(monomial);//aici se formeaza restul
            }
            if (polynom1.getPolynom().size() == 0) {
                break;
            }
        }
    }

    public String division(Polynomial polynom1, Polynomial polynom2) {

        Polynomial polynomial = new Polynomial(new ArrayList<>(100));
        Polynomial polynomial2 = new Polynomial(new ArrayList<>(100));
        Multiplication multiplication = new Multiplication(polynomial);
        Subtraction subtraction = new Subtraction(polynomial2);

        preDivision(polynom1, polynom2);
        multiplication.calculate(result, polynom2);
        subtraction.calculate(polynom1, polynomial);

        String polynomRes = PolynomDisplay.constructFromStringToPolynom(result);
        String rest = PolynomDisplay.constructFromStringToPolynom(polynomial2);

        if (rest.equals("0")) {
            return polynomRes;
        } else {
            return polynomRes + "   Rest:(" + rest + "/" + PolynomDisplay.constructFromStringToPolynom(polynom2) + ")";

        }
    }

    public void integration(Polynomial input) {
        int i = 0;
        while (i < input.getPolynom().size()) {
            double coefficient = input.getPolynom().get(i).getCoefficient() /
                    (input.getPolynom().get(i).getExponent() + 1);
            double exponent = input.getPolynom().get(i).getExponent() + 1;
            if (exponent == 0) {
                JOptionPane.showMessageDialog(new PolyCalcView(), "The result of x^-1 is ln(x)");
            }
            if (coefficient != 0) {
                result.getPolynom().add(new Monomial(coefficient, exponent));
            }
            i++;
        }
    }

    public void derivation(Polynomial input) {
        int i = 0;
        while (i < input.getPolynom().size()) {
            double coefficient = input.getPolynom().get(i).getCoefficient() * input.getPolynom().get(i).getExponent();
            double exponent = input.getPolynom().get(i).getExponent() - 1;
            if (coefficient != 0) {
                result.getPolynom().add(new Monomial(coefficient, exponent));
            }
            i++;
        }
    }

    private static void invertCoefficients(Polynomial polynom) {
        for (int i = 0; i < polynom.getPolynom().size(); i++) {
            polynom.getPolynom().get(i).setCoefficient(-polynom.getPolynom().get(i).getCoefficient());
        }
    }

    private void init(Polynomial polynom1, Polynomial polynom2) {
        for (int k = 0; k < polynom1.getPolynom().size() + polynom2.getPolynom().size() - 1; k++) {
            result.getPolynom().add(k, new Monomial(0, 0));
        }
    }

    private boolean verify(int index) {
        return result.getPolynom().get(index).getCoefficient() == 0;
    }

    public Polynomial getResult() {
        return result;
    }

    public void setResult(Polynomial result) {
        this.result = result;
    }
}
