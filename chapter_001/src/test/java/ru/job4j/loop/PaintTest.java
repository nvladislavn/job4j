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
     * Test paint method
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