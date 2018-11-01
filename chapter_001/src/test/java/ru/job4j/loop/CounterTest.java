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
public class CounterTest {

    /**
     * Test add method
     */
    @Test
    public void whenStartIsOneAndFinishIsTenThenThirty() {
        Counter counter = new Counter();
        int result = counter.add(1, 10);
        assertThat(result, is(30));
    }
}