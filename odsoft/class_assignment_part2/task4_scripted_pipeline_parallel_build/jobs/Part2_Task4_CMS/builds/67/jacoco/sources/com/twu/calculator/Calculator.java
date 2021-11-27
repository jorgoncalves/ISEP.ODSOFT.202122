package com.twu.calculator;

public class Calculator {

    private double accumulator;

    public double doOperation(String operation, double operand) {
        switch (operation) {
            case "add":
                accumulator += operand;
                break;
            case "subtract":
                accumulator -= operand;
                break;
            case "multiply":
                accumulator *= operand;
                break;
            case "divide":
                accumulator /= operand;
                break;
            case "abs":
                accumulator = Math.abs(accumulator);
                break;
            case "third":
                accumulator = accumulator/3;
                break;   
            case "neg":
                accumulator = -accumulator;
                break;
            case "sqrt":
                accumulator = Math.sqrt(accumulator);
                break;
            case "sqr":
                accumulator = Math.pow(accumulator, 2);
                break;
            case "cube":
                accumulator = Math.pow(accumulator, 3);
                break;     
            case "cubert":
                accumulator = Math.cbrt(accumulator);
                break;
            case "factorial":
                if (accumulator <= 1) {
                    accumulator = 1;
                } else {
                    double initial = accumulator;
                    accumulator = 1;
                    while (initial > 1) {
                        accumulator *= initial;
                        initial--;
                    }
                }
                break;
            case "cancel":
                accumulator = 0;
                break;
            case "exit":
                System.exit(0);
        }
        return accumulator;
    }
}
