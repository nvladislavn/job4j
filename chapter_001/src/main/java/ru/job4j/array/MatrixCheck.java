package ru.job4j.array;

/**
 * MatrixCheck
 *
 * @author Vladislav Nechaev
 * @version $Id$
 * @since 20/10/2018
 */
public class MatrixCheck {

    /**
     * mono
     *
     * @param data - two-dimensional array
     * @return - is homogeneity array
     */
    public boolean mono(boolean[][] data) {
        boolean result = true;
        int size = data.length;
        for (int i = 1; i < size; i++) {
            if (data[0][0] != data[i][i] || data[0][size - 1] != data[i][size - 1 - i]) {
                result = false;
                break;
            }
        }
        return result;
    }
}
