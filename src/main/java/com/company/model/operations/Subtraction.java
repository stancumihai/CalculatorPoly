package com.company.model.operations;

import com.company.model.Polynomial;

public class Subtraction extends AbstractOperationClass<Polynomial> {

    private final Polynomial result;

    public Subtraction(Polynomial result) {
        this.result = result;
    }

    @Override
    public void calculate(Polynomial polynom1, Polynomial polynom2) {

        Addition addition = new Addition(result);
        invertCoefficients(polynom2);
        addition.calculate(polynom1, polynom2);

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
