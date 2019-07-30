package ru.job4j.services.inputoutput.search;

import java.io.*;
import java.util.*;
import java.util.function.BiFunction;
import java.util.regex.Pattern;

/**
 * Finder
 *
 * @author Vladislav Nechaev
 * @since 26.07.2019
 */
public class Finder {

    private File mainDirectory;
    private SearchType searchType;
    private String selection;
    private File outputFile;
    private FilterHandler filterHandler;

    public Finder(File mainDirectory, String selection, SearchType searchType, File outputFile, FilterHandler filterHandler) {
        this.mainDirectory = mainDirectory;
        this.searchType = searchType;
        this.selection = selection;
        this.outputFile = outputFile;
        this.filterHandler = filterHandler;
    }

    /**
     * find
     *
     * @return list of found files.
     */
    public List<File> find() {
        File currentFile = null;
        List<File> found = new ArrayList<>();
        Queue<File> files = new LinkedList<>();
        files.add(mainDirectory);
        while (!files.isEmpty()) {
            currentFile = files.poll();
            found.addAll(
                    Arrays.asList(
                            Objects.requireNonNull(
                                    currentFile.listFiles(
                                            file -> filterHandler.getHandler(searchType).apply(file, selection)
                                    )
                            )
                    )
            );
            files.addAll(Arrays.asList(
                    currentFile.listFiles(File::isDirectory)
                    )
            );
        }
        return found;
    }

    /**
     * writeFoundFiles
     *
     * @param files - list of files to be write to a file on disk.
     */
    private void writeFoundFiles(List<File> files) {
        if (files == null) {
            return;
        }
        if (outputFile.exists()) {
            outputFile.delete();
        }
        try (PrintWriter out = new PrintWriter(new FileWriter(outputFile))) {
            files
                    .stream()
                    .forEach(
                            file -> out.println(file.toString())
                    );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        FindArgs findArgs = new FindArgs(args);
        Map<SearchType, BiFunction<File, String, Boolean>> handlers = new HashMap<>();
        handlers.put(SearchType.MASK, (file, selection) -> file.getName().endsWith(selection));
        handlers.put(SearchType.FULLNAME, (file, selection) -> file.equals(new File(selection)));
        handlers.put(SearchType.REGEX, (file, selection) -> Pattern.matches(selection, file.getPath()));
        FilterHandler filterHandler = new FilterHandler(handlers);
        Finder finder = new Finder(
                new File(findArgs.getSearchDirectory()), findArgs.getObjectParameters(),
                findArgs.getSearchParameters(), new File(findArgs.getOutputPath()), filterHandler
        );
        List<File> foundFiles = finder.find();
        finder.writeFoundFiles(foundFiles);
    }
}
