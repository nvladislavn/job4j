package ru.job4j.design.srp.reports;

import ru.job4j.design.srp.items.Employee;
import ru.job4j.design.srp.stores.Store;

import java.util.function.Predicate;

/**
 * Report
 *
 * @author Vladislav Nechaev
 * @since 28.05.2020
 */
public interface ReportStrategy {

    String doReport(Store store, Predicate<Employee> filter);
}
