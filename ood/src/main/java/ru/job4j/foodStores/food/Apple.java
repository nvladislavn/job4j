package ru.job4j.foodStores.food;

import java.time.LocalDate;
import java.util.Calendar;

/**
 * Apple
 *
 * @author Vladislav Nechaev
 * @since 04.06.2020
 */
public class Apple extends Food {

    public Apple(String name, LocalDate expireDate, LocalDate createDate, int price, int discount) {
        super(name, expireDate, createDate, price, discount);
    }
}
