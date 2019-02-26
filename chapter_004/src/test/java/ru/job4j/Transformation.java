package ru.job4j;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Transformation
 *
 * @author Vladislav Nechaev
 * @since 26.02.2019
 */
public class Transformation {

    /**
     * matrixToList
     * Method transformations matrix to list.
     * @param matrix - a two-dimensional array.
     * @return - list of Integer.
     */
    public List<Integer> matrixToList(Integer[][] matrix) {
        return Stream.of(matrix).flatMap(Arrays::stream).collect(Collectors.toList());
    }
}
