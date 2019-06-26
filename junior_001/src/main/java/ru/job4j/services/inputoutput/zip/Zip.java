package ru.job4j.services.inputoutput.zip;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * zip
 *
 * @author Vladislav Nechaev
 * @since 27.06.2019
 */
public class Zip {
    public void pack(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Zip().pack(new File("C:\\projects\\job4j\\junior_001\\TestIO"),
                        new File("C:\\projects\\job4j\\junior_001\\TestIO.zip"));
//                        new File(System.getProperty("java.io.tmpdir") + "_Job4JTemp\\TestIO\\A1 - 1.zip"));
//        new zip().pack(new File("./junior_001/pom.xml"), new File("./junior_001/pom.zip"));
    }
}
