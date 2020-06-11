package ru.job4j.foodStores.food;

import java.time.LocalDate;
import java.util.Calendar;

/**
 * Avocado
 *
 * @author Vladislav Nechaev
 * @since 04.06.2020
 */
public class Avocado extends Food {

    public Avocado(String name, LocalDate expireDate, LocalDate createDate, int price, int discount) {
        super(name, expireDate, createDate, price, discount);
    }
}
