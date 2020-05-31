package ru.job4j.design.srp;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.design.srp.items.Employee;
import ru.job4j.design.srp.reports.AccountingReport;
import ru.job4j.design.srp.reports.HRReport;
import ru.job4j.design.srp.reports.HTMLReport;
import ru.job4j.design.srp.reports.ReportEngine;
import ru.job4j.design.srp.stores.MemStore;

import java.util.Calendar;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ReportEngineTest {

    private static final String LS = System.lineSeparator();
    private MemStore store;

    /**
     * datePrepare
     * prepares object of the Store.
     */
    @Before
    public void datePrepare() {
        store = new MemStore();
        Calendar now = Calendar.getInstance();
        store.add(new Employee("Ivan", now, now, 100));
        store.add(new Employee("John", now, now, 120));
        store.add(new Employee("Kate", now, now, 130));
        store.add(new Employee("Sam", now, now, 110));
    }

    /**
     * whenProgrammersReport
     * tests doReport in HTMLReport class.
     */
    @Test
    public void whenProgrammersReport() {
        ReportEngine engine = new ReportEngine(store, new HTMLReport(), e -> true);
        String actual = engine.generateReport();
        String expected = "!DOCTYPE html"
                        + "<html>"
                        + "<head>"
                        + "<title>Report for programmers</title>"
                        + "</head>"
                        + LS
                        + "<body>"
                        + "<p>"
                        + "<b>"
                        + "Name;Hired;Fired;Salary"
                        + "</b>"
                        + "</p>"
                        + LS
                        + "<p>Ivan;31.05.2020;31.05.2020;100.0</p>"
                        + LS
                        + "<p>John;31.05.2020;31.05.2020;120.0</p>"
                        + LS
                        + "<p>Kate;31.05.2020;31.05.2020;130.0</p>"
                        + LS
                        + "<p>Sam;31.05.2020;31.05.2020;110.0</p>"
                        + LS
                        + "</body> + </html>";
        assertThat(actual, is(expected));
    }

    /**
     * whenHRReport
     * tests doReport in HRReport class.
     */
    @Test
    public void whenHRReport() {
        ReportEngine engine = new ReportEngine(store, new HRReport(), e -> true);
        String actual = engine.generateReport();
        String expected = "Report for HR"
                        + LS
                        + "Name;Salary"
                        + LS
                        + "Kate;130.0"
                        + LS
                        + "John;120.0"
                        + LS
                        + "Sam;110.0"
                        + LS
                        + "Ivan;100.0"
                        + LS;
        assertThat(actual, is(expected));
    }

    /**
     * whenAccountingReport
     * tests doReport in AccountingReport class.
     */
    @Test
    public void whenAccountingReport() {
        ReportEngine engine = new ReportEngine(store, new AccountingReport(), e -> true);
        String actual = engine.generateReport();
        String expected = "Report for Accounting"
                        + LS
                        + "Name;Hired;Fired;Salary"
                        + LS
                        + "Ivan;31.05.2020;31.05.2020;7000.0"
                        + LS
                        + "John;31.05.2020;31.05.2020;8400.0"
                        + LS
                        + "Kate;31.05.2020;31.05.2020;9100.0"
                        + LS
                        + "Sam;31.05.2020;31.05.2020;7700.0"
                        + LS;
        assertThat(actual, is(expected));
    }
}