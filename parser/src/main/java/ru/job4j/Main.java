package ru.job4j;

import ru.job4j.config.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import ru.job4j.shedules.ParserJob;

/**
 * Main
 *
 * @author Vladislav Nechaev
 * @since 29.11.2019
 */
public class Main {

    private static final Logger LOG = LogManager.getLogger(Main.class.getName());

    /**
     * start
     * starts work the scheduler
     *
     * @throws SchedulerException
     */
    private void start() throws SchedulerException {
        JobDetail job = JobBuilder.newJob(ParserJob.class).build();
        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("ParserTrigger")
                .withSchedule(CronScheduleBuilder.cronSchedule(new Config().getProperties("cronExpression")))
                .build();
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.start();
        scheduler.scheduleJob(job, trigger);
    }

    public static void main(String[] args) {
        Main main = new Main();
        try {
            main.start();
        } catch (SchedulerException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
