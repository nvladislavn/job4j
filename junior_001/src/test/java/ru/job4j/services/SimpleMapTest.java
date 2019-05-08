package ru.job4j.services;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * SimpleMapTest
 *
 * @author Vladislav Nechaev
 * @since 05.05.2019
 */
public class SimpleMapTest {

    @Test
    public void shouldReturnTrue() {
        var sm = new SimpleMap<>();
        sm.insert(2, "Two");
        assertTrue(sm.iterator().hasNext());
        assertTrue(sm.iterator().hasNext());
    }

    @Test
    public void whenNotEntriesThenReturnFalse() {
        var sm = new SimpleMap<>();
        assertFalse(sm.iterator().hasNext());
        assertFalse(sm.iterator().hasNext());
    }

    @Test
    public void shouldReturn512() {
        var sm = new SimpleMap<>();
        sm.insert(1, "One");
        sm.insert(2, "Two");
        sm.insert(5, "Five");
        assertThat(sm.get(5), is("Five"));
        assertThat(sm.get(1), is("One"));
        assertThat(sm.get(2), is("Two"));
    }

    @Test
    public void shouldReturnSize2() {
        var sm = new SimpleMap<>();
        sm.insert(1, "One");
        sm.insert(2, "Two");
        sm.insert(5, "Five");
        assertThat(sm.get(5), is("Five"));
        assertThat(sm.get(1), is("One"));
        assertThat(sm.get(2), is("Two"));
        assertTrue(sm.delete(1));
        assertThat(sm.size(), is(2));
        assertNull(sm.get(1));
    }

    @Test
    public void whenS17IsInsertedWithDifferentValueThenReturnS172() {
        var sm = new SimpleMap<>();
        sm.insert("s1", "s-1");
        sm.insert("s2", "s-2");
        sm.insert("s3", "s-3");
        sm.insert("s4", "s-4");
        sm.insert("s5", "s-5");
        sm.insert("s6", "s-6");
        sm.insert("s7", "s-7");
        sm.insert("s8", "s-8");
        sm.insert("s9", "s-9");
        sm.insert("s10", "s-10");
        sm.insert("s11", "s-11");
        sm.insert("s12", "s-12");
        sm.insert("s13", "s-13");
        sm.insert("s14", "s-14");
        sm.insert("s15", "s-15");
        sm.insert("s16", "s-16");
        sm.insert("s17", "s-17");
        assertThat(sm.get("s17"), is("s-17"));
        sm.insert("s17", "s-17-2");
        assertThat(sm.get("s17"), is("s-17-2"));
    }
}