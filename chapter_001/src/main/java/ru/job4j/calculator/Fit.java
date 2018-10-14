package ru.job4j.calculator;

/**
 * Программа расчета идеального веса.
 */
public class Fit {

    private final double MAN_S_SUBTRAHEND = 100;
    private final double WOMAN_S_SUBTRAHEND = 110;
    private final double COEFFICIENT = 1.15;

    /**
     * Идеальный вес для мужчины.
     * @param height Рост.
     * @return идеальный вес.
     */
    public double manWeight(double height) {
        return ((height - MAN_S_SUBTRAHEND) * COEFFICIENT);
    }

    /**
     * Идеальный вес для женщины.
     * @param height Рост.
     * @return идеальный вес.
     */
    public double womanWeight(double height) {
        return ((height - WOMAN_S_SUBTRAHEND) * COEFFICIENT);
    }

    /**
     * Рост для мужчины при соответствующем весе.
     * @param weight Вес.
     * @return рост.
     */
    public double manHeight(double weight) {
        return ((weight + MAN_S_SUBTRAHEND * COEFFICIENT) / COEFFICIENT);
    }

    /**
     * Рост для женщины при соответствующем весе.
     * @param weight Вес.
     * @return рост.
     */
    public double womanHeight(double weight) {
        return ((weight + WOMAN_S_SUBTRAHEND * COEFFICIENT) / COEFFICIENT);
    }
}
