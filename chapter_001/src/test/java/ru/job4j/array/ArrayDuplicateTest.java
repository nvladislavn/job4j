package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.collection.IsArrayContainingInAnyOrder.arrayContainingInAnyOrder;
import static org.junit.Assert.*;

/**
 * MatrixCheckTest
 *
 * @author Vladislav Nechaev
 * @version $Id$
 * @since 22/10/2018
 */
public class ArrayDuplicateTest {

    /**
     * Test remove method
     */
    @Test
    public void whenArrayWithDuplicatesThenRemoveDuplicates() {
        ArrayDuplicate arrayDuplicate = new ArrayDuplicate();
        String[] testArray = {"Привет", "Мир", "Привет", "Супер", "Мир"};
        String[] expectedArray = {"Привет", "Мир", "Супер"};
        String[] resultArray = arrayDuplicate.remove(testArray);
        assertThat(resultArray, arrayContainingInAnyOrder(expectedArray));
    }

}