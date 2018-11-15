package ru.job4j.drawing;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * PaintTest
 */
public class PaintTest {

    /**
     * Tests draw when draws a square.
     */
    @Test
    public void whenDrawSquare() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream stdOut = System.out;
        System.setOut(new PrintStream(baos));
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
        System.setOut(stdOut);
    }

    /**
     * Tests draw when draws a triangle.
     */
    @Test
    public void whenDrawTriangle() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream stdOut = System.out;
        System.setOut(new PrintStream(baos));
        new Paint().draw(new Triangle());
        String expected = "   +   \n  +++  \n +++++ \n+++++++\n";
        assertThat(baos.toString(), is(expected));
        System.setOut(stdOut);
    }
}