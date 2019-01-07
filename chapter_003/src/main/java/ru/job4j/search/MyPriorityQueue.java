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

    public void put(Task task) {
        tasks.add(task);
        Collections.sort(tasks, this.new PriorityComparator());
    }

    public Task take() {
        return this.tasks.poll();
    }

    private class PriorityComparator implements Comparator<Task> {

        @Override
        public int compare(Task task1, Task task2) {
            return task1.getPriority() - task2.getPriority();
        }
    }
}
