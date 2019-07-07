package ru.job4j.services;

import java.util.*;
import java.util.stream.Collectors;

/**
 * ScriptLoader
 *
 * @author Vladislav Nechaev
 * @since 16.06.2019
 */
public class ScriptLoader {

    /**
     * loadAnswers
     *
     * @param ds - the script map.
     * @param scriptId - the script to loadAnswers.
     * @return - script list.
     */
    public List<Integer> load(Map<Integer, List<Integer>> ds, Integer scriptId) {
        if (ds.isEmpty()) {
            return null;
        }
        if (scriptId < 0) {
            throw new IllegalArgumentException("Script id can not be negative.");
        }
        List<Integer> result = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(scriptId);
        while (!queue.isEmpty()) {
            Integer id = queue.poll();
            queue.addAll(ds.get(id));
            result.add(id);
        }
        Collections.reverse(result);
        result = result
                .stream()
                .distinct()
                .collect(Collectors.toList());
        return result;
    }
}
