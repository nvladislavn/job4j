package ru.job4j;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * CalculatorTest
 *
 * @author Vladislav Nechaev
 * @since 07.02.2019
 */
public class CalculatorTest {

    private Calculator calculator;

    @Before
    public void createCalculator() {
        this.calculator = new Calculator();
    }

    /**
     * tests diapason
     */
    @Test
    public void whenLinearFunctionThenLinearResults() {
        List<Double> actual = this.calculator.diapason(5, 8, value -> 2 * value + 1);
        List<Double> expected = Arrays.asList(11D, 13D, 15D);
        assertThat(actual, is(expected));
    }

    /**
     * tests diapason
     */
    @Test
    public void whenSquareFunctionThenSquareResults() {
        List<Double> actual = this.calculator.diapason(5, 8, value -> Math.pow(value, 2) + 1);
        List<Double> expected = Arrays.asList(26D, 37D, 50D);
        assertThat(actual, is(expected));
    }

    /**
     * tests diapason
     */
    @Test
    public void whenLogFunctionThenLogResults() {
        List<Double> actual = this.calculator.diapason(5, 8,
                                                        value -> new BigDecimal(Math.log(value) + 1)
                                                                .setScale(2, RoundingMode.HALF_UP).doubleValue());
        List<Double> expected = Arrays.asList(2.61, 2.79, 2.95);
        assertThat(actual, is(expected));
    }
}