package ru.job4j.trackersql;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.tracker.Item;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * TrackerSQLTest
 *
 * @author Vladislav Nechaev
 * @since 14.09.2019
 */
public class TrackerSQLTest {

    private Connection connection;
    private TrackerSQL trackerSQL;
    private List<Item> items = new ArrayList<>();
    private static final Item testItem = new Item("testItem", "testDesc", System.currentTimeMillis());

    @Before
    public void prepareTest() {
        setConnection();
        createTracker();
        createTable();
    }

    @After
    public void closeConnect() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void setConnection() {
        try (InputStream in = TrackerSQLTest.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties properties = new Properties();
            properties.load(in);
            Class.forName(properties.getProperty("driver-class-name"));
            connection = ConnectionRollback.create(
                                        DriverManager.getConnection(
                                                properties.getProperty("url"),
                                                properties.getProperty("username"),
                                                properties.getProperty("password")
                                        )
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createTable() {
        fillItems();
        String createSQL = "DROP TABLE IF EXISTS "
                + "    items,"
                + "    comments;"
                + "CREATE TABLE items "
                + "("
                + "    id          VARCHAR(13) PRIMARY KEY, "
                + "    name        VARCHAR(2000), "
                + "    description VARCHAR(3000), "
                + "    created     BIGINT "
                + "); "
                + "INSERT INTO items (id, name, description) "
                + "VALUES (?, ?, ?), "
                + "(?, ?, ?), "
                + "(?, ?, ?);";
        try (PreparedStatement pStatement = connection.prepareStatement(createSQL)) {
            pStatement.setString(1, items.get(0).getId());
            pStatement.setString(2, items.get(0).getName());
            pStatement.setString(3, items.get(0).getDesc());
            pStatement.setString(4, items.get(1).getId());
            pStatement.setString(5, items.get(1).getName());
            pStatement.setString(6, items.get(1).getDesc());
            pStatement.setString(7, items.get(2).getId());
            pStatement.setString(8, items.get(2).getName());
            pStatement.setString(9, items.get(2).getDesc());
            pStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void fillItems() {
        for (int i = 1; i < 4; i++) {
            Item item = new Item(String.format("Name%s", i),
                    String.format("Desc%s", i));
            item.setId(String.format("%s%s%s%s%s", i, i, i, i, i));
            items.add(item);
        }
    }

    private void createTracker() {
        trackerSQL = new TrackerSQL(connection);
    }

    /**
     * creationDateShouldBeEqualSelectedDate
     * <p>
     * tests add().
     */
    @Test
    public void creationDateShouldBeEqualSelectedDate() {
        trackerSQL.add(testItem);
        String query = "SELECT created " +
                "FROM items " +
                "WHERE name = 'testItem';";
        try (Statement statement = connection.createStatement()) {
            ResultSet res = statement.executeQuery(query);
            assertTrue(res.next());
            assertTrue(res.getLong("created") == testItem.getCreated());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * shouldReturnNameIsTestItem
     * tests replace().
     */
    @Test
    public void shouldReturnNameIsTestItem() {
        String testId = "22222";
        testItem.setId(testId);
        trackerSQL.replace(testId, testItem);
        String query = String.format("SELECT name FROM items WHERE id = '%s';", testId);
        try (Statement statement = connection.createStatement()) {
            ResultSet res = statement.executeQuery(query);
            assertTrue(res.next());
            assertTrue(res.getString("name").equals(testItem.getName()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * shouldBeDeletedTheRowWithID22222
     * tests delete().
     */
    @Test
    public void shouldBeDeletedTheRowWithID22222() {
        String preQuery = "SELECT COUNT(*) "
                + "FROM items "
                + "WHERE id = '22222';";
        String postQuery = "SELECT COUNT(*) FROM "
                + "("
                + "SELECT * "
                + "FROM items "
                + "WHERE id = '22222') as s ";
        try (Statement statement = connection.createStatement()) {
            ResultSet res = statement.executeQuery(preQuery);
            assertTrue(res.next());
            assertTrue(res.getInt(1) == 1);
            trackerSQL.delete("22222");
            res = statement.executeQuery(postQuery);
            assertTrue(res.next());
            assertTrue(res.getInt(1) == 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * listfromDBShouldBeEqualToThisItems
     * tests findAll().
     */
    @Test
    public void listfromDBShouldBeEqualToThisItems() {
        assertThat(trackerSQL.findAll(), is(this.items));
    }

    /**
     * whenName2ThenItem2
     * tests findByName().
     */
    @Test
    public void whenName2ThenItem2() {
        assertThat(trackerSQL.findByName("Name2"), is(List.of(items.get(1))));
    }

    /**
     * whenId22222ThenItem2
     * tests findById().
     */
    @Test
    public void whenId22222ThenItem2() {
        assertTrue(trackerSQL.findById("22222").equals(items.get(1)));
    }
}