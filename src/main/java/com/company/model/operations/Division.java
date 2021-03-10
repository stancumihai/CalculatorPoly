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
        Polynomial polynomial = new Polynomial(new ArrayList<>(100));
        Polynomial polynomial2 = new Polynomial(new ArrayList<>(100));
        Multiplication multiplication = new Multiplication(polynomial);
        Subtraction subtraction = new Subtraction(polynomial2);

        calculate(polynom1, polynom2);
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

    public Polynomial getResult() {
        return result;
    }

}
