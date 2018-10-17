package ru.job4j.loop;

import org.junit.Test;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test
 *
 * @author Vladislav Nechaev
 * @version $Id$
 * @since 17/10/2018
 */
public class PaintTest {

    /**
     * Test whenPyramid4Right
     */
    @Test
    public void whenPyramid4Right() {
        Paint paint = new Paint();
        String rst = paint.rightTrl(4);
        System.out.println(rst);
        assertThat(rst, is(new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                                .add("^   ")
                                .add("^^  ")
                                .add("^^^ ")
                                .add("^^^^")
                                .toString()));
    }

    /**
     * Test whenPyramid4Left
     */
    @Test
    public void whenPyramid4Left() {
        Paint paint = new Paint();
        String rst = paint.leftTrl(4);
        System.out.println(rst);
        assertThat(rst, is(new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                                .add("   ^")
                                .add("  ^^")
                                .add(" ^^^")
                                .add("^^^^")
                                .toString()));
    }

    /**
     * Test pyramid method
     */
    @Test
    public void whenPyramidOf4Rows() {
        Paint paint = new Paint();
        assertThat(paint.pyramid(4), is(new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                                                        .add("   ^   ")
                                                        .add("  ^^^  ")
                                                        .add(" ^^^^^ ")
                                                        .add("^^^^^^^")
                                                        .toString()));
    }
}