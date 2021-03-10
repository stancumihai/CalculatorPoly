package com.company.controller;

import com.company.exception.NotValidDataEntered;
import com.company.model.PolyCalcModel;
import com.company.model.Polynomial;
import com.company.model.operations.*;
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

                Polynomial result = new Polynomial(new ArrayList<>(100));

                switch (Objects.requireNonNull(polyCalcView.getSelectOperationsComboBox().getSelectedItem()).toString()) {
                    case "ADD":
                        try {
                            Addition addition = new Addition(result);
                            addition.calculate(polynomial1, polynomial2);
                            polynomValidator.validate(Regex.getRegex(), PolynomDisplay.constructFromStringToPolynom(result));
                            polyCalcView.setPolynomRezField(PolynomDisplay.constructFromStringToPolynom(result));

                        } catch (NotValidDataEntered exception) {
                            exception.printStackTrace();
                            polyCalcView.displayErrorMessage("Bad input for addition");
                        }
                        break;
                    case "SUBTRACT":
                        try {
                            Subtraction subtraction = new Subtraction(result);
                            subtraction.calculate(polynomial1, polynomial2);
                            polynomValidator.validate(Regex.getRegex(), PolynomDisplay.constructFromStringToPolynom(result));
                            polyCalcView.setPolynomRezField(PolynomDisplay.constructFromStringToPolynom(result));

                        } catch (Exception exception) {
                            exception.printStackTrace();
                            polyCalcView.displayErrorMessage("Bad input for for subtraction");
                        }
                        break;
                    case "MULTIPLY":
                        try {

                            Multiplication multiplication = new Multiplication(result);
                            multiplication.calculate(polynomial1, polynomial2);
                            polynomValidator.validate(Regex.getRegex(), PolynomDisplay.constructFromStringToPolynom(result));
                            polyCalcView.setPolynomRezField(PolynomDisplay.constructFromStringToPolynom(result));

                        } catch (Exception exception) {
                            exception.printStackTrace();
                            polyCalcView.displayErrorMessage("Bad input for multiplication");
                        }
                        break;
                    case "DIVIDE":
                        try {

                            if (polynomial2.getPolynom().size() == 0) {
                                JOptionPane.showMessageDialog(polyCalcView, "Cannot Divide with 0");
                            } else {
                                Division division = new Division(result);
                                String res = division.giveFinalResult(polynomial1, polynomial2);
                                //polynomValidator.validate(Regex.getRegex(), PolynomDisplay.constructFromStringToPolynom(result));
                                polyCalcView.setPolynomRezField(res);
                            }


                        } catch (Exception exception) {
                            exception.printStackTrace();
                            polyCalcView.displayErrorMessage("Bad input for division");
                        }
                        break;
                    case "INTEGRATE":
                        try {

                            Integration integration = new Integration(result);
                            if (Objects.equals(polyCalcView.getSelectPolynomsComboBox().getSelectedItem(), "1")) {
                                integration.calculate(polynomial1);
                            } else {
                                integration.calculate(polynomial2);
                            }
                            polynomValidator.validate(Regex.getRegex(), PolynomDisplay.constructFromStringToPolynom(result));
                            polyCalcView.setPolynomRezField(PolynomDisplay.constructFromStringToPolynom(result));


                        } catch (Exception exception) {
                            exception.printStackTrace();
                            polyCalcView.displayErrorMessage("Bad input for integration");
                        }
                        break;
                    case "DERIVATE":
                        try {

                            Derivation derivation = new Derivation(result);

                            if (Objects.equals(polyCalcView.getSelectPolynomsComboBox().getSelectedItem(), "1")) {
                                derivation.calculate(polynomial1);
                            } else {
                                derivation.calculate(polynomial2);
                            }
                            polynomValidator.validate(Regex.getRegex(), PolynomDisplay.constructFromStringToPolynom(result));
                            polyCalcView.setPolynomRezField(PolynomDisplay.constructFromStringToPolynom(result));

                        } catch (Exception exception) {
                            exception.printStackTrace();
                            polyCalcView.displayErrorMessage("Bad input for derivation");
                        }
                        break;
                }
            } catch (NotValidDataEntered nde) {
                nde.printStackTrace();
                polyCalcView.displayErrorMessage("Exponents are unordered");
            }
        }
    }
}
