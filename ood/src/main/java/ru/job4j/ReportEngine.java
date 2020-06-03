package ru.job4j;

import ru.job4j.items.Employee;
import ru.job4j.stores.Store;

import java.util.function.Predicate;

/**
 * ReportEngine
 * is the context of the strategy pattern.
 *
 * @author Vladislav Nechaev
 * @since 26.05.2020
 */
public class ReportEngine {

    private Store store;
    private ReportStrategy report;
    private Predicate<Employee> filter;

    public ReportEngine(Store store, ReportStrategy report, Predicate<Employee> filter) {
        this.store = store;
        this.report = report;
        this.filter = filter;
    }

    /**
     * generateReport
     * delegates method execution.
     *
     * @return - the report string.
     */
    public String generateReport() {
        return report.doReport(store, filter);
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public void setReport(ReportStrategy report) {
        this.report = report;
    }
}
