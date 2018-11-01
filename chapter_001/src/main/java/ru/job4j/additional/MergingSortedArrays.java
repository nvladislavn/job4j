package ru.job4j.additional;

/**
 * MergingSortedArrays
 *
 * @author Vladislav Nechaev
 * @version $Id$
 * @since 26/10/2018
 */
public class MergingSortedArrays {

    /**
     * merge method
     *
     * @param firstArray  - the first sorted array
     * @param secondArray - the second sorted array
     * @return resultArray - the merged sorted array
     */
    public int[] merge(int[] firstArray, int[] secondArray) {
        int indexFirst = 0, indexSec = 0, indexRes = 0;
        int firstLength = firstArray.length;
        int secondLength = secondArray.length;
        int[] resultArray = new int[firstLength + secondLength];
        while (indexFirst < firstLength && indexSec < secondLength) {
            int elem1 = firstArray[indexFirst];
            int elem2 = secondArray[indexSec];
            if (elem1 < elem2) {
                resultArray[indexRes] = elem1;
                indexFirst++;
            } else {
                resultArray[indexRes] = elem2;
                indexSec++;
            }
            indexRes++;
        }
        while (indexFirst < firstLength) {
            resultArray[indexRes] = firstArray[indexFirst];
            indexFirst++;
            indexRes++;
        }
        while (indexSec < secondLength) {
            resultArray[indexRes] = secondArray[indexRes];
            indexSec++;
            indexRes++;
        }
        return resultArray;
    }
}
