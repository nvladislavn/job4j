package ru.job4j.carParking.cars;

/**
 * EmptyCar
 *
 * @author Vladislav Nechaev
 * @since 25.06.2020
 */
public class EmptyCar extends AbstractCar {

    private static final int EMPTY_PARKING_WEIGHT = 0;

    public EmptyCar() {
        super(EMPTY_PARKING_WEIGHT);
    }
}
