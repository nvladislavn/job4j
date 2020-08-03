package ru.job4j.xoGame.player;

import ru.job4j.xoGame.logic.StopException;

public interface Player {

    String getName();
    void play() throws IllegalArgumentException, StopException;
}
