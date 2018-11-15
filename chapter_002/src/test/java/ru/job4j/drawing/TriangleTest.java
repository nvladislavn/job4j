package ru.job4j.drawing;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * TriangleTest.
 */
public class TriangleTest {

    /**
     * Tests draw.
     */
    @Test
    public void draw() {
        String expected = new String("   +   \n  +++  \n +++++ \n+++++++\n");
        assertThat(new Triangle().draw(), is(expected));
    }
}