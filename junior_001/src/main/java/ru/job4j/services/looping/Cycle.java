package ru.job4j.services.looping;

/**
 * Cycle
 *
 * @author Vladislav Nechaev
 * @since 23.04.2019
 */
public class Cycle {

    /**
     * hasCycle
     *
     * @param first - first item of linked list.
     * @return - true if the linked list has a loop.
     */
    public boolean hasCycle(Node first) {
        if (first == null) {
            return false;
        }
        Node slow = first;
        Node fast = first;
        while (true) {
            slow = slow.next;
            if (fast.next != null) {
                fast = fast.next.next;
            } else {
                return false;
            }
            if (slow == null || fast == null) {
                return false;
            }
            if (slow == fast) {
                return true;
            }
        }
    }
}
