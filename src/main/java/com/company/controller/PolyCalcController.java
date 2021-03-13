package com.company.controller;

import com.company.exception.NotValidDataEntered;
import com.company.model.PolyCalcModel;
import com.company.model.Polynomial;
import com.company.utils.PolynomDisplay;
import com.company.utils.Regex;
import com.company.utils.StringToPolynomConverter;
import com.company.validator.PolynomValidator;
import com.company.view.PolyCalcView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

public class PolyCalcController {

    private final PolyCalcView polyCalcView;
    private final PolynomValidator polynomValidator;
    private final PolyCalcModel polyCalcModel;

    public PolyCalcController(PolyCalcView polyCalcView, PolynomValidator polynomValidator, PolyCalcModel polyCalcModel) {

        this.polyCalcView = polyCalcView;
        this.polynomValidator = polynomValidator;
        this.polyCalcModel = polyCalcModel;

        this.polyCalcView.addButtonListener(new ButtonListener());
        this.polyCalcView.addClearListener(new ClearListener());
        this.polyCalcView.addCalculateListener(new CalculateListener());
        this.polyCalcView.addClearAllListener(new ClearAllListener());
    }

    class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (Objects.equals(polyCalcView.getSelectPolynomsComboBox().getSelectedItem(), "1")) {
                    if (e.getActionCommand().equals("del")) {
                        polyCalcView.getPolynom1Field().setText(Optional.ofNullable(polyCalcView.getPolynom1Field().getText())
                                .filter(sStr -> sStr.length() != 0)
                                .map(sStr -> sStr.substring(0, sStr.length() - 1))
                                .orElse(polyCalcView.getPolynom1Field().getText()));
                    } else {
                        polyCalcView.getPolynom1Field().setText(polyCalcView.getPolynom1Field().getText() + e.getActionCommand());
                    }
                } else {
                    if (e.getActionCommand().equals("del")) {
                        polyCalcView.getPolynom2Field().setText(Optional.ofNullable(polyCalcView.getPolynom2Field().getText())
                                .filter(sStr -> sStr.length() != 0)
                                .map(sStr -> sStr.substring(0, sStr.length() - 1))
                                .orElse(polyCalcView.getPolynom2Field().getText()));
                    } else {
                        polyCalcView.getPolynom2Field().setText(polyCalcView.getPolynom2Field().getText() + e.getActionCommand());
                    }
                }

            } catch (Exception exception) {
                exception.printStackTrace();
                polyCalcView.displayErrorMessage("Bad input");
            }
        }
    }

    class ClearListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String nameId = e.getSource().toString();
            try {
                if (nameId.contains("clearOne")) {
                    polyCalcView.getPolynom1Field().setText(null);
                } else if (nameId.contains("clearTwo")) {
                    polyCalcView.getPolynom2Field().setText(null);
                } else {
                    polyCalcView.getPolynomRezField().setText(null);
                }
            } catch (Exception exception) {
                exception.printStackTrace();
                polyCalcView.displayErrorMessage("Bad input");
            }
        }
    }

    class ClearAllListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            polyCalcView.getPolynom1Field().setText(null);
            polyCalcView.getPolynom2Field().setText(null);
            polyCalcView.getPolynomRezField().setText(null);
        }
    }

    class CalculateListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {

                Polynomial polynomial1 = StringToPolynomConverter.regEx(Regex.getRegex(), polyCalcView.getPolynom1Field().getText());
                Polynomial polynomial2 = StringToPolynomConverter.regEx(Regex.getRegex(), polyCalcView.getPolynom2Field().getText());

                polynomValidator.validate(Regex.getRegex(), PolynomDisplay.constructFromStringToPolynom(polynomial1));
                polynomValidator.validate(Regex.getRegex(), PolynomDisplay.constructFromStringToPolynom(polynomial2));

                switch (Objects.requireNonNull(polyCalcView.getSelectOperationsComboBox().getSelectedItem()).toString()) {
                    case "ADD":
                        additionListener(polyCalcModel, polynomValidator, polynomial1, polynomial2, polyCalcView);
                        break;
                    case "SUBTRACT":
                        subtractListener(polyCalcModel, polynomValidator, polynomial1, polynomial2, polyCalcView);
                        break;
                    case "MULTIPLY":
                        multiplyListener(polyCalcModel, polynomValidator, polynomial1, polynomial2, polyCalcView);
                        break;
                    case "DIVIDE":
                        divisionListener(polyCalcModel, polynomValidator, polynomial1, polynomial2, polyCalcView);
                        break;
                    case "INTEGRATE":
                        integrateListener(polyCalcModel, polynomValidator, polynomial1, polynomial2, polyCalcView);
                        break;
                    case "DERIVATE":
                        derivateListener(polyCalcModel, polynomValidator, polynomial1, polynomial2, polyCalcView);
                        break;
                }
            } catch (NotValidDataEntered nde) {
                nde.printStackTrace();
                polyCalcView.displayErrorMessage("Exponents are unordered");
            }
        }
    }


    private static void additionListener(PolyCalcModel polyCalcModel, PolynomValidator polynomValidator,
                                         Polynomial polynomial1, Polynomial polynomial2, PolyCalcView polyCalcView) {
        try {
            polyCalcModel.setResult(new Polynomial(new ArrayList<>(100)));
            polyCalcModel.addition(polynomial1, polynomial2);
            polynomValidator.validate(Regex.getRegex(), PolynomDisplay.constructFromStringToPolynom(polyCalcModel.getResult()));
            polyCalcView.setPolynomRezField(PolynomDisplay.constructFromStringToPolynom(polyCalcModel.getResult()));

        } catch (NotValidDataEntered exception) {
            exception.printStackTrace();
            polyCalcView.displayErrorMessage("Bad input for addition");
        }
    }

    private static void subtractListener(PolyCalcModel polyCalcModel, PolynomValidator polynomValidator,
                                         Polynomial polynomial1, Polynomial polynomial2, PolyCalcView polyCalcView) {
        try {
            polyCalcModel.setResult(new Polynomial(new ArrayList<>()));
            polyCalcModel.subtraction(polynomial1, polynomial2);
            polynomValidator.validate(Regex.getRegex(), PolynomDisplay.constructFromStringToPolynom(polyCalcModel.getResult()));
            polyCalcView.setPolynomRezField(PolynomDisplay.constructFromStringToPolynom(polyCalcModel.getResult()));

        } catch (Exception exception) {
            exception.printStackTrace();
            polyCalcView.displayErrorMessage("Bad input for for subtraction");
        }
    }

    private static void multiplyListener(PolyCalcModel polyCalcModel, PolynomValidator polynomValidator,
                                         Polynomial polynomial1, Polynomial polynomial2, PolyCalcView polyCalcView) {
        try {
            polyCalcModel.setResult(new Polynomial(new ArrayList<>(5)));
            polyCalcModel.multiplication(polynomial1, polynomial2);
            polyCalcView.setPolynomRezField(PolynomDisplay.constructFromStringToPolynom(polyCalcModel.getResult()));

        } catch (Exception exception) {
            exception.printStackTrace();
            polyCalcView.displayErrorMessage("Bad input for multiplication");
        }
    }

    private static void divisionListener(PolyCalcModel polyCalcModel, PolynomValidator polynomValidator,
                                         Polynomial polynomial1, Polynomial polynomial2, PolyCalcView polyCalcView) {
        try {
            if (polynomial2.getPolynom().size() == 0 ||
                    (polynomial2.getPolynom().get(0).getCoefficient() == 0 && polynomial2.getPolynom().size() == 1)) {
                JOptionPane.showMessageDialog(polyCalcView, "Cannot Divide with 0");
            } else {
                polyCalcModel.setResult(new Polynomial(new ArrayList<>(5)));
                String res = polyCalcModel.division(polynomial1, polynomial2);
                polyCalcView.setPolynomRezField(res);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            polyCalcView.displayErrorMessage("Bad input for division");
        }
    }

    private static void derivateListener(PolyCalcModel polyCalcModel, PolynomValidator polynomValidator,
                                         Polynomial polynomial1, Polynomial polynomial2, PolyCalcView polyCalcView) {
        try {
            polyCalcModel.setResult(new Polynomial(new ArrayList<>(5)));

            if (Objects.equals(polyCalcView.getSelectPolynomsComboBox().getSelectedItem(), "1")) {
                polyCalcModel.derivation(polynomial1);
            } else {
                polyCalcModel.derivation(polynomial2);
            }
            polynomValidator.validate(Regex.getRegex(),
                    PolynomDisplay.constructFromStringToPolynom(polyCalcModel.getResult()));
            polyCalcView.setPolynomRezField(PolynomDisplay
                    .constructFromStringToPolynom(polyCalcModel.getResult()));

        } catch (Exception exception) {
            exception.printStackTrace();
            polyCalcView.displayErrorMessage("Bad input for derivation");
        }
    }

    private static void integrateListener(PolyCalcModel polyCalcModel, PolynomValidator polynomValidator,
                                          Polynomial polynomial1, Polynomial polynomial2, PolyCalcView polyCalcView) {
        try {

            polyCalcModel.setResult(new Polynomial(new ArrayList<>(5)));
            if (Objects.equals(polyCalcView.getSelectPolynomsComboBox().getSelectedItem(), "1")) {
                polyCalcModel.integration(polynomial1);
            } else {
                polyCalcModel.integration(polynomial2);
            }
            polynomValidator.validate(Regex.getRegex(),
                    PolynomDisplay.constructFromStringToPolynom(polyCalcModel.getResult()));
            polyCalcView.setPolynomRezField(PolynomDisplay.
                    constructFromStringToPolynom(polyCalcModel.getResult()));

        } catch (Exception exception) {
            exception.printStackTrace();
            polyCalcView.displayErrorMessage("Bad input for integration");
        }
    }
}
