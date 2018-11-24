package ru.job4j.tracker;

public interface Input {

    String ask(String message);
    int ask(String message, int[] range);
}
