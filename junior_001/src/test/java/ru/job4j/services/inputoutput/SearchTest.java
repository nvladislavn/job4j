package ru.job4j.services.inputoutput;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
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

    private static final String SPR = File.separator;

    /**
     * listsShouldBeEqual
     * tests the files().
     */
    @Test
    public void listsShouldBeEqual() {
        var search = new Search();
        String testDir = String.format("%s%s%s%s", "./_Job4JTemp", SPR, "TestIO", SPR);
        String txtDir = String.format("%s%s%s", testDir, "222", SPR);
        String pdfDir = String.format("%s%s%s", txtDir, "333", SPR);
        List<File> files = List.of(
                new File(String.format("%s%s", testDir, "A1 - 1.docx")),
                new File(String.format("%s%s", testDir, "A1 - 2.docx")),
                new File(String.format("%s%s", testDir, "A1 - 3.docx")),
                new File(String.format("%s%s", txtDir, "TXT 1.txt")),
                new File(String.format("%s%s", txtDir, "TXT 2.txt")),
                new File(String.format("%s%s", txtDir, "TXT 3.txt")),
                new File(String.format("%s%s", pdfDir, "PDF1.pdf")),
                new File(String.format("%s%s", pdfDir, "PDF2.pdf")),
                new File(String.format("%s%s", pdfDir, "PDF3.pdf"))
        );
        if (!createFilesForTest(new File(pdfDir), files)) {
            fail("Failed to create files. Please try again.");
        }
        List<File> filter = search.files(testDir, List.of("docx", "pdf", "fb2", "txt"));

        assertThat(filter, is(files));
    }

    /**
     * createFilesForTest
     *
     * @param childDir - the descendent directory.
     * @param files - the list of files.
     * @return - true if creation was successful.
     */
    private boolean createFilesForTest(File childDir, List<File> files) {
        if (!childDir.exists()) {
            if (!childDir.mkdirs()) {
                return false;
            }
        }
        for (File file : files) {
            if (!file.exists()) {
                try {
                    if (!file.createNewFile()) {
                        return false;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }
}