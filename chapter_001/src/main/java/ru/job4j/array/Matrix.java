package ru.job4j.array;

/**
 * Matrix
 *
 * @author Vladislav Nechaev
 * @version $Id$
 * @since 20/10/2018
 */
public class Matrix {

    /**
     * multiple method
     * @param size - size of the matrix
     * @return table - filled table
     */
    public int[][] multiple(int size) {
        int[][] table = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                table[i][j] = (i + 1) * (j + 1);
            }
        }
        return table;
    }
}
