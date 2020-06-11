package ru.job4j.foodStores;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.foodStores.food.*;
import ru.job4j.foodStores.storeHouses.Shop;
import ru.job4j.foodStores.storeHouses.Store;
import ru.job4j.foodStores.storeHouses.Trash;
import ru.job4j.foodStores.storeHouses.Warehouse;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class QualityControlTest {

    private Store wareHouse;
    private Store shop;
    private Store trash;
    private List<Food> apples;
    private List<Food> breads;
    private List<Food> avokado;

    private void createFoods() {
        apples = List.of(
                new Apple("Apple1", LocalDate.now().plusDays(80), LocalDate.now().minusDays(20), 100, 0),
                new Apple("Apple2", LocalDate.now().plusDays(85), LocalDate.now().minusDays(15), 110, 10),
                new Apple("Apple3", LocalDate.now().plusDays(90), LocalDate.now().minusDays(10), 120, 5)
        );
        breads = List.of(
                new Bread("Bread1", LocalDate.now().plusDays(50), LocalDate.now().minusDays(50), 50, 0),
                new Bread("Bread2", LocalDate.now().plusDays(10), LocalDate.now().minusDays(90), 100, 10),
                new Bread("Bread3", LocalDate.now().plusDays(52), LocalDate.now().minusDays(48), 70, 0)
        );
        avokado = List.of(
                new Avocado("Avokado1", LocalDate.now().plusDays(0), LocalDate.now().minusDays(100), 200, 0),
                new Avocado("Avokado2", LocalDate.now().minusDays(10), LocalDate.now().minusDays(110), 210, 15),
                new Avocado("Avokado3", LocalDate.now().plusDays(0), LocalDate.now().minusDays(100), 220, 0)
        );
    }

    @Before
    public void prepareTestDate() {
        createFoods();
        wareHouse = new Warehouse(0, 25);
        wareHouse.addFoods(List.of(
                            apples.get(0),
                            breads.get(0),
                            avokado.get(0)
        ));
        shop = new Shop(25, 100);
        shop.addFoods(List.of(
                            apples.get(1),
                            breads.get(1),
                            avokado.get(1)
        ));
        trash = new Trash(100, 100);
        trash.addFoods(List.of(
                            apples.get(2),
                            breads.get(2),
                            avokado.get(2)
                    ));
        wareHouse.setNext(shop);
        shop.setNext(trash);
    }

    /**
     * tests control {@link ru.job4j.foodStores.QualityControl#control(List)}
     */
    @Test
    public void shouldBeThreeApplesInWarehouseAndThreeBreadsInShopAndThreeAvocadoInTrash() {
        new QualityControl().control(List.of(wareHouse, shop, trash));
        Set<Food> wareHouseActual = wareHouse.getFoods(f -> true);
        Set<Food> wareHouseExpected = new HashSet<>();
        wareHouseExpected.add(apples.get(0));
        wareHouseExpected.add(apples.get(1));
        wareHouseExpected.add(apples.get(2));
        assertThat(wareHouseActual, is(wareHouseExpected));
        Set<Food> shopActual = shop.getFoods(f -> true);
        Set<Food> shopExpected = new HashSet<>();
        shopExpected.add(breads.get(0));
        shopExpected.add(
                new Bread("Bread2",
                        LocalDate.now().plusDays(10),
                        LocalDate.now().minusDays(90),
                        90, 10)
        );
        shopExpected.add(breads.get(2));
        assertThat(shopActual, is(shopExpected));
        Set<Food> trashActual = trash.getFoods(f -> true);
        Set<Food> trashExpected = new HashSet<>();
        trashExpected.add(avokado.get(0));
        trashExpected.add(avokado.get(1));
        trashExpected.add(avokado.get(2));
        assertThat(trashActual, is(trashExpected));
    }
}

