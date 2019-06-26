package ru.job4j.services.inputoutput;

import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * SearchTest
 *
 * @author Vladislav Nechaev
 * @since 26.06.2019
 */
public class SearchTest {

    /**
     * listsShouldBeEqual
     * tests the files().
     */
    @Test
    public void listsShouldBeEqual() {
        var search = new Search();
        List<File> filter = search.files(System.getProperty("java.io.tmpdir") + "_Job4JTemp\\TestIO",
                                                                                List.of("docx", "pdf", "fb2", "txt"));
        List<File> expected = List.of(
                new File(System.getProperty("java.io.tmpdir") + "_Job4JTemp\\TestIO\\A1 - 1.docx"),
                new File(System.getProperty("java.io.tmpdir") + "_Job4JTemp\\TestIO\\A1 - 2.docx"),
                new File(System.getProperty("java.io.tmpdir") + "_Job4JTemp\\TestIO\\A1 - 3.docx"),
                new File(System.getProperty("java.io.tmpdir") + "_Job4JTemp\\TestIO\\222\\TXT 1.txt"),
                new File(System.getProperty("java.io.tmpdir") + "_Job4JTemp\\TestIO\\222\\TXT 2.txt"),
                new File(System.getProperty("java.io.tmpdir") + "_Job4JTemp\\TestIO\\222\\TXT 3.txt"),
                new File(System.getProperty("java.io.tmpdir") + "_Job4JTemp\\TestIO\\222\\333\\PDF1.pdf"),
                new File(System.getProperty("java.io.tmpdir") + "_Job4JTemp\\TestIO\\222\\333\\PDF2.pdf"),
                new File(System.getProperty("java.io.tmpdir") + "_Job4JTemp\\TestIO\\222\\333\\PDF3.pdf"));
        assertThat(filter, is(expected));
    }
}