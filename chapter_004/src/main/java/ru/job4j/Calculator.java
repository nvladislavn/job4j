package ru.job4j;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Calculator
 *
 * @author Vladislav Nechaev
 * @since 06.02.2019
 */
public class Calculator {

    /**
     * diapason
     * Calculates the gives expression.
     *
     * @param start - start value.
     * @param end   - end value.
     * @param func  - an instance of Functional interface.
     * @return - result list.
     */
    public List<Double> diapason(int start, int end, Function<Double, Double> func) {
        List<Double> res = new ArrayList<>();
        for (int i = start; i < end; i++) {
            res.add(func.apply(new Double(i)));
        }
        return res;
    }
}
