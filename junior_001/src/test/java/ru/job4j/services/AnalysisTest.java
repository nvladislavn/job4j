package ru.job4j.services;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * AnalysisTest
 *
 * @author Vladislav Nechaev
 * @since 24.05.2019
 */
public class AnalysisTest {

    @Test
    public void shouldReturn111() {
        var analysis = new Analysis();
        List<Analysis.User> previous = List.of(new Analysis.User(1, "John"),
                new Analysis.User(2, "Ivan"),
                new Analysis.User(3, "Katy"),
                new Analysis.User(4, "Anna"));
        List<Analysis.User> current = List.of(new Analysis.User(1, "Stan"),
                new Analysis.User(2, "Ivan"),
                new Analysis.User(3, "Katy"),
                new Analysis.User(5, "Barbara"));
        Analysis.Info info = analysis.diff(previous, current);
        assertThat(info.added, is(1));
        assertThat(info.changed, is(1));
        assertThat(info.deleted, is(1));
    }

    @Test
    public void whenFirstOfTheListsIsEmptyThenReturn400() {
        var analysis = new Analysis();
        List<Analysis.User> previous = new ArrayList<>();
        List<Analysis.User> current = List.of(new Analysis.User(1, "Stan"),
                new Analysis.User(2, "Ivan"),
                new Analysis.User(3, "Katy"),
                new Analysis.User(5, "Barbara"));
        Analysis.Info info = analysis.diff(previous, current);
        assertThat(info.added, is(4));
        assertThat(info.changed, is(0));
        assertThat(info.deleted, is(0));
    }

    @Test
    public void whenSecondOfTheListsIsEmptyThenReturn004() {
        var analysis = new Analysis();
        List<Analysis.User> previous = List.of(new Analysis.User(1, "John"),
                new Analysis.User(2, "Ivan"),
                new Analysis.User(3, "Katy"),
                new Analysis.User(4, "Anna"));
        List<Analysis.User> current = new ArrayList<>();
        Analysis.Info info = analysis.diff(previous, current);
        assertThat(info.added, is(0));
        assertThat(info.changed, is(0));
        assertThat(info.deleted, is(4));
    }

    @Test
    public void whenBothListsAreNullThenReturnNull() {
        var analysis = new Analysis();
        Analysis.Info info = analysis.diff(null, null);
        assertNull(info);
    }

    @Test
    public void whenFirstOfTheListsIsNullThenReturnNull() {
        var analysis = new Analysis();
        List<Analysis.User> current = List.of(new Analysis.User(1, "Stan"),
                new Analysis.User(2, "Ivan"),
                new Analysis.User(3, "Katy"),
                new Analysis.User(5, "Barbara"));
        Analysis.Info info = analysis.diff(null, current);
        assertNull(info);
    }

    @Test
    public void whenSecondOfTheListsIsNullThenReturnNull() {
        var analysis = new Analysis();
        List<Analysis.User> previous = List.of(new Analysis.User(1, "Stan"),
                new Analysis.User(2, "Ivan"),
                new Analysis.User(3, "Katy"),
                new Analysis.User(5, "Barbara"));
        Analysis.Info info = analysis.diff(previous, null);
        assertNull(info);
    }
}