package com.twu.calculator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CalculatorAppTest {
    ByteArrayInputStream inputContent = new ByteArrayInputStream("add 10\n".getBytes());
    ByteArrayOutputStream outputContent = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        System.setIn(inputContent);
        System.setOut(new PrintStream(outputContent));
    }

    @Test
    public void shouldStartTheCalculator() {
        Calculator calculator = new Calculator();
        CalculatorApp calculatorApp = new CalculatorApp(calculator);
        calculatorApp.start(false);

        String actualResult = outputContent.toString();

        assertThat(actualResult, is("> 10.0" + System.getProperty("line.separator")));
    }

    @Test
    public void severalOperationsIntegrationTest() {
        Calculator calculator = new Calculator();
        calculator.doOperation("add", 5);
        calculator.doOperation("subtract", 2);
        calculator.doOperation("multiply", 5);
        calculator.doOperation("divide", 3);
        double result = calculator.doOperation("factorial", 0);

        assertThat(result, is(120.0));
    }

    @Test
    public void thirdIntegrationTest() {
        Calculator calculator = new Calculator();
        calculator.doOperation("add", 7);
        calculator.doOperation("subtract", 1);
        calculator.doOperation("multiply", 1);
        calculator.doOperation("divide", 1);
        double result = calculator.doOperation("third", 0);

        assertThat(result, is(2.0));
    }

    @After
    public void tearDown() {
        System.setIn(System.in);
        System.setOut(null);
    }
}
