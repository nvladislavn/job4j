package ru.job4j.reports;

import ru.job4j.ReportStrategy;
import ru.job4j.items.Employee;
import ru.job4j.stores.Store;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.Predicate;

/**
 * AccountingReport
 *
 * @author Vladislav Nechaev
 * @since 31.05.2020
 */
public class AccountingReport implements ReportStrategy {

    private static final String SMC = ";";
    private static final String LS = System.lineSeparator();
    private int dollarRate = 70;

    /**
     * doReport
     * generates a report for accounting.
     *
     * @param store  - object of store.
     * @param filter - filter to get data about employees.
     * @return - the report string.
     */
    @Override
    public String doReport(Store store, Predicate<Employee> filter) {
        List<Employee> employees = store.findBy(filter);
        if (employees.isEmpty()) {
            return "There are no employees meeting the given conditions.";
        }
        employees.forEach(
                e -> e.setSalary(e.getSalary() * dollarRate)
        );
        return generateReportBody(employees);
    }

    /**
     * generateReportBody
     * generates a report body.
     *
     * @param employees - a list of Employees.
     * @return - the report string.
     */
    public String generateReportBody(List<Employee> employees) {
        StringJoiner joiner = new StringJoiner("");
        joiner.add("Name;Hired;Fired;Salary").add(LS);
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        employees.forEach(employee -> joiner
                .add(employee.getName().strip()).add(SMC)
                .add(df.format(employee.getHired().getTime())).add(SMC)
                .add(df.format(employee.getFired().getTime())).add(SMC)
                .add(Double.toString(employee.getSalary()))
                .add(LS)
        );
        return joiner.toString();
    }
}

