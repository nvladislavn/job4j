package ru.job4j.carParking.cars;

/**
 * Truck
 *
 * @author Vladislav Nechaev
 * @since 20.06.2020
 */
public class Truck extends AbstractCar {

    private Truck(int parkingWeight) {
        super(parkingWeight);
    }

    public static Truck getTruck(int parkingWeight) {
        if (parkingWeight < 2) {
            throw new IllegalArgumentException("Wrong car parking weight.");
        }
        return new Truck(parkingWeight);
    }
}
