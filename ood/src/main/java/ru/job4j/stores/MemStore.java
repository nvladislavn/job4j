package ru.job4j.stores;

import ru.job4j.items.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * MemStore
 * Emulates working with a data source.
 *
 * @author Vladislav Nechaev
 * @since 26.05.2020
 */
public class MemStore implements Store {

    private final List<Employee> employees = new ArrayList<>();

    /**
     * add
     * adds an employee object to the store.
     *
     * @param em - an employee object.
     */
    public void add(Employee em) {
        employees.add(em);
    }

    /**
     * findBy
     *
     * @param filter - predicate
     * @return - a list of employees received on given condition.
     */
    @Override
    public List<Employee> findBy(Predicate<Employee> filter) {
        return employees.stream().filter(filter).collect(Collectors.toList());
    }
}
