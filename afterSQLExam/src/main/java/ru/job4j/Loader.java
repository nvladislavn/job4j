package ru.job4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.config.Config;
import ru.job4j.db.DBConnector;
import ru.job4j.db.DBDescriptor;
import ru.job4j.db.DBReporter;
import ru.job4j.db.DBFiller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Loader
 *
 * @author Vladislav Nechaev
 * @since 20.03.2020
 */
public class Loader {

    private static final Logger LOGGER = LogManager.getLogger(Loader.class.getName());
    private DBConnector connector;

    public static void main(String[] args) {
        Loader loader = new Loader();
        loader.start();
    }

    /**
     * start
     * starts a program
     */
    private void start() {
        Config config = new Config();
        connector = new DBConnector(config);
        try {
            prepareDB();
            fillDB(getConnectionToDB());
            DBReporter reporter = new DBReporter(getConnectionToDB());
            printConfirmApp(reporter);
            printEmptyMeetings(reporter);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * printEmptyApp
     * prints meetings without application.
     *
     * @param reporter - an instance of DBReporter
     * @throws SQLException
     */
    private void printEmptyMeetings(DBReporter reporter) throws SQLException {
        List<String> emptyMeetings = reporter.getEmptyMeetings();
        if (emptyMeetings.isEmpty()) {
            System.out.println("All meetings have applications.");
        } else {
            System.out.println("These meeting(s) have no applications:");
            emptyMeetings.forEach(System.out::println);
        }
    }

    /**
     * printConfirmApp
     * prints confirmed meetings.
     *
     * @param reporter - an instance of DBReporter
     * @throws SQLException
     */
    private void printConfirmApp(DBReporter reporter) throws SQLException {
        Map<String, String> apps = reporter.getConfirmAppNumber();
        apps.entrySet().forEach(e -> System.out.printf("%s - %s%s",
                                                        e.getKey(),
                                                        e.getValue(),
                                                        System.lineSeparator()));
    }

    /**
     *fillDB
     * fills the database
     *
     * @param connection -  instance of connection to the DB
     * @throws SQLException
     */
    private void fillDB(Connection connection) throws SQLException {
        DBFiller filler = new DBFiller(connection);
        filler.fillTables();
    }

    /**
     * prepareDB
     * creates and prepares db
     *
     * @throws SQLException
     */
    private void prepareDB() throws SQLException {
        DBDescriptor descriptor = null;
        descriptor = new DBDescriptor();
        descriptor.createDB(connector.getConnectionToServer());
        Connection connectionToDB = getConnectionToDB();
        descriptor.createTables(connectionToDB);
    }

    /**
     * getConnectionToDB
     *
     * @return - instance of connection to the DB
     * @throws SQLException
     */
    private Connection getConnectionToDB() throws SQLException {
        return connector.getConnectionToDB();
    }
}
