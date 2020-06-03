package ru.job4j.xmlReports;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import ru.job4j.items.Employee;
import ru.job4j.reports.AccountingReport;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * XMLForAccounting
 *
 * @author Vladislav Nechaev
 * @since 02.06.2020
 */
public class XMLForAccounting extends AccountingReport {

    private static final Logger LOG = LogManager.getLogger(XMLForAccounting.class.getName());
    private final DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
    private Document document;

    /**
     * generateReportBody.
     * generates a report body for accounting in XML format.
     *
     * @param employees - a list of Employees.
     * @return - the report string in XML format.
     */
    @Override
    public String generateReportBody(List<Employee> employees) {
        StringWriter writer = new StringWriter();
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        StreamResult result;
        try {
            document =  factory.newDocumentBuilder().newDocument();
            Element root = document.createElementNS("https://ru.job4j.ood", "employees");
            document.appendChild(root);
            employees.forEach(e ->
                    root.appendChild(getEmployee(e))
                    );
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(document);
            result = new StreamResult(writer);
            transformer.transform(source, result);
        } catch (ParserConfigurationException | TransformerException e) {
            LOG.error(e.getMessage(), e);
        }
        return writer.toString();
    }

    /**
     * getEmployee.
     * creates nested node.
     *
     * @param employee - a list of Employees.
     * @return - the nested node with employee data.
     */
    private Node getEmployee(Employee employee) {
        Element node = document.createElement("employee");
        node.setAttribute("name", employee.getName());
        node.appendChild(
                    getNestedItems("hired", df.format(employee.getHired().getTime()))
            );
        node.appendChild(
                    getNestedItems("fired", df.format(employee.getFired().getTime()))
            );
        node.appendChild(
                    getNestedItems("salary", Double.toString(employee.getSalary()))
            );
        return node;
    }

    /**
     * getNestedItems.
     * creates nested node.
     *
     * @param item - name of nested node.
     * @param value - value of nested node.
     * @return - nested node.
     */
    private Node getNestedItems(String item, String value) {
        Element nestNode = document.createElement(item);
        nestNode.appendChild(document.createTextNode(value));
        return nestNode;
    }
}
