package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * TurnTest
 *
 * @author Vladislav Nechaev
 * @version $Id$
 * @since 18/10/2018
 */
public class TurnTest {

    /**
     * Test back method when odd number of elevents
     */
    @Test
    public void when12345Then54321() {
        Turn turn = new Turn();
        int[] result = turn.back(new int[]{1, 2, 3, 4, 5});
        assertThat(result, is(new int[]{5, 4, 3, 2, 1}));
    }

    /**
     * Test back methods when even number of elements
     */
    @Test
    public void when123456Then654321() {
        Turn turn = new Turn();
        int[] result = turn.back(new int[]{1, 2, 3, 4, 5, 6});
        assertThat(result, is(new int[]{6, 5, 4, 3, 2, 1}));
    }

    /**
     * Test back method when zero elements
     */
    @Test
    public void whenSize0() {
        Turn turn = new Turn();
        int[] result = turn.back(new int[]{});
        assertThat(result, is(new int[]{}));
    }
}