package com.company.model.operations;

import com.company.model.Monomial;
import com.company.model.Polynomial;
import com.company.utils.PolynomDisplay;

import java.util.ArrayList;

public class Division extends AbstractOperationClass<Polynomial> {
    private final Polynomial result;

    public Division(Polynomial result) {
        this.result = result;
    }


    @Override
    public void calculate(Polynomial polynom1, Polynomial polynom2) {

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

    public String giveFinalResult(Polynomial polynom1, Polynomial polynom2) {
        Polynomial aux = new Polynomial(new ArrayList<>());
        for (Monomial monomial : polynom1.getPolynom()) {
            aux.getPolynom().add(monomial);
        }

        calculate(polynom1, polynom2);
        String polynomRes = PolynomDisplay.constructFromStringToPolynom(result);
        String numitorRest = PolynomDisplay.constructFromStringToPolynom(polynom1);
        String numaratorRest = PolynomDisplay.constructFromStringToPolynom(polynom2);
        if (polynomRes.length() == 1 && polynomRes.charAt(0) == '0') {
            if (numitorRest.length() == 1 && numitorRest.charAt(0) == '0') {
                return "0";
            } else {
                return "(" + numitorRest + "\\" + numaratorRest + ")";
            }
        } else {
            if (numitorRest.length() == 1 && numitorRest.charAt(0) == '0') {
                return polynomRes;
            } else {
                return polynomRes + "+ (" + numitorRest + "\\" + numaratorRest + ")";
            }
        }
    }

    public Polynomial getResult() {
        return result;
    }

    public static void invertCoefficients(Polynomial polynom) {
        for (int i = 0; i < polynom.getPolynom().size(); i++) {
            polynom.getPolynom().get(i).setCoefficient(-polynom.getPolynom().get(i).getCoefficient());
        }
    }
}
