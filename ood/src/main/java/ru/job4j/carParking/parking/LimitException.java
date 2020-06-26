package ru.job4j.carParking.parking;

/**
 * LimitException
 *
 * @author Vladislav Nechaev
 * @since 20.06.2020
 */
public class LimitException extends Exception {

    public LimitException(String message) {
        super(message);
    }
}
