package ru.job4j.services;

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

    @Test
    public void shouldReturn3() {
        var dl = new DynamicList<>(3);
        dl.add(1);
        dl.add(2);
        dl.add(3);
        assertThat(dl.get(2), is(3));
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldReturnException() {
        var dl = new DynamicList<>(3);
        dl.add(1);
        dl.add(2);
        dl.add(3);
        assertThat(dl.get(3), is(4));
    }

    @Test
    public void shouldIncreasesArrayAndReturns4() {
        var dl = new DynamicList<>(3);
        dl.add(1);
        dl.add(2);
        dl.add(3);
        dl.add(4);
        assertThat(dl.get(3), is(4));
    }

    @Test
    public void shouldReturnFalse() {
        var dl = new DynamicList<>(3);
        dl.add(1);
        dl.add(2);
        dl.add(3);
        Iterator it = dl.iterator();
        it.next();
        it.next();
        it.next();
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void shouldReturnTrueAnd2() {
        var dl = new DynamicList<>(3);
        dl.add(1);
        dl.add(2);
        dl.add(3);
        Iterator it = dl.iterator();
        assertThat(it.hasNext(), is(true));
        it.next();
        assertThat(it.next(), is(2));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void shouldReturnConcurrentModificationException() {
        var dl = new DynamicList<>(3);
        dl.add(1);
        dl.add(2);
        dl.add(3);
        Iterator it = dl.iterator();
        dl.add(4);
        it.hasNext();
    }
}