package ru.job4j.coffeemachine;

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
        int[] resultArray = new int[change];
        int index = 0;
        for (Coin coin : coinArray) {
            int coinValue = coin.getCoinValue();
            int coinCount = 0;
            if (coinValue <= change) {
                coinCount = change / coinValue;
                change = change % coinValue;
                for (int i = 0; i < coinCount; i++) {
                    resultArray[index + i] = coinValue;
                }
                index += coinCount;
            }
        }
        resultArray = Arrays.copyOf(resultArray, index);
        return resultArray;
    }
}
