package ru.job4j.carParking.cars;

/**
 * Car
 *
 * @author Vladislav Nechaev
 * @since 20.06.2020
 */
public abstract class AbstractCar implements ICar {

    private int parkingWeight;
    private int id;
    private static int idCounter = 0;

    public AbstractCar(int parkingWeight) {
        if (parkingWeight < 0) {
            throw new IllegalArgumentException("Wrong car parking weight.");
        }
        this.parkingWeight = parkingWeight;
        this.id = ++idCounter;
    }

    /**
     * getParkingWeight.
     * @return the number of parking spaces occupied by a car.
     */
    @Override
    public int getParkingWeight() {
        return this.parkingWeight;
    }

    /**
     * getID.
     * @return the id of the car.
     */
    @Override
    public int getID() {
        return this.id;
    }
}
