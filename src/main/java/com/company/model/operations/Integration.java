package com.company.model.operations;

import com.company.model.Monomial;
import com.company.model.Polynomial;

public class Integration {

    private final Polynomial result;

    public Integration(Polynomial result) {
        this.result = result;
    }

    public void calculate(Polynomial input) {
        int i = 0;
        while (i < input.getPolynom().size()) {
            double coefficient = input.getPolynom().get(i).getCoefficient() /
                    (input.getPolynom().get(i).getExponent() + 1);
            double exponent = input.getPolynom().get(i).getExponent() + 1;
            if(exponent==0){
                result.getPolynom().add(new Monomial());
            }
            if (coefficient != 0) {
                result.getPolynom().add(new Monomial(coefficient, exponent));
            }
            i++;
        }
    }

    public Polynomial getResult() {
        return result;
    }
}
