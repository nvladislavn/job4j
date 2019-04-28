package ru.job4j.services;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * SimpleQueueTest
 *
 * @author Vladislav Nechaev
 * @since 28.04.2019
 */
public class SimpleQueueTest {

    @Test
    public void shouldReturn123() {
        SimpleQueue<Integer> sQ = new SimpleQueue<>();
        sQ.push(1);
        sQ.push(2);
        sQ.push(3);
        assertThat(sQ.poll(), is(1));
        assertThat(sQ.poll(), is(2));
        assertThat(sQ.poll(), is(3));
    }

    @Test
    public void shouldReturn4() {
        SimpleQueue<Integer> sQ = new SimpleQueue<>();
        sQ.push(1);
        sQ.push(2);
        sQ.push(3);
        sQ.poll();
        sQ.poll();
        sQ.poll();
        sQ.push(4);
        assertThat(sQ.poll(), is(4));
    }

    @Test
    public void shouldReturn123Then4() {
        SimpleQueue<Integer> sQ = new SimpleQueue<>();
        sQ.push(1);
        sQ.push(2);
        sQ.push(3);
        assertThat(sQ.poll(), is(1));
        sQ.push(4);
        assertThat(sQ.poll(), is(2));
        assertThat(sQ.poll(), is(3));
        assertThat(sQ.poll(), is(4));
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldReturnNoSuchElementException() {
        SimpleQueue<Integer> sQ = new SimpleQueue<>();
        sQ.push(1);
        sQ.push(2);
        sQ.push(3);
        assertThat(sQ.poll(), is(1));
        assertThat(sQ.poll(), is(2));
        assertThat(sQ.poll(), is(3));
        sQ.poll();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNotPushThenReturnNoSuchElementException() {
        SimpleQueue<Integer> sQ = new SimpleQueue<>();
        sQ.poll();
    }
}