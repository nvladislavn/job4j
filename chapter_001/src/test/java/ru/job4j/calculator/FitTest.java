package ru.job4j.calculator;

import org.junit.Test;

import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;

/**
 *Test
 *@author Vladislav Nechaev
 *@version $Id$
 *@since 14/10/2018
 */
public class FitTest {

    /**
     * Test manWeight
     */
    @Test
    public void manWeight() {
        Fit fit = new Fit();
        double weight = fit.manWeight(180);
        assertThat(weight, closeTo(92.0, 0.1));
    }

    /**
     * Test womanWeight
     */
    @Test
    public void womanWeight() {
        Fit fit = new Fit();
        double weight = fit.womanWeight(170);
        assertThat(weight, closeTo(69.0, 0.1));
    }

    /**
     * Test manHeight
     */
    @Test
    public void manHeight() {
        Fit fit = new Fit();
        assertThat(fit.manHeight(92.0), closeTo(180, 0.1));
    }

    /**
     * Test womanHeight
     */
    @Test
    public void womanHeight() {
        Fit fit = new Fit();
        assertThat(fit.womanHeight(69.0), closeTo(170, 0.1));
    }
}
