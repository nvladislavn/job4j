package ru.job4j.drawing;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * SquareTest.
 */
public class SquareTest {

    /**
     * Tests draw.
     */
    @Test
    public void whenDrawSquare() {
        String expected = new StringBuilder("+++++++\n"
                                            + "+++++++\n"
                                            + "+++++++\n"
                                            + "+++++++\n"
                                            + "+++++++\n"
                                            + "+++++++\n"
                                            + "+++++++\n")
                                            .toString();
        assertThat(new Square().draw(), is(expected));
    }
}