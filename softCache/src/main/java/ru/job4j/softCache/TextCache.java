package ru.job4j.softCache;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.StringJoiner;

/**
 * TextCache
 *
 * @author Vladislav Nechaev
 * @since 06.05.2020
 */
public class TextCache implements ICache<String, String> {

    private static final String TEMP_DIR = System.getProperty("java.io.tmpdir");
    private HashMap<String, SoftReference<String>> cacheMap = new HashMap<>();

    @Override
    public void add(String fileName, String text) {
        cacheMap.put(fileName, new SoftReference<>(text));
    }

    @Override
    public String getValue(String fileName) throws IOException {
        String text = null;
        SoftReference<String> ref = cacheMap.get(fileName);
        if (ref == null || ref.get() == null) {
            text = readText(fileName);
            this.add(fileName, text);
        } else {
            text = ref.get();
        }
        return text;
    }

    /**
     * getText
     *
     * @param fileName - the file name to search.
     * @return - found text.
     * @throws IOException
     */
    private String readText(String fileName) throws IOException {
        Path path = Paths.get(String.format("%s\\_Job4JTemp\\%s", TEMP_DIR, fileName));
        String text;
        if (Files.exists(path) && !Files.isDirectory(path, LinkOption.NOFOLLOW_LINKS)) {
            StringJoiner joiner = new StringJoiner(System.lineSeparator());
            Files.readAllLines(path)
                    .forEach(joiner::add);
            text = joiner.toString();
        } else {
            throw new FileNotFoundException(String.format("File with name %s not found", fileName));
        }
        return text;
    }
}