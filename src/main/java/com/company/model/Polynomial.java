package com.company.model;

import java.util.ArrayList;

public class Polynomial extends Monomial {

    private ArrayList<Monomial> polynom;

    public Polynomial(double coefficient, double exponent, ArrayList<Monomial> polynom) {
        super(coefficient, exponent);
        this.polynom = polynom;
    }

    public Polynomial(ArrayList<Monomial> polynom) {
        this.polynom = polynom;
    }

    public ArrayList<Monomial> getPolynom() {
        return polynom;
    }

    public void setPolynom(ArrayList<Monomial> polynom) {
        this.polynom = polynom;
    }

    @Override
    public String toString() {
        return "Polynomial{" +
                "polynom=" + polynom +
                '}';
    }
}
