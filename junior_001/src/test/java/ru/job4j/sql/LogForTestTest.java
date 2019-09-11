package ru.job4j.sql;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class LogForTestTest {

    @Test
    public void whenLevelErrorThanTestError() {
        PrintStream ps = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        LogForTest.main(new String[]{""});
        String except = "Test: error" + System.lineSeparator();
        LogForTest.main(new String[0]);
        String actual = baos.toString();
        assertThat(actual.substring(actual.length() - 13), is(except));
        System.setOut(ps);
    }
}