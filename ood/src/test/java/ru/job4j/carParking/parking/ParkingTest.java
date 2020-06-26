package ru.job4j.carParking.parking;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.carParking.cars.ICar;
import ru.job4j.carParking.cars.PassengerCar;
import ru.job4j.carParking.cars.Truck;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ParkingTest {

    private static final int PASSENGER_CAR_PLACES = 6;
    private static final int TRUCK_PLACES = 7;
    private IParking parking;
    private ICar passCar1;
    private ICar passCar2;
    private ICar passCar3;
    private ICar truck1;
    private ICar truck2;

    @Before
    public void createParking() throws LimitException {
        parking = new Parking(PASSENGER_CAR_PLACES, TRUCK_PLACES);
        passCar1 = PassengerCar.getPassengerCar();
        parking.add(passCar1);
        passCar2 = PassengerCar.getPassengerCar();
        parking.add(passCar2);
        passCar3 = PassengerCar.getPassengerCar();
        parking.add(passCar3);
        truck1 = Truck.getTruck(2);
        parking.add(truck1);
        truck2 = Truck.getTruck(3);
        parking.add(truck2);
    }

    /**
     * When try to add truck to Parking (@link ru.job4j.carParking.parking.Parking)
     * when the limit is reached then throws the LimitException.
     * @throws LimitException (@see ru.job4j.carParking.parking.LimitException).
     */
    @Test(expected = LimitException.class)
    public void whenTruckPlacesGreater8AndPassengerPlacesGreater2ThenLimitException() throws LimitException {
        parking.add(Truck.getTruck(4));
    }

    /**
     * When try to add passenger car to Parking (@link ru.job4j.carParking.parking.Parking)
     * when the limit is reached then throws the LimitException.
     * @throws LimitException (@see ru.job4j.carParking.parking.LimitException).
     */
    @Test(expected = LimitException.class)
    public void whenPassengerPlacesGreater6ThenLimitException() throws LimitException {
        parking.add(PassengerCar.getPassengerCar());
        parking.add(PassengerCar.getPassengerCar());
        parking.add(PassengerCar.getPassengerCar());
        parking.add(PassengerCar.getPassengerCar());
    }

    /**
     * Tests method add(ICar car) (@link ru.job4j.carParking.parking.Parking#add(ICar car)).
     * @throws LimitException (@see ru.job4j.carParking.parking.LimitException).
     */
    @Test
    public void whenAddedPassengerCarThenContainsShouldReturnTrue() throws LimitException {
        ICar passengerCar = PassengerCar.getPassengerCar();
        parking.add(passengerCar);
        assertTrue(parking.contains(passengerCar));
    }

    /**
     * Tests method contains(ICar car) (@link ru.job4j.carParking.parking.Parking#contains(ICar car)).
     */
    @Test
    public void whenAddedTruckThenContainsShouldReturnTrue() {
        assertTrue(parking.contains(truck2));
    }

    /**
     * Tests method add(ICar car) (@link ru.job4j.carParking.parking.Parking#add(ICar car)).
     * @throws LimitException (@see ru.job4j.carParking.parking.LimitException).
     */
    @Test
    public void shouldReturn11() throws LimitException {
        int actualIndex = parking.add(Truck.getTruck(2));
        int expectedIndex = 11;
        assertEquals(actualIndex, expectedIndex);
    }

    /**
     * Tests method contains(ICar car) (@link ru.job4j.carParking.parking.Parking#contains(ICar car)).
     */
    @Test
    public void whenCarIsNotAddThenContainsShouldReturnFalse() {
        assertFalse(parking.contains(
                PassengerCar.getPassengerCar()
        ));
    }

    /**
     * Tests method removeCar(int id) (@link ru.job4j.carParking.parking.Parking#contains(ICar car))
     * for a truck.
     */
    @Test
    public void firstShouldReturnTrueThenFalseForTruck() {
        assertTrue(parking.contains(truck2));
        parking.removeCar(truck2.getID());
        assertFalse(parking.contains(truck2));
    }

    /**
     * Tests method removeCar(int id) (@link ru.job4j.carParking.parking.Parking#contains(ICar car))
     * for a passenger car.
     */
    @Test
    public void firstShouldReturnTrueThenFalseForPassengerCar() {
        assertTrue(parking.contains(passCar2));
        parking.removeCar(passCar2.getID());
        assertFalse(parking.contains(passCar2));
    }

    /**
     * Tests method getCarList() (@link ru.job4j.carParking.parking.Parking#getCarList()).
     */
    @Test
    public void shouldReturnThreePassengerCarAndTwoTruck() {
        List<ICar> expected = List.of(passCar1, passCar2, passCar3, truck1, truck2);
        assertThat(parking.getCarList(), is(expected));
    }
}
