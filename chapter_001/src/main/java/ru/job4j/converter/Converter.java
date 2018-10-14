package ru.job4j.converter;

public class Converter {

    private final int COURSE_OF_RUBLE_TO_DOLLAR = 60;
    private final int COURSE_OF_RUBLE_TO_EURO = 70;

    /**
     * Конвертируем рубли в евро.
     * @param value рубли.
     * @return Евро.
     */
    public int rubleToEuro(int value) {
        return (int) Math.round(value / COURSE_OF_RUBLE_TO_EURO);
    }

    /**
     * Конвертируем рубли в доллары.
     * @param value рубли.
     * @return Доллары
     */
    public int rubleToDollar(int value) {
        return (int) Math.round(value / COURSE_OF_RUBLE_TO_DOLLAR);
    }

    /**
     * Конвертируем евро в рубли.
     * @param value евро.
     * @return Рубли.
     */
    public int euroToRuble(int value) {
        return (int) Math.round(value * COURSE_OF_RUBLE_TO_EURO);
    }

    /**
     * Конвертируем доллары в рубли.
     * @param value доллары.
     * @return Рубли.
     */
    public int dollarToRuble(int value) {
        return (int) Math.round(value * COURSE_OF_RUBLE_TO_DOLLAR);
    }
}
