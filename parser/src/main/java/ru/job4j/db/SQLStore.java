package ru.job4j.db;


import ru.job4j.Item;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.sql.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * SQLStore
 *
 * @author Vladislav Nechaev
 * @since 19.11.2019
 */
public class SQLStore implements AutoCloseable {

    private static final Logger LOG = LogManager.getLogger(SQLStore.class.getName());
    private Connection connection;

    public SQLStore(Connection connection) {
        this.connection = connection;
        prepareDB();
    }

    /**
     * prepareDB
     * Prepares the database for work.
     */
    private void prepareDB() {
        String createTable = "CREATE TABLE IF NOT EXISTS parsed_data"
                + "("
                + "id SERIAL PRIMARY KEY,"
                + "name VARCHAR(3000) CONSTRAINT unique_name UNIQUE,"
                + "text TEXT,"
                + "link VARCHAR(2000),"
                + "date TIMESTAMP"
                + ");";
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                statement.execute(createTable);
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        }
    }

    /**
     * addItems
     * Adds a vacancy to the database.
     *
     * @param items - an instance of the Vacancy.
     * @throws SQLException
     */
    public void addItems(List<Item> items) throws SQLException {
        String insertItems = "INSERT INTO parsed_data "
                + "("
                + "name, "
                + "text, "
                + "link, "
                + "date)"
                + "VALUES (?, ?, ?, ?)"
                + "ON CONFLICT (name) DO NOTHING;";
        connection.setAutoCommit(false);
        PreparedStatement pStatement = connection.prepareStatement(insertItems);
        for (Item item : items) {
            pStatement.setString(1, item.getName());
            pStatement.setString(2, item.getText());
            pStatement.setString(3, item.getUrl());
            pStatement.setTimestamp(4, new Timestamp(item.getMsgDate().getTime()));
            pStatement.addBatch();
        }
        pStatement.executeBatch();
        connection.commit();
    }

    /**
     * getLastEntryDate
     * Gets the date of the last entry from the database.
     *
     * @return - the date of the last entry.
     */
    public Calendar getLastEntryDate() {
        Calendar lastDate = new GregorianCalendar(
                new GregorianCalendar().get(Calendar.YEAR), Calendar.JANUARY, 1
        );
        String sqlLastDate = "SELECT MAX(date) lastDate FROM parsed_data";
        try (Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery(sqlLastDate);
            result.next();
            Timestamp timestamp = result.getTimestamp("lastDate");
            if (timestamp != null) {
                lastDate.setTimeInMillis(timestamp.getTime());
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return lastDate;
    }

    @Override
    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        }
    }
}
