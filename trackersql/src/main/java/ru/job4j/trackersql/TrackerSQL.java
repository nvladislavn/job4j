package ru.job4j.trackersql;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.tracker.ITracker;
import ru.job4j.tracker.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * TrackerSQL
 *
 * @author Vladislav Nechaev
 * @since 12.09.2019
 */
public class TrackerSQL implements ITracker, AutoCloseable {

    private static final Logger LOG = LogManager.getLogger(TrackerSQL.class.getName());
    private Connection connection;

    public TrackerSQL(Connection connection) {
        this.connection = connection;
    }

    /**
     * The method add.
     *
     * @param item - the instance of Item.
     * @return the instance of Item with id.
     */
    @Override
    public Item add(Item item) {
        if (connection != null && item != null) {
            String sqlStatement =
                    "INSERT INTO items(id, name, description, created)"
                            + "VALUES (?, ?, ?, ?)";
            try (PreparedStatement pStatement =
                         connection.prepareStatement(sqlStatement)) {
                item.setId(generateId());
                pStatement.setString(1, item.getId());
                pStatement.setString(2, item.getName());
                pStatement.setString(3, item.getDesc());
                pStatement.setLong(4, getCreateDate(item));
                pStatement.executeUpdate();
            } catch (Exception e) {
                LOG.error(e.getMessage(), e);
            }
        }
        return item;
    }

    /**
     * replace.
     * This method changes item with the specified id.
     *
     * @param id   - the specified item id.
     * @param item - the item to replace.
     */
    @Override
    public boolean replace(String id, Item item) {
        boolean result = false;
        if (connection != null && item != null && id != null) {
            String sqlStatement =
                    "UPDATE items"
                            + " SET name = ?,"
                            + " description = ?,"
                            + " created = ?"
                            + " WHERE id = ?;";
            try (PreparedStatement pStatement = connection.prepareStatement(sqlStatement)) {
                pStatement.setString(1, item.getName());
                pStatement.setString(2, item.getDesc());
                pStatement.setLong(3, getCreateDate(item));
                pStatement.setString(4, id);
                pStatement.executeUpdate();
                result = true;
            } catch (Exception e) {
                LOG.error(e.getMessage(), e);
            }
        }
        return result;
    }

    /**
     * delete.
     * This method deletes the item with the specified id.
     *
     * @param id - the item id for the item to be deleted.
     */
    @Override
    public boolean delete(String id) {
        boolean result = false;
        if (connection != null && id != null) {
            String sqlStatement = String.format("DELETE FROM items WHERE id = '%s';", id);
            try (Statement statement = connection.createStatement()) {
                statement.execute(sqlStatement);
                result = true;
            } catch (Exception e) {
                LOG.error(e.getMessage(), e);
            }
        }
        return result;
    }

    /**
     * findAll.
     * This method returns the List non-null objects.
     *
     * @return the List non-null objects.
     */
    @Override
    public List<Item> findAll() {
        List<Item> items = new ArrayList<>();
        String query = "SELECT * FROM items;";
        try (Statement statement = connection.createStatement()) {
            items = getItemList(statement.executeQuery(query));
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return items;
    }

    /**
     * findByName.
     * This method returns the List of items with the specified name.
     *
     * @param key - the given name.
     * @return the array of items with the given name.
     */
    @Override
    public List<Item> findByName(String key) {
        List<Item> items = new ArrayList<>();
        String query = String.format("SELECT * FROM items WHERE name = '%s';", key);
        try (Statement statement = connection.createStatement()) {
            items = getItemList(statement.executeQuery(query));
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return items;
    }

    /**
     * findById.
     * This method returns the item with the specified id.
     *
     * @param id - the id for item to be find.
     * @return the item with the specified id.
     */
    @Override
    public Item findById(String id) {
        Item result = null;
        String query = String.format("SELECT * FROM items WHERE id = '%s'", id);
        try (Statement statement = connection.createStatement()) {
            result = getItemList(statement.executeQuery(query)).get(0);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }

        return result;
    }

    /**
     * getItemList.
     *
     * @param res - result set from sql query.
     * @return - the item List from resultSet.
     * @throws - SQLException
     */
    private List<Item> getItemList(ResultSet res) throws SQLException {
        List<Item> items = new ArrayList<>();
        while (res.next()) {
            Item item = new Item(
                    res.getString("name"),
                    res.getString("description"),
                    res.getLong("created")
            );
            item.setId(res.getString("id"));
            items.add(item);
        }
        return items;
    }

    /**
     * getCreateDate.
     *
     * @param item - the item to which you need to set the creation time.
     * @return - the item creation time in milliseconds.
     */
    private long getCreateDate(Item item) {
        long created = item.getCreated();
        return created == 0 ? System.currentTimeMillis() : created;
    }

    @Override
    public void close() {
        try {
            if (connection != null) {
                this.connection.close();
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
