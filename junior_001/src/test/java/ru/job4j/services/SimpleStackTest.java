package ru.job4j.services;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * SimpleStackTest
 *
 * @author Vladislav Nechaev
 * @since 21.04.2019
 */
public class SimpleStackTest {

    private SimpleStack simpleStack;
    private Iterator it;

    @Before
    public void createSimpleStack() {
        simpleStack = new SimpleStack();
        simpleStack.push(1);
        simpleStack.push(2);
        simpleStack.push(3);
        it = simpleStack.iterator();
    }

    @Test
    public void shouldReturn3And4() {
        assertThat(simpleStack.poll(), is(3));
        simpleStack.push(4);
        assertThat(simpleStack.poll(), is(4));
    }

    @Test
    public void shouldReturn321() {
        assertThat(simpleStack.poll(), is(3));
        assertThat(simpleStack.poll(), is(2));
        assertThat(simpleStack.poll(), is(1));
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldReturnException() {
        simpleStack.poll();
        simpleStack.poll();
        simpleStack.poll();
        assertThat(simpleStack.poll(), is(4));
    }

    @Test
    public void shouldReturnFalse() {
        it.next();
        it.next();
        it.next();
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void shouldReturnTrueAnd2() {
        assertThat(it.hasNext(), is(true));
        it.next();
        assertThat(it.next(), is(2));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void shouldReturnConcurrentModificationException() {
        simpleStack.push(4);
        it.hasNext();
    }
}