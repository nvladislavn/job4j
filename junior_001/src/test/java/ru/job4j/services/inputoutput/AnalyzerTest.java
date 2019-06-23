package ru.job4j.services.inputoutput;

import org.junit.Test;

import java.io.*;
import java.util.stream.Collectors;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * AnalyzerTest
 *
 * @author Vladislav Nechaev
 * @since 23.06.2019
 */
public class AnalyzerTest {

    @Test
    public void shouldBeTheSameFiles() {
        String pathIn = "server.log";
        String pathOut = "unavailable.csv";
        try (BufferedWriter out = new BufferedWriter(new FileWriter(pathIn))) {
            out.write("200 10:56:01" + System.lineSeparator());
            out.write("500 10:57:01" + System.lineSeparator());
            out.write("400 10:58:01" + System.lineSeparator());
            out.write("200 10:59:01" + System.lineSeparator());
            out.write("500 11:01:02" + System.lineSeparator());
            out.write("200 11:02:02" + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
        var an = new Analyzer();
        an.unavailable(pathIn, pathOut);
        String actual = null;
        try (BufferedReader in = new BufferedReader(new FileReader(pathOut))) {
            actual = in
                    .lines()
                    .collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat(actual, is("10:57:01;10:59:01"
                                + System.lineSeparator()
                                + "11:01:02;11:02:02"));
    }
}