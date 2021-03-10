package com.company.test;

import com.company.model.Monomial;
import com.company.model.Polynomial;
import com.company.model.operations.Division;

import java.util.ArrayList;

public class Tester {

    public static void test() {
        Polynomial pol1 = new Polynomial(new ArrayList<>(100));
        Polynomial pol2 = new Polynomial(new ArrayList<>(100));
        Monomial monomial1 = new Monomial(1, 5);
        Monomial monomial2 = new Monomial(1, 3);
        Monomial monomial3 = new Monomial(1, 0);
        pol1.getPolynom().add(monomial1);
        pol2.getPolynom().add(monomial2);
        pol2.getPolynom().add(monomial3);
        Polynomial result = new Polynomial(new ArrayList<>(100));
        Division division = new Division(result);
        division.calculate(pol1, pol2);
        division.getResult().getPolynom().forEach(System.out::println);
    }
}
