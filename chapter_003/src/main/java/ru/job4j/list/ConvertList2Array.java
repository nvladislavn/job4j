package ru.job4j.list;

import java.util.ArrayList;
import java.util.List;

/**
 * ConvertList2Array
 *
 * @author Vladislav Nechaev
 * @since 08.01.2019
 */
public class ConvertList2Array {

    /**
     * toArray
     * convert List to Array
     * @param list - list
     * @param rows - row count
     * @return - array
     */
    public int[][] toArray(List<Integer> list, int rows) {
        int remainder = list.size() % rows;
        int columns = (remainder == 0) ? list.size() / rows : list.size() / rows + 1;
        int[][] resArray = new int[rows][columns];
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                int index = columns * row + column;
                resArray[row][column] = index > (list.size() - 1) ? 0 : list.get(index);
            }
        }
        return resArray;
    }

    /**
     * convert
     *
     * convert List of arrays to integer List
     * @param list - List of arrays
     * @return - integer List
     */
    public List<Integer> convert(List<int[]> list) {
        List<Integer> result = new ArrayList<>();
        for (int[] array : list) {
            for (int item : array) {
                result.add(item);
            }
        }
        return result;
    }
}
