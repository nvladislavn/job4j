package ru.job4j.templateGenerator;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class GeneratorTest {

    private static final String WRITER = "Jane Austen";
    private static final String BOOK = "Persuasion";
    private static final String TEMPLATE = "My favorite writer is {writer}, who wrote the book \"{book}\".";
    private Generator generator = new SimpleGenerator();
    private Map<String, String> keys = new HashMap<>();

    @Before
    public void prepareTest() {
        keys.put("writer", WRITER);
        keys.put("book", BOOK);
    }

    @Test
    public void shouldBeEquals() {
        String excepted = String.format("My favorite writer is %s, who wrote the book \"%s\".", WRITER, BOOK);
        String actual = generator.produce(TEMPLATE, keys);
        assertEquals(excepted, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenEmptyKeysThenIllegalArgumentException() {
        Map<String, String> empty = new HashMap<>();
        generator.produce(TEMPLATE, empty);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenTemplateStringIsEmptyThenIllegalArgumentException() {
        generator.produce("", keys);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenMapDoesNotHasCorrectKeysThenIllegalArgumentException() {
        keys.clear();
        keys.put("country", "UK");
        keys.put("currency", "GBP");
        generator.produce(TEMPLATE, keys);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenMapHasRedundantKeysThenIllegalArgumentException() {
        keys.put("country", "UK");
        keys.put("currency", "GBP");
        generator.produce(TEMPLATE, keys);
    }
}
