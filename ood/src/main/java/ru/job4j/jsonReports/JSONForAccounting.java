package ru.job4j.jsonReports;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import ru.job4j.items.Employee;
import ru.job4j.reports.AccountingReport;
import org.json.simple.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * JSONForAccounting
 *
 * @author Vladislav Nechaev
 * @since 01.06.2020
 */
public class JSONForAccounting extends AccountingReport {

    /**
     * generateReportBody
     * generates a report body for accounting in JSON format.
     *
     * @param employees = a list of Employees.
     * @return - the report string in JSON format.
     */
    @Override
    public String generateReportBody(List<Employee> employees) {
        JSONObject jObj = new JSONObject();
        JSONArray employeesArr = new JSONArray();
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        employees.forEach(e -> {
            JSONObject employee = new JSONObject();
            employee.put("name", e.getName());
            employee.put("hired", df.format(e.getHired().getTime()));
            employee.put("fired", df.format(e.getFired().getTime()));
            employee.put("salary", e.getSalary());
            employeesArr.add(employee);
        });
        jObj.put("employees", employeesArr);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(jObj);
    }
}
