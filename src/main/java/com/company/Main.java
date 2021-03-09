package com.company;

import com.company.controller.PolyCalcController;
import com.company.test.Tester;
import com.company.validator.PolynomValidator;
import com.company.view.PolyCalcView;

public class Main {

    public static void main(String[] args) {

        PolyCalcView polyCalcView = new PolyCalcView();
        PolynomValidator polynomValidator = new PolynomValidator();
        PolyCalcController polyCalcController = new PolyCalcController(polyCalcView, polynomValidator);
        polyCalcView.setVisible(true);
//        Tester.test();
    }
}
