package ru.job4j.services;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * SimpleArrayListTest
 *
 * @author Vladislav Nechaev
 * @since 16.04.2019
 */
public class SimpleArrayListTest {

    private SimpleArrayList<Integer> list;

    @Before
    public void beforeTest() {
        list = new SimpleArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
    }

    /**
     * tests get
     */
    @Test
    public void whenAddThreeElementsThenUseGetOneResultTwo() {
        assertThat(list.get(1), is(2));
    }

    /**
     * tests size
     */
    @Test
    public void whenAddThreeElementsThenUseGetSizeResultThree() {
        assertThat(list.getSize(), is(3));
    }

    /**
     * tests delete
     */
    @Test
    public void shouldReturn21() {
        list.delete();
        assertThat(list.get(0), is(2));
        assertThat(list.get(1), is(1));
    }

    /**
     * tests delete
     */
    @Test
    public void whenDeleteThenSize2() {
        assertThat(list.delete(), is(3));
        assertThat(list.getSize(), is(2));
    }
}