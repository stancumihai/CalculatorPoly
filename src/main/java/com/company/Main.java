package com.company;

import com.company.controller.PolyCalcController;
import com.company.model.PolyCalcModel;
import com.company.validator.PolynomValidator;
import com.company.view.PolyCalcView;

public class Main {

    public static void main(String[] args) {
        PolyCalcView polyCalcView = new PolyCalcView();
        PolyCalcModel polyCalcModel = new PolyCalcModel();
        PolynomValidator polynomValidator = new PolynomValidator();
        PolyCalcController polyCalcController = new PolyCalcController(polyCalcView, polynomValidator, polyCalcModel);
        polyCalcView.setVisible(true);
    }
}
