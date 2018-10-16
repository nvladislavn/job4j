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
public class BoardTest {

    /**
     * Test paint method. Board 3x3
     */
    @Test
    public void when3x3() {
        Board board = new Board();
        String result = board.paint(3, 3);
        String ln = System.lineSeparator();
        assertThat(result, is(String.format("X X%s X %sX X%s", ln, ln, ln)));
    }

    /**
     * Test paint method. Board 5x4
     */
    @Test
    public void when5x4() {
        Board board = new Board();
        String result = board.paint(5, 4);
        String ln = System.lineSeparator();
        assertThat(result, is(String.format("X X X%s X X %sX X X%s X X %s", ln, ln, ln, ln)));
    }

}