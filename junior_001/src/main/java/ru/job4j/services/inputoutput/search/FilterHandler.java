package ru.job4j.services.inputoutput.search;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * FilterHandler
 *
 * @author Vladislav Nechaev
 * @since 30.07.2019
 */
public class FilterHandler {

    private Map<SearchType, BiFunction<File, String, Boolean>> dispatcher;

    public FilterHandler() {
        this.dispatcher = new HashMap<>();
    }

    public FilterHandler(Map<SearchType, BiFunction<File, String, Boolean>> dispatcher) {
        this.dispatcher = dispatcher;
    }

    /**
     * getHandler
     *
     * @param searchType - a search type (SearchType)
     * @return - a handler for file filter (FileFilter)
     */
    public BiFunction<File, String, Boolean>  getHandler(SearchType searchType) {
        BiFunction<File, String, Boolean> handler = null;
        if (dispatcher.containsKey(searchType)) {
            handler = dispatcher.get(searchType);
        }
        return handler;
    }
}
