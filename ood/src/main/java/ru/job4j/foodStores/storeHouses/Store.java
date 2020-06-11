package ru.job4j.foodStores.storeHouses;

import com.sun.istack.NotNull;
import ru.job4j.foodStores.food.Food;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * AbstractStore
 *
 * @author Vladislav Nechaev
 * @since 09.06.2020
 */
public abstract class Store {

    private Store next;
    private Set<Food> foods = new HashSet<>();
    private int lowerPercent;
    private int upperPercent;

    public Store(int lowerPercent, int upperPercent) {
        this.lowerPercent = lowerPercent;
        this.upperPercent = upperPercent;
    }

    /**
     * Creates Predicate.
     * @return - predicates if food in the range of suitable foods then true.
     */
    public abstract Predicate<Food> getInRangeFilter();

    /**
     * Adds food to foods {@link Store#foods}
     * @param food - food {@link package ru.job4j.foodStores.food.Food} to be added.
     */
    public void add(@NotNull Food food) {
        if (getInRangeFilter().test(food)) {
            foods.add(food);
        } else {
            next.add(food);
        }
    }

    /**
     * returns a Set by a specific filter.
     * @param filter - a predicate {@link Store#getInRangeFilter()}
     * @return - the food Set.
     */
    public Set<Food> getFoods(Predicate<Food> filter) {
        return foods.stream()
                .filter(filter)
                .collect(Collectors.toSet());
    }

    /**
     * calculates percentage of expiration.
     * @param food - food {@link package ru.job4j.foodStores.food.Food} for calculation.
     * @return - percentage of expiration.
     */
    public int getExpiryPercent(@NotNull Food food) {
        return (int) Math.ceil(
                (double) ChronoUnit.DAYS.between(food.getCreateDate(), LocalDate.now())
                        / ChronoUnit.DAYS.between(food.getCreateDate(), food.getExpireDate())
                        * 100);
    }

    /**
     * removes specified food {@link package ru.job4j.foodStores.food.Food}.
     * @param food - food {@link package ru.job4j.foodStores.food.Food} to be removed.
     */
    public void removeFood(Food food) {
        foods.remove(food);
    }

    /**
     * removes elements from passed collection from foods {@link Store#foods}.
     * @param collection - collection of foods to be removed from foods {@link Store#foods}.
     */
    public void removeFoods(Collection<Food> collection) {
        foods.removeAll(collection);
    }

    public Store getNext() {
        return next;
    }

    public void setNext(Store next) {
        this.next = next;
    }

    public int getLowerPercent() {
        return lowerPercent;
    }

    public void setLowerPercent(int lowerPercent) {
        this.lowerPercent = lowerPercent;
    }

    public int getUpperPercent() {
        return upperPercent;
    }

    public void setUpperPercent(int upperPercent) {
        this.upperPercent = upperPercent;
    }

    public void addFoods(Collection<Food> collection) {
        foods.addAll(collection);
    }
}
