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
     * calcLin
     * Calculates linear expression for a range of values.
     *
     * @param start - start value.
     * @param end   - end value.
     * @return - result list.
     */
    public List<Double> calcLin(int start, int end) {
        return this.diapason(start, end, value -> 2 * value + 1);
    }

    /**
     * calcSquare
     * Calculates square expression for a range of values.
     *
     * @param start - start value.
     * @param end   - end value.
     * @return - result list.
     */
    public List<Double> calcSquare(int start, int end) {
        return this.diapason(start, end, value -> Math.pow(value, 2) + 1);
    }

    /**
     * calcLog
     * Calculates logarithmic expression for a range of values.
     *
     * @param start - start value.
     * @param end   - end value.
     * @return - result list.
     */
    public List<Double> calcLog(int start, int end) {
        return this.diapason(start, end, value -> new BigDecimal(Math.log(value) + 1)
                                                        .setScale(2, RoundingMode.HALF_UP).doubleValue());
    }

    /**
     * diapason
     * Calculates the gives expression.
     *
     * @param start - start value.
     * @param end   - end value.
     * @param func  - an instance of Functional interface.
     * @return - result list.
     */
    private List<Double> diapason(int start, int end, Function<Double, Double> func) {
        List<Double> res = new ArrayList<>();
        for (int i = start; i < end; i++) {
            res.add(func.apply(new Double(i)));
        }
        return res;
    }
}
