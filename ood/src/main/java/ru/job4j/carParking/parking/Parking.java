package ru.job4j.carParking.parking;

import ru.job4j.carParking.cars.EmptyCar;
import ru.job4j.carParking.cars.ICar;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Parking
 *
 * @author Vladislav Nechaev
 * @since 20.06.2020
 */
public class Parking implements IParking {

    private final int startPassCarIndx;
    private final int startTruckIndx;
    private ICar[] parking;

    public Parking(int passCarPlaces, int truckPlaces) throws IllegalArgumentException {
        if (passCarPlaces < 0 || truckPlaces < 0 || (passCarPlaces + truckPlaces < 1)) {
            throw new IllegalArgumentException("Wrong cars number.");
        }
        this.startPassCarIndx = 0;
        this.startTruckIndx = passCarPlaces;
        parking = new ICar[passCarPlaces + truckPlaces];
        createEmptyParking();
    }

    /**
     * Added a car to the Parking.
     *
     * @param car (@see ru.job4j.carParking.cars.ICar).
     * @return the index of the array with the first element of the car.
     * @throws IllegalArgumentException - throws when wrong incoming date.
     * @throws LimitException           - (@link ru.job4j.carParking.parking.LimitException).
     */
    @Override
    public int add(ICar car) throws IllegalArgumentException, LimitException {
        int result;
        int parkingWeight = car.getParkingWeight();
        if (parkingWeight <= 0) {
            throw new IllegalArgumentException("Wrong parking weight. The Car with id="
                    + car.getID()
                    + " has parking weight = "
                    + parkingWeight);
        }
        if (contains(car)) {
            throw new IllegalArgumentException("The parking already has a car with id=" + car.getID());
        }
        int freePlace = findFreeParking(parkingWeight);
        if (freePlace < 0) {
            throw new LimitException("The number of cars has reached the limit.");
        }
        result = freePlace;
        for (int i = 0; i < parkingWeight; i++) {
            parking[freePlace++] = car;
        }
        return result;
    }

    /**
     * Finds a free space in parking.
     *
     * @param parkingWeight - the number of parking spaces occupied by a car.
     * @return start index to place a car in the parking.
     */
    private int findFreeParking(int parkingWeight) {
        int startFreeIndx;
        if (parkingWeight == 1) {
            startFreeIndx = getFreeSpaceOnPassengerParking(parkingWeight);
        } else {
            startFreeIndx = findFreeSpace(startTruckIndx, parking.length, parkingWeight);
            if (startFreeIndx == -1) {
                startFreeIndx = getFreeSpaceOnPassengerParking(parkingWeight);
            }
        }
        return startFreeIndx;
    }

    /**
     * Finds a free space in parking for a passenger car.
     *
     * @param parkingWeight - the number of parking spaces occupied by a car.
     * @return start index to place a car in the parking.
     */
    private int getFreeSpaceOnPassengerParking(int parkingWeight) {
        return findFreeSpace(startPassCarIndx, startTruckIndx, parkingWeight);
    }

    /**
     * Finds a free space in parking between the start index and the end index.
     *
     * @param startIndx     - the start index of search.
     * @param endIndx       - the end index of search.
     * @param parkingWeight - - the number of parking spaces occupied by a car.
     * @return start index to place a car in the parking.
     */
    private int findFreeSpace(int startIndx, int endIndx, int parkingWeight) {
        int start = -1;
        for (int i = startIndx; i < endIndx; i++) {
            if ((i + parkingWeight) > endIndx) {
                break;
            }
            ICar car = parking[i];
            boolean isFreeSpace = false;
            if (car.getParkingWeight() == 0) {
                isFreeSpace = true;
                for (int j = i + 1; j < i + parkingWeight; j++) {
                    isFreeSpace = parking[j].getParkingWeight() == 0;
                    if (!isFreeSpace) {
                        break;
                    }
                }
            }
            if (isFreeSpace) {
                start = i;
                break;
            }
        }
        return start;
    }

    /**
     * getCarList.
     *
     * @return list of cars placed in the parking.
     */
    @Override
    public List<ICar> getCarList() {
        return Stream.of(parking)
                .filter(car -> !car.getClass().equals(EmptyCar.class))
                .distinct()
                .collect(Collectors.toList());
    }

    /**
     * Deletes the specified car.
     *
     * @param id - the id of the the car to be deleted.
     */
    @Override
    public void removeCar(int id) {
        for (int i = 0; i < parking.length; i++) {
            if (parking[i].getID() == id) {
                parking[i] = new EmptyCar();
                int temp = i + 1;
                while (parking[temp++].getID() == id)
                    parking[temp] = new EmptyCar();
            }
        }
    }

    /**
     * Checks if the specified car is in the parking.
     *
     * @param car (@see ru.job4j.carParking.cars.ICar).
     * @return - true if the specified car is in the parking.
     */
    @Override
    public boolean contains(ICar car) {
        return Arrays.asList(parking).contains(car);
    }

    /**
     * Performs the initial filling of the parking (@link Parking#parking).
     */
    private void createEmptyParking() {
        for (int i = 0; i < parking.length; i++) {
            parking[i] = new EmptyCar();
        }
    }
}
