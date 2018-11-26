package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * ValidateInputTest.
 *
 * @author Vladislav Nechaev
 * @version $Id$
 * @since 16.11.2018
 */
public class ValidateInputTest {

    PrintStream out = System.out;
    ByteArrayOutputStream baos = new ByteArrayOutputStream();

    @Before
    public void loadBaos() {
        loadSystemOut(new PrintStream(this.baos));
    }

    @After
    public void loadOut() {
        loadSystemOut(this.out);
    }

    private void loadSystemOut(PrintStream ps) {
        System.setOut(ps);
    }

    /**
     * Tests ask.
     */
    @Test
    public void whenInvalidInput() {
        Input input = new ValidateInput(new StubInput(new String[] {"invalid", "1"}));
        input.ask("One", new int[] {1});
        assertThat(this.baos.toString(), is("Please enter validate data again." + System.lineSeparator()));
    }
}