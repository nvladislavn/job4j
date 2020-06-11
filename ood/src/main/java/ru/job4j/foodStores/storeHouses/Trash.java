package ru.job4j.foodStores.storeHouses;

import ru.job4j.foodStores.food.Food;

import java.util.function.Predicate;

/**
 * Trash
 *
 * @author Vladislav Nechaev
 * @since 09.06.2020
 */
public class Trash extends Store {

    public Trash(int lowerPercent, int upperPercent) {
        super(lowerPercent, upperPercent);
    }

    /**
     * if the percentage of food {@link package ru.job4j.foodStores.food.Food} expiration
     * is greater or equal than lowerPercent in this Store then predicate returns true.
     * @return Predicate.
     */
    @Override
    public Predicate<Food> getInRangeFilter() {
        return f ->
                getLowerPercent() <= getExpiryPercent(f);
    }
}
