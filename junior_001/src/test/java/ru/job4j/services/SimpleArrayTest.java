package ru.job4j.services;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleArrayTest {

    private SimpleArray<Integer> sa;
    Iterator<Integer> it;

    @Before
    public void createSimpleArray() {
        sa = new SimpleArray<>(5);
        sa.add(1);
        sa.add(2);
        sa.add(3);
        sa.add(4);
        sa.add(5);
        it = sa.iterator();
    }

    @Test
    public void when5TimesDoNextThen12345() {
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(5));
    }

    @Test
    public void shouldReturnsFalse() {
        it.next();
        it.next();
        it.next();
        it.next();
        it.next();
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void theCallSequence() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(5));
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void threeShouldBeNine() {
        sa.set(2, 9);
        assertThat(sa.get(2), is(9));
    }

    @Test
    public void shouldReturns1245Null() {
        sa.remove(2);
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(5));
    }
}