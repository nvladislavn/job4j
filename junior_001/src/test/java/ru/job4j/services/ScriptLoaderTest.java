package ru.job4j.services;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * ScriptLoaderTest
 *
 * @author Vladislav Nechaev
 * @since 16.06.2019
 */
public class ScriptLoaderTest {

    @Test
    public void shouldReturn54321() {
        Map<Integer, List<Integer>> ds = new HashMap<>();
        ds.put(1, List.of(2, 3));
        ds.put(2, List.of(4));
        ds.put(3, List.of(4, 5));
        ds.put(4, List.of());
        ds.put(5, List.of());
        var loader = new ScriptLoader();
        assertThat(loader.load(ds, 1), is(List.of(5, 4, 3, 2, 1)));
    }

    @Test
    public void shouldReturn543() {
        Map<Integer, List<Integer>> ds = new HashMap<>();
        ds.put(1, List.of(2, 3));
        ds.put(2, List.of(4));
        ds.put(3, List.of(4, 5));
        ds.put(4, List.of());
        ds.put(5, List.of());
        var loader = new ScriptLoader();
        assertThat(loader.load(ds, 3), is(List.of(5, 4, 3)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenScriptIdIsLessZeroThenThrowIllegalArgumentException() {
        Map<Integer, List<Integer>> ds = new HashMap<>();
        ds.put(1, List.of(2, 3));
        ds.put(2, List.of(4));
        ds.put(3, List.of(4, 5));
        ds.put(4, List.of());
        ds.put(5, List.of());
        var loader = new ScriptLoader();
        loader.load(ds, -2);
    }
}