package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test
 *
 * @author Vladislav Nechaev
 * @version $Id$
 * @since 18/10/2018
 */
public class SquareTest {

    /**
     * Test when array size = 3
     */
    @Test
    public void whenBound3Then149() {
        Square square = new Square();
        assertThat(square.calculate(3), is(new int[]{1, 4, 9}));
    }

    /**
     * Test when array size = 0
     */
    @Test
    public void whenBound0Then0() {
        Square square = new Square();
        assertThat(square.calculate(0), is(new int[]{}));
    }

    /**
     * Test when array size = 5
     */
    @Test
    public void whenBound5Then1491625() {
        Square square = new Square();
        assertThat(square.calculate(5), is(new int[]{1, 4, 9, 16, 25}));
    }
}