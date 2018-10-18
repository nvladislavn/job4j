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
public class FindLoopTest {

    /**
     * Test indexOf method
     */
    @Test
    public void when4Then2() {
        FindLoop fl = new FindLoop();
        int result = fl.indexOf(new int[]{7, 4, 5, 10, 8}, 4);
        assertThat(result, is(1));
    }

    /**
     * Test indexOf method
     */
    @Test
    public void when4ThenMinus1() {
        FindLoop fl = new FindLoop();
        int result = fl.indexOf(new int[]{7, 15, 5, 10, 8}, 4);
        assertThat(result, is(-1));
    }

    /**
     * Test indexOf method
     */
    @Test
    public void when5Then3ButNotThe7() {
        FindLoop fl = new FindLoop();
        int result = fl.indexOf(new int[]{7, 15, 5, 10, 8, 9, 5}, 5);
        assertThat(result, is(2));
    }
}