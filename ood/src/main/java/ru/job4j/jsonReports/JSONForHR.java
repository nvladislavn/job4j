package ru.job4j.jsonReports;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ru.job4j.items.Employee;
import ru.job4j.reports.HRReport;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * JSONForHR.
 *
 * @author Vladislav Nechaev.
 * @since 02.06.2020.
 */
public class JSONForHR extends HRReport {

    /**
     * generateReportBody.
     * generates a report body for HR in the JSON format.
     *
     * @param employees - a list of Employee.
     * @return - the report string in JSON format.
     */
    @Override
    public String generateReportBody(List<Employee> employees) {
        JSONObject jObject = new JSONObject();
        JSONArray employeesArr = new JSONArray();
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        employees.forEach(e -> {
                            JSONObject employee = new JSONObject();
                            employee.put("name", e.getName());
                            employee.put("salary", Double.toString(e.getSalary()));
                            employeesArr.add(employee);
        });
        jObject.put("employees", employeesArr);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(jObject);
    }
}
