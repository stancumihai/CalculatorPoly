package com.company.model.operations;

import com.company.model.Monomial;
import com.company.model.Polynomial;

public class Multiplication extends AbstractOperationClass<Polynomial> {
    private final Polynomial result;

    public Multiplication(Polynomial result) {
        this.result = result;
    }

    @Override
    public void calculate(Polynomial polynom1, Polynomial polynom2) {
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

    public Polynomial getResult() {
        return result;
    }

    public void init(Polynomial polynom1, Polynomial polynom2) {
        for (int k = 0; k < polynom1.getPolynom().size() + polynom2.getPolynom().size() - 1; k++) {
            result.getPolynom().add(k, new Monomial(0, 0));
        }
    }

    public boolean verify(int index) {
        return result.getPolynom().get(index).getCoefficient() == 0;
    }
}
