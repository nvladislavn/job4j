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

    /**
     * Test the max method, when the first mumber is less than the second
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
}