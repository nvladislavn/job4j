package ru.job4j.services;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * DynamicListTest
 *
 * @author Vladislav Nechaev
 * @since 20.04.2019
 */
public class DynamicListTest {

    private DynamicList dl;
    private Iterator it;

    @Before
    public void createDynamicList() {
        dl = new DynamicList(3);
        dl.add(1);
        dl.add(2);
        dl.add(3);
        it = dl.iterator();
    }

    @Test
    public void shouldReturn3() {
        assertThat(dl.get(2), is(3));
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldReturnException() {
        assertThat(dl.get(3), is(4));
    }

    @Test
    public void shouldIncreasesArrayAndReturns4() {
        dl.add(4);
        assertThat(dl.get(3), is(4));
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
        dl.add(4);
        it.hasNext();
    }
}