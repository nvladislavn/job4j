package ru.job4j.carParking.cars;

/**
 * PassengerCar
 *
 * @author Vladislav Nechaev
 * @since 20.06.2020
 */
public class PassengerCar extends AbstractCar {

    private static final int PARKING_WEIGHT = 1;

    private PassengerCar() {
        super(PARKING_WEIGHT);
    }

    public static PassengerCar getPassengerCar() {
        return new PassengerCar();
    }
}
