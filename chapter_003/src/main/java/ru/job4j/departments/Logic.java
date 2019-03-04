package ru.job4j.departments;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Logic
 *
 * @author Vladislav Nechaev
 * @since 31.01.2019
 */
public class Logic {

    /**
     * sort
     * Sorts ascending.
     *
     * @param depArray - an departments id array.
     * @return - the sorted array of department id.
     */
    public String[] sort(String[] depArray) {
        Set<String> depSet = getId(depArray);
        String[] result = depSet.toArray(new String[depSet.size()]);
        Arrays.sort(result);
        return result;
    }

    /**
     * reversSort
     * sorts descending.
     *
     * @param depArray - an departments id array.
     * @return - the sorted array of department id.
     */
    public String[] reversSort(String[] depArray) {
        Set<String> depSet = getId(depArray);
        Comparator<String> stringComparator = (s1, s2) -> {
            int minLen = Math.min(s1.length(), s2.length());
            int res = -s1.substring(0, minLen).compareTo(s2.substring(0, minLen));
            return res != 0 ? res : Integer.compare(s1.length(), s2.length());
        };
       return depSet
                .stream()
                .sorted(stringComparator)
                .toArray(String[]::new);
    }

    /**
     * getId
     * Splits an id array.
     *
     * @param array - an departments id array.
     * @return - the Set of departments id.
     */
    private Set<String> getId(String[] array) {
        Set<String> depSet = new HashSet<>();
        for (String string : array) {
            String[] departments = string.split("/");
            String department = "";
            for (int i = 0; i < departments.length; i++) {
                department = department + (i == 0 ? "" : "/") + departments[i];
                depSet.add(department);
            }
        }
        return depSet;
    }
}
