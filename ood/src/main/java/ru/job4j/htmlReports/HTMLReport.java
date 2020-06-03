package ru.job4j.htmlReports;

import ru.job4j.items.Employee;
import ru.job4j.ReportStrategy;
import ru.job4j.stores.Store;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.function.Predicate;

/**
 * HTMLReport
 *
 * @author Vladislav Nechaev
 * @since 30.05.2020
 */
public class HTMLReport implements ReportStrategy {

    private static final String SMC = ";";
    private static final String LS = System.lineSeparator();

    /**
     * doReport
     * generates a report in html format.
     *
     * @param store - object of store.
     * @param filter - filter to get data about employees.
     * @return - the report string in html format.
     */
    @Override
    public String doReport(Store store, Predicate<Employee> filter) {
        String html = "!DOCTYPE html"
                + "<html>"
                +   "<head>"
                +       "<title>Report for programmers</title>"
                +   "</head>"
                +   LS
                +   "<body>"
                +       "<p>"
                +           "<b>"
                +               "Name;Hired;Fired;Salary"
                +            "</b>"
                +       "</p>"
                + LS;
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        for (Employee employee : store.findBy(filter)) {
            html = String.format("%s%s%s%s%s%s%s%s%s%s%s",
                    html,
                    "<p>",
                    employee.getName(), SMC,
                    df.format(employee.getHired().getTime()), SMC,
                    df.format(employee.getFired().getTime()), SMC,
                    employee.getSalary(),
                    "</p>",
                    LS
            );
        }
        html = html + "</body> + </html>";
        return html;
    }
}
