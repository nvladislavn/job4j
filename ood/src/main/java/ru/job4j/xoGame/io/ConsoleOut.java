package ru.job4j.xoGame.io;

public class ConsoleOut implements Output {

    @Override
    public void write(String text) {
        System.out.println(text);
    }
}
