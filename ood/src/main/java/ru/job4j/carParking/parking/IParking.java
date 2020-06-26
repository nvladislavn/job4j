package ru.job4j.carParking.parking;

import ru.job4j.carParking.cars.ICar;

import java.util.List;

/**
 * IParking
 *
 * @author Vladislav Nechaev
 * @since 19.06.2020
 */
public interface IParking {

    int add(ICar car) throws LimitException;

    void removeCar(int id);

    boolean contains(ICar car);

    List<ICar> getCarList();
}
