package ru.job4j.array;

import java.util.Arrays;

/**
 * ArrayDuplicate
 *
 * @author Vladislav Nechaev
 * @version $Id$
 * @since 22/10/2018
 */
public class ArrayDuplicate {

    /**
     * remove method
     * @param array - String array
     * @return array without duplicates
     */
    public String[] remove(String[] array) {
        int size = array.length;
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (array[i].equals(array[j])) {
                    array[j] = array[size - 1];
                    size--;
                    j--;
                }
            }
        }
        return Arrays.copyOf(array, size);
    }
}
