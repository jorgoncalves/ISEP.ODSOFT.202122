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

        assertThat(actualResult, is("> 10.0"+System.getProperty("line.separator")));
    }

    @After
    public void tearDown() {
        System.setIn(System.in);
        System.setOut(null);
    }
}
