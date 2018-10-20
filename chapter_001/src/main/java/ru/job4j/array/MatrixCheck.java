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
        for (int i = 0; result & i < size - 1; i++) {
            result = data[i][i] == data[i + 1][i + 1];
            if (!result) {
                break;
            } else {
                result = data[i][size - 1 - i] == data[i + 1][size - 1 - (i + 1)];
            }
        }
        return result;
    }
}
