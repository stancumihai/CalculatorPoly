package com.company.model.operations;

import com.company.model.Monomial;
import com.company.model.Polynomial;

public class Addition extends AbstractOperationClass<Polynomial> {

    private final Polynomial result;

    public Addition(Polynomial result) {
        this.result = result;
    }

    @Override
    public void calculate(Polynomial polynom1, Polynomial polynom2) {
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

    public Polynomial getResult() {
        return result;
    }
}
