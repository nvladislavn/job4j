package ru.job4j.stores;

import ru.job4j.items.Employee;

import java.util.List;
import java.util.function.Predicate;

/**
 * Store
 *
 * @author Vladislav Nechaev
 * @since 26.05.2020
 */
public interface Store {

    List<Employee> findBy(Predicate<Employee> filter);
}
