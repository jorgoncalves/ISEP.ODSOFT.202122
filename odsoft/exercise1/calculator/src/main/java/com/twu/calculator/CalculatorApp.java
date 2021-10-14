package com.twu.calculator;

import java.util.Scanner;

public class CalculatorApp {

    private Calculator calculator;

    public CalculatorApp(Calculator calculator) {
        this.calculator = calculator;
    }

    public void start(boolean runForever) {
        Scanner inputReader = new Scanner(System.in);
        do {
            System.out.print("> ");
            String userInput = inputReader.nextLine();
            if (isOperationNeedExternalOperator(userInput))
                System.out.println(calculator.doOperation(getOperation(userInput), getOperand(userInput)));
            else
                System.out.println(calculator.doOperation(getOperation(userInput), 0));
        } while (runForever);
    }

    private boolean isOperationNeedExternalOperator(String userInput) {
        return (userInput.split(" ").length == 2) ? true : false;
    }

    private String getOperation(String userInput) {
        return userInput.split(" ")[0];
    }

    private double getOperand(String userInput) {
        return Double.parseDouble(userInput.split(" ")[1]);
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        CalculatorApp calculatorApp = new CalculatorApp(calculator);
        calculatorApp.start(true);
    }
}
