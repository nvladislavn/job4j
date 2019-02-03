package ru.job4j.departments;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

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
        String[] result = depSet.toArray(new String[depSet.size()]);
        Comparator<String> decreaseComparator = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                int length1 = s1.length();
                int length2 = s2.length();
                int minLength = Math.min(length1, length2);
                for (int i = 0; i < minLength; i++) {
                    char c1 = s1.charAt(i);
                    char c2 = s2.charAt(i);
                    if (c1 != c2) {
                        return c2 - c1;
                    }
                }
                if (length1 != length2) {
                    return length1 - length2;
                }
                return 0;
            }
        };
        Arrays.sort(result, decreaseComparator);
        return result;
    }

    /**
     * getId
     * Splits an id array.
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
