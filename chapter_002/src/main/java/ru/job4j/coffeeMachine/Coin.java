package ru.job4j.coffeeMachine;

public enum Coin {
    COIN_10(10), COIN_5(5), COIN_2(2), COIN_1(1);

    private int coinValue;

    Coin(int coinValue) {
        this.coinValue = coinValue;
    }

    public int getCoinValue() {
        return coinValue;
    }
}
