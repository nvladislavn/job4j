package ru.job4j.services.inputoutput.search;

import java.io.File;

/**
 * FindArgs
 *
 * @author Vladislav Nechaev
 * @since 26.07.2019
 */
public class FindArgs {

    private String[] args;

    public FindArgs(String[] args) {
       if (args.length != 4) {
            throw new IllegalArgumentException("Invalid arguments.");
        }
        this.args = args;
    }

    /**
     * getSearchDirectory
     *
     * @return - a directory start search.
     * @throws IllegalArgumentException
     */
    public String getSearchDirectory() throws IllegalArgumentException {
        String dir = args[0];
        String exceptionMessage = "Please enter a valid search directory.";
        areValid(dir, exceptionMessage);
        if (!new File(dir).exists()) {
            throw new IllegalArgumentException(exceptionMessage);
        }
        return args[0];
    }

    /**
     * getObjectParameters
     *
     * @return - an search object.
     * @throws IllegalArgumentException
     */
    public String getObjectParameters() throws IllegalArgumentException {
        String obj = args[1];
        areValid(obj, "Please enter a valid search object.");
        return args[1];
    }

    /**
     * getSearchParameters
     *
     * @return - a search type.
     * @throws IllegalArgumentException
     */
    public SearchType getSearchParameters() throws IllegalArgumentException {
        String type = args[2];
        areValid(type, "Please enter a valid search type.");
        SearchType searchType = null;
        for (SearchType item : SearchType.values()) {
            if (item.getSearchType().equals(args[2])) {
                searchType = item;
                break;
            }
        }
        return searchType;
    }

    /**
     * getOutputPath
     *
     * @return - an output path for write found files.
     */
    public String getOutputPath() {
        String obj = args[3];
        areValid(obj, "Please enter a valid output path.");
        return args[3];
    }

    /**
     * areValid
     *
     * @param arg - an input argument.
     * @param message - an exception message.
     * @throws IllegalArgumentException
     */
    private  void areValid(String arg, String message) throws IllegalArgumentException {
        if (arg == null || arg.isEmpty()) {
            throw new IllegalArgumentException(String.format("Invalid arguments. %s", message));
        }
    }
}

/**
 * SearchType
 *
 * A search types.
 */
enum SearchType {

    MASK("-m"), FULLNAME("-f"), REGEX("-r");

    private String searchType;

    SearchType(String searchType) {
        this.searchType = searchType;
    }

    public String getSearchType() {
        return searchType;
    }
}
