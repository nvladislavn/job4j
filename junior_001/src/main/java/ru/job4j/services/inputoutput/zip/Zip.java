package ru.job4j.services.inputoutput.zip;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Zip
 *
 * @author Vladislav Nechaev
 * @since 28.06.2019
 */
public class Zip {

    /**
     * seekBy
     *
     * @param root - the source directory.
     * @param ext  - excluded extension.
     * @return - list of files to archive.
     */
    public List<File> seekBy(String root, String ext) {
        List<File> result = new ArrayList<>();
        result.add(new File(root));
        Queue<File> files = new LinkedList<>();
        files.add(new File(root));
        while (!files.isEmpty()) {
            File currentFile = files.poll();
            if (currentFile.isDirectory()) {
                files.addAll(Arrays.asList(currentFile.listFiles(file -> !file.getName().endsWith(ext))));
            }
            result.add(currentFile);
        }
        return result;
    }

    /**
     * pack
     *
     * @param sources - list of files to archive.
     * @param target  - the target file.
     */
    public void pack(List<File> sources, File target) {
        try (ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File file : sources) {
                if (!file.isDirectory()) {
                    out.putNextEntry(new ZipEntry(file.getPath()));
                    try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file))) {
                        out.write(in.readAllBytes());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
