package ru.job4j.foodStores.storeHouses;

import ru.job4j.foodStores.food.Food;

import java.util.function.Predicate;

/**
 * Shop
 *
 * @author Vladislav Nechaev
 * @since 09.06.2020
 */
public class Shop extends Store {

    private static final int DISCOUNT_BOUND = 75;

    public Shop(int lowerPercent, int upperPercent) {
        super(lowerPercent, upperPercent);
    }

    /**
     * if the percentage of food {@link package ru.job4j.foodStores.food.Food} expiration
     * is between lowerPercent and upperPercent or equals lowerPercent in this Store then predicate returns true.
     *
     * @return Predicate.
     */
    @Override
    public Predicate<Food> getInRangeFilter() {
        return f ->
                getExpiryPercent(f) >= getLowerPercent()
                        && getExpiryPercent(f) < getUpperPercent();
    }

    /**
     * Adds food to foods {@see ru.job4j.foodStores.storeHouses.Store#foods}
     * @param food - food {@link package ru.job4j.foodStores.food.Food} to be added.
     */
    @Override
    public void add(Food food) {
        if (getInRangeFilter().test(food)) {
            if (getExpiryPercent(food) > DISCOUNT_BOUND) {
                food.setPrice(
                        (int) Math.ceil((double) food.getPrice() * (100 - food.getDiscount()) / 100)
                );
            }
            super.add(food);
        } else {
            getNext().add(food);
        }
    }
}
