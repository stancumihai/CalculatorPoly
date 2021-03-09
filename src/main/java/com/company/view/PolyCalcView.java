package com.company.view;

import com.company.model.Operation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class PolyCalcView extends JFrame {

    private final JLabel selectOperationLabel;
    private final JComboBox<Operation> selectOperationsComboBox;
    private final JLabel selectPolynomLabel;
    private final JComboBox<String> selectPolynomsComboBox;
    private final JButton zero;
    private final JButton one;
    private final JButton two;
    private final JButton three;
    private final JButton four;
    private final JButton five;
    private final JButton six;
    private final JButton seven;
    private final JButton eight;
    private final JButton nine;
    private final JButton plus;
    private final JButton minus;
    private final JButton multiply;
    private final JButton divide;
    private final JButton power;
    private final JButton dot;
    private final JButton X;
    private final JButton del;

    private final JButton calculate;
    private final JLabel polynom1;
    private final JTextField polynom1Field;

    private final JLabel polynom2;
    private final JTextField polynom2Field;

    private final JLabel polynomRez;
    private final JTextField polynomRezField;

    private final JButton clearPol1;
    private final JButton clearPol2;
    private final JButton clearPolRez;
    private final JButton clearAll;

    private final JPanel myFrame;
    private final JPanel selectTheOperation;
    private final JPanel selectThePolynom;
    private final JPanel selectPanel;
    private final JPanel polynom1Panel;
    private final JPanel polynom2Panel;
    private final JPanel polynomRezPanel;
    private final JPanel polynomsPanel;
    private final JPanel group1Buttons;
    private final JPanel group2Buttons;
    private final JPanel buttonsPanel;
    private final JPanel calculatePanel;

    public PolyCalcView() {

        setSize(440, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        myFrame = new JPanel();
        myFrame.setLayout(new BoxLayout(myFrame, BoxLayout.Y_AXIS));
        selectTheOperation = new JPanel();
        selectTheOperation.setLayout(new BoxLayout(selectTheOperation, BoxLayout.Y_AXIS));
        selectThePolynom = new JPanel();
        selectThePolynom.setLayout(new BoxLayout(selectThePolynom, BoxLayout.Y_AXIS));
        selectPanel = new JPanel();
        selectPanel.setLayout(new BoxLayout(selectPanel, BoxLayout.X_AXIS));
        polynom1Panel = new JPanel();
        polynom1Panel.setLayout(new BoxLayout(polynom1Panel, BoxLayout.X_AXIS));
        polynom2Panel = new JPanel();
        polynom2Panel.setLayout(new BoxLayout(polynom2Panel, BoxLayout.X_AXIS));
        polynomRezPanel = new JPanel();
        polynomRezPanel.setLayout(new BoxLayout(polynomRezPanel, BoxLayout.X_AXIS));
        polynomsPanel = new JPanel();
        polynomsPanel.setLayout(new GridLayout(3, 1));

        group1Buttons = new JPanel();
        group1Buttons.setLayout(new GridLayout(3, 3));
        group1Buttons.setPreferredSize(new Dimension(this.getWidth() / 4, this.getHeight() / 4));

        group2Buttons = new JPanel();
        group2Buttons.setLayout(new GridLayout(3, 3));
        group2Buttons.setPreferredSize(new Dimension(this.getWidth() / 4, this.getHeight() / 4));

        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        buttonsPanel.setBorder(new EmptyBorder(0, this.getWidth() / 8, 0, this.getWidth() / 8));

        calculatePanel = new JPanel();
        calculatePanel.setLayout(new FlowLayout());

        Operation[] operations = Operation.values();
        String[] indexOfPolynoms = {"1", "2"};

        selectOperationLabel = new JLabel("Select the Operation");
        selectOperationLabel.setFont(new Font("ARIAL", Font.BOLD, 14));
        selectOperationLabel.setAlignmentX(CENTER_ALIGNMENT);
        selectOperationLabel.setBorder(new EmptyBorder(0, 0, 10, 0));

        selectOperationsComboBox = new JComboBox<>(operations);
        selectOperationsComboBox.setMaximumSize(new Dimension(this.getWidth() / 4, 25));
        selectOperationsComboBox.setAlignmentX(CENTER_ALIGNMENT);

        selectTheOperation.add(selectOperationLabel);
        selectTheOperation.add(selectOperationsComboBox);

        selectPolynomLabel = new JLabel("Select polynom");
        selectPolynomLabel.setFont(new Font("ARIAL", Font.BOLD, 14));
        selectPolynomLabel.setAlignmentX(CENTER_ALIGNMENT);
        selectPolynomLabel.setBorder(new EmptyBorder(0, 0, 10, 0));

        selectPolynomsComboBox = new JComboBox<>(indexOfPolynoms);
        selectPolynomsComboBox.setMaximumSize(new Dimension(this.getWidth() / 4, 25));
        selectPolynomsComboBox.setAlignmentX(CENTER_ALIGNMENT);

        selectThePolynom.add(selectPolynomLabel);
        selectThePolynom.add(selectPolynomsComboBox);
        selectThePolynom.setBorder(new EmptyBorder(0, 30, 0, 0));
        selectPanel.add(selectTheOperation);
        selectPanel.add(selectThePolynom);
        selectPanel.setBorder(new EmptyBorder(60, 0, 60, 0));

        polynom1 = new JLabel("Polynom 1");
        polynom1.setHorizontalAlignment(JLabel.CENTER);
        polynom1.setFont(new Font("ARIAL", Font.BOLD, 14));
        polynom1.setBorder(new EmptyBorder(0, 0, 0, 20));
        polynom1Field = new JTextField(15);
        polynom1Field.setPreferredSize(new Dimension(this.getWidth() / 2, 25));
        clearPol1 = new JButton("Clear");
        clearPol1.setName("clearOne");

        polynom1Panel.setLayout(new FlowLayout());
        polynom1Panel.add(polynom1);
        polynom1Panel.add(polynom1Field);
        polynom1Panel.add(clearPol1);
        polynom1Panel.setAlignmentY(10);

        polynom2 = new JLabel("Polynom 2");
        polynom2.setFont(new Font("ARIAL", Font.BOLD, 14));
        polynom2.setBorder(new EmptyBorder(0, 0, 0, 20));
        polynom2Field = new JTextField(15);
        polynom2Field.setPreferredSize(new Dimension(this.getWidth() / 2, 25));

        clearPol2 = new JButton("Clear");
        clearPol2.setName("clearTwo");

        polynom2Panel.setLayout(new FlowLayout());
        polynom2Panel.add(polynom2);
        polynom2Panel.add(polynom2Field);
        polynom2Panel.add(clearPol2);

        polynomRez = new JLabel("Polynom rez");
        polynomRez.setFont(new Font("ARIAL", Font.BOLD, 14));
        polynomRez.setBorder(new EmptyBorder(0, 0, 0, 8));
        polynomRezField = new JTextField(15);
        polynomRezField.setMaximumSize(new Dimension(this.getWidth() / 2, 40));
        polynomRezField.setPreferredSize(new Dimension(this.getWidth() / 2, 25));
        clearPolRez = new JButton("Clear");
        clearAll = new JButton("Clear All");

        polynomRezPanel.setLayout(new FlowLayout());

        polynom1Field.setEditable(false);
        polynom2Field.setEditable(false);
        polynomRezField.setEditable(false);

        polynomRezPanel.add(polynomRez);
        polynomRezPanel.add(polynomRezField);
        polynomRezPanel.add(clearPolRez);

        one = new JButton("1");
        two = new JButton("2");
        three = new JButton("3");
        four = new JButton("4");
        five = new JButton("5");
        six = new JButton("6");
        seven = new JButton("7");
        eight = new JButton("8");
        nine = new JButton("9");
        zero = new JButton("0");
        plus = new JButton("+");
        minus = new JButton("-");
        multiply = new JButton("*");
        divide = new JButton(":");
        power = new JButton("^");
        dot = new JButton(".");
        X = new JButton("X");
        del = new JButton("del");
        del.setFont(new Font("Arial", Font.PLAIN, 11));
        calculate = new JButton("Calculate");
        calculate.setAlignmentX(LEFT_ALIGNMENT);
        clearAll.setAlignmentX(RIGHT_ALIGNMENT);

        group1Buttons.add(one);
        group1Buttons.add(two);
        group1Buttons.add(three);
        group1Buttons.add(four);
        group1Buttons.add(five);
        group1Buttons.add(six);
        group1Buttons.add(seven);
        group1Buttons.add(eight);
        group1Buttons.add(nine);

        group2Buttons.add(zero);
        group2Buttons.add(plus);
        group2Buttons.add(minus);
        group2Buttons.add(multiply);
        group2Buttons.add(divide);
        group2Buttons.add(power);
        group2Buttons.add(dot);
        group2Buttons.add(X);
        group2Buttons.add(del);

        myFrame.add(selectPanel);
        polynomsPanel.add(polynom1Panel);
        polynomsPanel.add(polynom2Panel);
        polynomsPanel.add(polynomRezPanel);
        polynomsPanel.setBorder(new EmptyBorder(0, 0, 40, 0));

        myFrame.add(polynomsPanel);

        buttonsPanel.add(group1Buttons);
        buttonsPanel.add(new JLabel("       "));
        calculatePanel.setBorder(new EmptyBorder(20, 0, 30, 0));

        buttonsPanel.add(group2Buttons);
        myFrame.add(buttonsPanel);
        calculatePanel.add(calculate);
        calculatePanel.add(clearAll);
        myFrame.add(calculatePanel);
        add(myFrame);

    }

    public JComboBox<String> getSelectPolynomsComboBox() {
        return selectPolynomsComboBox;
    }

    public JComboBox<Operation> getSelectOperationsComboBox() {
        return selectOperationsComboBox;
    }

    public JTextField getPolynom1Field() {
        return polynom1Field;
    }

    public JTextField getPolynom2Field() {
        return polynom2Field;
    }

    public JTextField getPolynomRezField() {
        return polynomRezField;
    }

    public void addButtonListener(ActionListener buttonListener) {

        one.addActionListener(buttonListener);
        two.addActionListener(buttonListener);
        three.addActionListener(buttonListener);
        four.addActionListener(buttonListener);
        five.addActionListener(buttonListener);
        six.addActionListener(buttonListener);
        seven.addActionListener(buttonListener);
        eight.addActionListener(buttonListener);
        nine.addActionListener(buttonListener);
        zero.addActionListener(buttonListener);
        plus.addActionListener(buttonListener);
        minus.addActionListener(buttonListener);
        multiply.addActionListener(buttonListener);
        divide.addActionListener(buttonListener);
        power.addActionListener(buttonListener);
        dot.addActionListener(buttonListener);
        X.addActionListener(buttonListener);
        del.addActionListener(buttonListener);
    }

    public void addClearListener(ActionListener clearListener) {

        clearPol1.addActionListener(clearListener);
        clearPol2.addActionListener(clearListener);
        clearPolRez.addActionListener(clearListener);
    }

    public void addClearAllListener(ActionListener clearAllListener) {
        clearAll.addActionListener(clearAllListener);
    }

    public void addCalculateListener(ActionListener calculateListener) {
        calculate.addActionListener(calculateListener);
    }

    public void displayErrorMessage(String error) {
        JOptionPane.showMessageDialog(this, error);
    }

    public void setPolynomRezField(String result) {
        this.polynomRezField.setText(result);
    }
}
