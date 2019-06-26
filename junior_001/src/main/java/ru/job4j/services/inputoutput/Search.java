package ru.job4j.services.inputoutput;

import java.io.File;
import java.util.*;

/**
 * Search
 *
 * @author Vladislav Nechaev
 * @since 25.06.2019
 */
public class Search {

    /**
     * files
     *
     * @param parent - the path to the parent directory.
     * @param exts   - the list of files extensions.
     * @return - the list of files with the specified extensions from exts.
     */
    public List<File> files(String parent, List<String> exts) {
        File headDir = new File(parent);
        List<File> filterFiles = new ArrayList<>();
        Queue<File> files = new LinkedList<>();
        files.add(headDir);
        while (!files.isEmpty()) {
            File currentFile = files.poll();
            if (currentFile.isDirectory()) {
                files.addAll(Arrays.asList(currentFile.listFiles()));
            } else if (exts.contains(getExtension(currentFile))) {
                filterFiles.add(currentFile);
            }
        }
        return filterFiles;
    }

    /**
     * getExtension
     *
     * @param file - the specified file.
     * @return - the extension of the specified file.
     */
    private String getExtension(File file) {
        String name = file.getName();
        return name.substring(name.lastIndexOf(".") + 1);
    }
}
