package ru.job4j.services;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * SimpleSetTest
 *
 * @author Vladislav Nechaev
 * @since 27.04.2019
 */
public class SimpleSetTest {

    @Test
    public void shouldReturn123() {
        SimpleSet<Integer> sSet = new SimpleSet<>(5);
        sSet.add(1);
        sSet.add(2);
        sSet.add(3);
        Iterator<Integer> it = sSet.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldReturnNoSuchElementException() {
        SimpleSet<Integer> sSet = new SimpleSet<>(5);
        sSet.add(1);
        sSet.add(2);
        sSet.add(3);
        sSet.add(3);
        Iterator<Integer> it = sSet.iterator();
        it.next();
        it.next();
        it.next();
        it.next();
    }

    @Test
    public void shouldReturnFalse() {
        SimpleSet<Integer> sSet = new SimpleSet<>(5);
        sSet.add(1);
        sSet.add(2);
        sSet.add(3);
        sSet.add(3);
        Iterator<Integer> it = sSet.iterator();
        it.next();
        it.next();
        it.next();
        assertFalse(it.hasNext());
    }

    @Test
    public void shouldReturn1Null3() {
        SimpleSet<Integer> sSet = new SimpleSet<>(5);
        sSet.add(1);
        sSet.add(null);
        sSet.add(3);
        Iterator<Integer> it = sSet.iterator();
        assertThat(it.next(), is(1));
        assertNull(it.next());
        assertThat(it.next(), is(3));
    }
}