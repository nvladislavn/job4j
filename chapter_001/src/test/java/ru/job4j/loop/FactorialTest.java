package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test
 *
 * @author Vladislav Nechaev
 * @version $Id$
 * @since 16/10/2018
 */
public class FactorialTest {

    /**
     * Test calc method when factorial for 5
     */
    @Test
    public void whenFactorialForFiveThenOneHundreedTwenty() {
        Factorial fact = new Factorial();
        assertThat(fact.calc(5), is(120));
    }

    /**
     * Test calc method when factorial for 0
     */
    @Test
    public void whenFactorialForZeroThenOne() {
        Factorial fact = new Factorial();
        assertThat(fact.calc(0), is(1));
    }
}