package ru.job4j.sql;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * LogForTest
 *
 * @author Vladislav Nechaev
 * @since 11.09.2019
 */
public class LogForTest {

    private static final Logger LOG = LogManager.getLogger(LogForTest.class.getName());
    private static final String PREF = "Test:";

    public static void main(String[] args) {
        LOG.trace(String.format("%s %s", PREF, "trace"));
        LOG.debug(String.format("%s %s", PREF, "debug"));
        LOG.info(String.format("%s %s", PREF, "info"));
        LOG.warn(String.format("%s %s", PREF, "warn"));
        LOG.error(String.format("%s %s", PREF, "error"));
    }
}
