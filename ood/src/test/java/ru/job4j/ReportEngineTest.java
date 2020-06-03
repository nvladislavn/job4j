package ru.job4j;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.htmlReports.HTMLReport;
import ru.job4j.items.Employee;
import ru.job4j.jsonReports.JSONForAccounting;
import ru.job4j.jsonReports.JSONForHR;
import ru.job4j.reports.AccountingReport;
import ru.job4j.reports.HRReport;
import ru.job4j.stores.MemStore;
import ru.job4j.xmlReports.XMLForAccounting;
import ru.job4j.xmlReports.XMLForHR;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ReportEngineTest {

    private static final String LS = System.lineSeparator();
    private MemStore store;
    private String testDate;

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
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        testDate = df.format(Calendar.getInstance().getTime());
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
                + "<p>Ivan;" + testDate + ";" + testDate + ";100.0</p>"
                + LS
                + "<p>John;" + testDate + ";" + testDate + ";120.0</p>"
                + LS
                + "<p>Kate;" + testDate + ";" + testDate + ";130.0</p>"
                + LS
                + "<p>Sam;" + testDate + ";" + testDate + ";110.0</p>"
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
        String expected = "Kate;130.0" + LS
                + "John;120.0" + LS
                + "Sam;110.0" + LS
                + "Ivan;100.0" + LS;
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
        String expected = "Name;Hired;Fired;Salary"
                + LS
                + "Ivan;" + testDate + ";" + testDate + ";7000.0"
                + LS
                + "John;" + testDate + ";" + testDate + ";8400.0"
                + LS
                + "Kate;" + testDate + ";" + testDate + ";9100.0"
                + LS
                + "Sam;" + testDate + ";" + testDate + ";7700.0"
                + LS;
        assertThat(actual, is(expected));
    }

    /**
     * whenJSONAccountingReport
     * tests generateReportBody in JSONForAccounting class.
     */
    @Test
    public void whenJSONAccountingReport() {
        ReportEngine engine = new ReportEngine(store, new JSONForAccounting(), e -> true);
        String actual = engine.generateReport();
        String expected = "{\n"
                + "  \"employees\": [\n"
                + "    {\n"
                + "      \"fired\": \"" + testDate + "\",\n"
                + "      \"name\": \"Ivan\",\n"
                + "      \"hired\": \"" + testDate + "\",\n"
                + "      \"salary\": 7000.0\n"
                + "    },\n"
                + "    {\n"
                + "      \"fired\": \"" + testDate + "\",\n"
                + "      \"name\": \"John\",\n"
                + "      \"hired\": \"" + testDate + "\",\n"
                + "      \"salary\": 8400.0\n"
                + "    },\n"
                + "    {\n"
                + "      \"fired\": \"" + testDate + "\",\n"
                + "      \"name\": \"Kate\",\n"
                + "      \"hired\": \"" + testDate + "\",\n"
                + "      \"salary\": 9100.0\n"
                + "    },\n"
                + "    {\n"
                + "      \"fired\": \"" + testDate + "\",\n"
                + "      \"name\": \"Sam\",\n"
                + "      \"hired\": \"" + testDate + "\",\n"
                + "      \"salary\": 7700.0\n"
                + "    }\n"
                + "  ]\n"
                + "}";
        assertThat(actual, is(expected));
    }

    /**
     * whenJSONHRReport
     * tests generateReportBody in JSONForHR class.
     */
    @Test
    public void whenJSONHRReport() {
        ReportEngine engine = new ReportEngine(store, new JSONForHR(), e -> true);
        String actual = engine.generateReport();
        String expected = "{\n"
                + "  \"employees\": [\n"
                + "    {\n"
                + "      \"name\": \"Kate\",\n"
                + "      \"salary\": \"130.0\"\n"
                + "    },\n"
                + "    {\n"
                + "      \"name\": \"John\",\n"
                + "      \"salary\": \"120.0\"\n"
                + "    },\n"
                + "    {\n"
                + "      \"name\": \"Sam\",\n"
                + "      \"salary\": \"110.0\"\n"
                + "    },\n"
                + "    {\n"
                + "      \"name\": \"Ivan\",\n"
                + "      \"salary\": \"100.0\"\n"
                + "    }\n"
                + "  ]\n"
                + "}";
        assertThat(actual, is(expected));
    }

    /**
     * whenXMLAccountingReport
     * tests generateReportBody in XMLForAccounting class.
     */
    @Test
    public void whenXMLAccountingReport() {
        ReportEngine engine = new ReportEngine(store, new XMLForAccounting(), e -> true);
        String actual = engine.generateReport();
        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>" + LS
                + "<employees xmlns=\"https://ru.job4j.ood\">" + LS
                + "    <employee name=\"Ivan\">" + LS
                + "        <hired>" + testDate + "</hired>" + LS
                + "        <fired>" + testDate + "</fired>" + LS
                + "        <salary>7000.0</salary>" + LS
                + "    </employee>" + LS
                + "    <employee name=\"John\">" + LS
                + "        <hired>" + testDate + "</hired>" + LS
                + "        <fired>" + testDate + "</fired>" + LS
                + "        <salary>8400.0</salary>" + LS
                + "    </employee>" + LS
                + "    <employee name=\"Kate\">" + LS
                + "        <hired>" + testDate + "</hired>" + LS
                + "        <fired>" + testDate + "</fired>" + LS
                + "        <salary>9100.0</salary>" + LS
                + "    </employee>" + LS
                + "    <employee name=\"Sam\">" + LS
                + "        <hired>" + testDate + "</hired>" + LS
                + "        <fired>" + testDate + "</fired>" + LS
                + "        <salary>7700.0</salary>" + LS
                + "    </employee>" + LS
                + "</employees>" + LS;
        assertThat(actual, is(expected));
    }

    /**
     * whenXMLHRReport
     * tests generateReportBody in XMLForHR class.
     */
    @Test
    public void whenXMLHRReport() {
        ReportEngine engine = new ReportEngine(store, new XMLForHR(), e -> true);
        String actual = engine.generateReport();
        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>" + LS
                + "<employees xmlns=\"https://ru.job4j.ood\">" + LS
                + "    <employee name=\"Kate\">" + LS
                + "        <salary>130.0</salary>" + LS
                + "    </employee>" + LS
                + "    <employee name=\"John\">" + LS
                + "        <salary>120.0</salary>" + LS
                + "    </employee>" + LS
                + "    <employee name=\"Sam\">" + LS
                + "        <salary>110.0</salary>" + LS
                + "    </employee>" + LS
                + "    <employee name=\"Ivan\">" + LS
                + "        <salary>100.0</salary>" + LS
                + "    </employee>" + LS
                + "</employees>" + LS;
        assertThat(actual, is(expected));
    }
}

