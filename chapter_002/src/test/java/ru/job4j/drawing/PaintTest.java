package ru.job4j.drawing;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * PaintTest
 */
public class PaintTest {

    private PrintStream stdOut;
    private ByteArrayOutputStream baos;

    @Before
    public void loadInput() {
        stdOut = System.out;
        baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
    }

    @After
    public void backOut() {
        System.setOut(stdOut);
    }

    /**
     * Tests draw when draws a square.
     */
    @Test
    public void whenDrawSquare() {
        new Paint().draw(new Square());
        String expected = new StringBuilder("+++++++\n"
                                            + "+++++++\n"
                                            + "+++++++\n"
                                            + "+++++++\n"
                                            + "+++++++\n"
                                            + "+++++++\n"
                                            + "+++++++\n")
                                            .toString();
        assertThat(baos.toString(), is(expected));
    }

    /**
     * Tests draw when draws a triangle.
     */
    @Test
    public void whenDrawTriangle() {
        new Paint().draw(new Triangle());
        String expected = "   +   \n  +++  \n +++++ \n+++++++\n";
        assertThat(baos.toString(), is(expected));
    }
}