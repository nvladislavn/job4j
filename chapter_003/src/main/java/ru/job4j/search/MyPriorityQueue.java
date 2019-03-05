package ru.job4j.search;

import java.util.*;

/**
 * MyPriorityQueue.
 *
 * @author Vladislav Nechaev
 * @version $Id$
 * @since 07.01.2018
 */
public class MyPriorityQueue {

    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * put
     *
     * adds the instance of Task to tasks
     *
     * @param task
     */
    public void put(Task task) {
        for (var i = 0; i < this.tasks.size(); i++) {
                if (task.getPriority() <= this.tasks.get(i).getPriority()) {
                    this.tasks.add(i, task);
                    return;
                }
            }
            this.tasks.add(task);
    }

    public Task take() {
        return this.tasks.poll();
    }
}
