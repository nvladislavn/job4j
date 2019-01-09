package ru.job4j.list;

import java.util.ArrayList;
import java.util.List;

/**
 * ConvertMatrix2List
 *
 * @author Vladislav Nechaev
 * @since 09.01.2019
 */
public class ConvertMatrix2List {

    /**
     * toList
     *
     * convert Array to List
     * @param array - Array
     * @return - List
     */
    public List<Integer> toList(int[][] array) {
        List<Integer> list = new ArrayList<>();
        for (int row = 0; row < array.length; row++) {
            for (int column = 0; column < array[row].length; column++) {
                list.add(array[row][column]);
            }
        }
        return list;
    }
}
