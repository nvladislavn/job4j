package ru.job4j.calculator;

/**
 * Программа расчета идеального веса.
 */
public class Fit {

    private final double mansSubtrahend = 100;
    private final double womansSubtrahend = 110;
    private final double coefficient = 1.15;

    /**
     * Идеальный вес для мужчины.
     * @param height Рост.
     * @return идеальный вес.
     */
    public double manWeight(double height) {
        return ((height - mansSubtrahend) * coefficient);
    }

    /**
     * Идеальный вес для женщины.
     * @param height Рост.
     * @return идеальный вес.
     */
    public double womanWeight(double height) {
        return ((height - womansSubtrahend) * coefficient);
    }

    /**
     * Рост для мужчины при соответствующем весе.
     * @param weight Вес.
     * @return рост.
     */
    public double manHeight(double weight) {
        return ((weight + mansSubtrahend * coefficient) / coefficient);
    }

    /**
     * Рост для женщины при соответствующем весе.
     * @param weight Вес.
     * @return рост.
     */
    public double womanHeight(double weight) {
        return ((weight + womansSubtrahend * coefficient) / coefficient);
    }
}
