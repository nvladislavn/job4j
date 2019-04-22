package ru.job4j.services;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * SimpleQueueTest
 *
 * @author Vladislav Nechaev
 * @since 22.04.2019
 */
public class SimpleQueueTest {

    private SimpleQueue sq;

    @Before
    public void createSimpleStack() {
        sq = new SimpleQueue();
        sq.push(1);
        sq.push(2);
        sq.push(3);
    }

    @Test
    public void shouldReturn12345() {
        assertThat(sq.poll(), is(1));
        assertThat(sq.poll(), is(2));
        assertThat(sq.poll(), is(3));
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldReturnNoSuchElementException() {
        assertThat(sq.poll(), is(1));
        assertThat(sq.poll(), is(2));
        assertThat(sq.poll(), is(3));
        sq.poll();
    }
}