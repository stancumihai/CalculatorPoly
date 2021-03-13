package com.company;

import com.company.controller.PolyCalcController;
import com.company.model.PolyCalcModel;
import com.company.model.Polynomial;
import com.company.utils.Regex;
import com.company.utils.StringToPolynomConverter;
import com.company.validator.PolynomValidator;
import com.company.view.PolyCalcView;

public class Main {

    public static void main(String[] args) {

        PolyCalcModel polyCalcModel = new PolyCalcModel();
        PolyCalcView polyCalcView = new PolyCalcView();
        PolynomValidator polynomValidator = new PolynomValidator();
        PolyCalcController polyCalcController = new PolyCalcController(polyCalcView, polynomValidator, polyCalcModel);
        polyCalcView.setVisible(true);

//        String pol1 = "2X2";
//        Polynomial pol = StringToPolynomConverter.regEx(Regex.getRegex(), pol1);
//        PolynomValidator polynomValidator = new PolynomValidator();
//        boolean b=polynomValidator.checkIfNotCorrect(Regex.getRegex(), pol1);
//        System.out.println(b);
    }
}
