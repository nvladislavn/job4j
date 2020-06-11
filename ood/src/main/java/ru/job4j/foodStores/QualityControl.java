package ru.job4j.foodStores;

import ru.job4j.foodStores.food.Food;
import ru.job4j.foodStores.storeHouses.Store;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * QualityControl
 *
 * @author Vladislav Nechaev
 * @since 09.06.2020
 */
public class QualityControl {

    /**
     * redistributes foods to stores from the list of stores.
     *
     * @param stores - the list of stores {@link ru.job4j.foodStores.storeHouses.Store}.
     */
    public void control(List<Store> stores) {
        Store startStore = stores.get(0);
        for (Food food : getAllFoods(stores)) {
            startStore.add(food);
        }
    }

    /**
     * returns all foods from all stores and cleans all stores.
     *
     * @param stores - the list of stores {@link ru.job4j.foodStores.storeHouses.Store}.
     * @return - Set of foods {@link ru.job4j.foodStores.food.Food}.
     */
    private Set<Food> getAllFoods(List<Store> stores) {
        Set<Food> allFoods = new HashSet<>();
        stores.forEach(s -> {
                        Set<Food> foods = s.getFoods(f -> true);
                        allFoods.addAll(foods);
                        s.removeFoods(foods);
                    });
        return allFoods;
    }
}
