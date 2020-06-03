package ru.job4j.reports;

import ru.job4j.ReportStrategy;
import ru.job4j.items.Employee;
import ru.job4j.stores.Store;

import java.util.List;
import java.util.StringJoiner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * HRReport
 *
 * @author Vladislav Nechaev
 * @since 30.05.2020
 */
public class HRReport implements ReportStrategy {

    private static final String SMC = ";";
    private static final String LS = System.lineSeparator();

    /**
     * doReport
     * generates a report without the date oh hiring and the date of dismissal
     * and sorted by the decrease in employee salaries.
     *
     * @param store - object of store.
     * @param filter - filter to get data about employees.
     * @return - the report string.
     */
    @Override
    public String doReport(Store store, Predicate<Employee> filter) {
        List<Employee> employees = store.findBy(filter);
        if (employees.isEmpty()) {
            return "There are no employees meeting the given conditions.";
        }
        employees = employees.stream()
                .sorted((employee1, employee2) -> (int) (employee2.getSalary() - employee1.getSalary()))
                .collect(Collectors.toList());
        StringJoiner joiner = new StringJoiner("");
        joiner.add("Report for HR")
                .add(LS)
                .add("Name;Salary").add(LS);
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
        employees.forEach(employee -> joiner
                .add(employee.getName()).add(SMC)
                .add(Double.toString(employee.getSalary()))
                .add(LS)
        );
        return joiner.toString();
    }
}
