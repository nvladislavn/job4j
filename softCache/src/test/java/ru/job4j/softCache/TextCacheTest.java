package ru.job4j.softCache;

import org.junit.Test;

import java.io.IOException;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TextCacheTest {

    /**
     * tests getValue()
     *
     * @throws IOException
     */
    @Test
    public void shouldReturnTextFromDoc2() throws IOException {
        TextCache cache = new TextCache();
        String actual = cache.getValue("Doc2.txt");
        String expected = new StringJoiner(System.lineSeparator())
                .add("Text2 - 1")
                .add("Text2 - 2")
                .add("Text2 - 3")
                .toString();
        assertThat(actual, is(expected));
    }

    @Test (expected = IOException.class)
    public void whenTxtFileNotFoundThenThrowsException() throws IOException {
        TextCache cache = new TextCache();
        cache.getValue("Doc4.txt");
    }
}