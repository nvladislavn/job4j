package ru.job4j.max;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test
 *
 * @author Vladislav Nechaev
 * @version $Id$
 * @since 15/10/2018
 */
public class MaxTest {

    //Maximum of two numbers

    /**
     * Test the max method, when the first number is less than the second
     */
    @Test
    public void whenFirstLessThanSecond() {
        Max maxim = new Max();
        int result = maxim.max(1, 2);
        assertThat(result, is(2));
    }

    /**
     * Test the max method, when the first number is greater than the second
     */
    @Test
    public void whenSecondLessThanFirst() {
        Max maxim = new Max();
        int result = maxim.max(2, 1);
        assertThat(result, is(2));
    }

    /**
     * Test the max method, when the first number is equal to the second
     */
    @Test
    public void whenFirstEqualSecond() {
        Max maxim = new Max();
        int result = maxim.max(1, 1);
        assertThat(result, is(1));
    }

    //Maximum of three numbers

    /**
     * Test the max method, when the first number is maximum
     */
    @Test
    public void whenTheMaximumIsTheFirst() {
        Max max = new Max();
        int result = max.max(3, 2, 1);
        assertThat(result, is(3));
    }

    /**
     * Test the max method, when the second number is maximum
     */
    @Test
    public void whenTheMaximumIsTheSecond() {
        Max max = new Max();
        int result = max.max(2, 3, 1);
        assertThat(result, is(3));
    }

    /**
     * Test the max method, when the third number is maximum
     */
    @Test
    public void whenTheMaximumIsTheThird() {
        Max max = new Max();
        int result = max.max(2, 1, 3);
        assertThat(result, is(3));
    }

    /**
     * Test the max method, when three numbers are equal
     */
    @Test
    public void whenThreeEqualNumbers() {
        Max max = new Max();
        int result = max.max(1, 1, 1);
        assertThat(result, is(1));
    }
}