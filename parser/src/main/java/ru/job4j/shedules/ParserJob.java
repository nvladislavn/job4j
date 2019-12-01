package ru.job4j.shedules;

import ru.job4j.Item;
import ru.job4j.config.Config;
import ru.job4j.db.SQLStore;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import ru.job4j.parsers.ParserSQLRu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * ParserJob
 *
 * @author Vladislav Nechaev
 * @since 22.11.2019
 */
public class ParserJob implements Job {

    private static final Logger LOG = LogManager.getLogger(ParserJob.class.getName());

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Config config = new Config();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    config.getProperties("url"),
                    config.getProperties("username"),
                    config.getProperties("password")
            );
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        SQLStore store = new SQLStore(connection);
        ParserSQLRu parser = new ParserSQLRu(store.getLastEntryDate());
        List<Item> items = parser.parse();
        try {
            store.addItems(items);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
