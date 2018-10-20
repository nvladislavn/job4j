package ru.job4j.array;

/**
 * BubbleSort
 *
 * @author Vladislav Nechaev
 * @version $Id$
 * @since 19/10/2018
 */
public class BubbleSort {

    /**
     * sort method
     *
     * @param array - array for sort
     * @return sorted array
     */
    public int[] sort(int[] array) {
        int[] resArray = array;
        for (int i = 0; i < resArray.length; i++) {
            for (int j = 0; j < resArray.length - 1 - i; j++) {
                if (resArray[j] > resArray[j + 1]) {
                    int tmp = resArray[j];
                    resArray[j] = resArray[j + 1];
                    resArray[j + 1] = tmp;
                }
            }
        }
        return resArray;
    }
}
