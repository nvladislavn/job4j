package ru.job4j.xoGame.io;

import java.util.Scanner;

public class ConsoleIn implements Input {

    private Scanner scanner = new Scanner(System.in);

    @Override
    public String read() {
        return scanner.nextLine();
    }
}
