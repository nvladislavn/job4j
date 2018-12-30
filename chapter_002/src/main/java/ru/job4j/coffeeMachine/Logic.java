package ru.job4j.coffeeMachine;

import java.util.Arrays;

/**
 * Logic
 *
 * @author Vladislav Nechaev
 * @version $Id$
 * @since 30.12.2018
 */
public class Logic {

    /**
     * getChange
     *
     * @param value - the amount
     * @param price - the price
     * @return - array of coin's values
     */
    public int[] getChange(int value, int price) {
        int change = value - price;
        Coin[] coinArray = Coin.values();
        int[] resultArray = new int[0];
        for (Coin coin : coinArray) {
            int coinValue = coin.getCoinValue();
            int coinCount = 0;
            if (coinValue <= change) {
                coinCount = change / coinValue;
                change = change % coinValue;
                int arrayLength = resultArray.length;
                resultArray = Arrays.copyOf(resultArray, arrayLength + coinCount);
                for (int i = 0; i < coinCount; i++) {
                    resultArray[arrayLength + i] = coinValue;
                }
            }
        }
        return resultArray;
    }
}
