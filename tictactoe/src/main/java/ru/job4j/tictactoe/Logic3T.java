package ru.job4j.tictactoe;

import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Logic3T {
    private final Figure3T[][] table;

    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    private boolean isFillBy(Predicate<Figure3T> predicate, int startX, int startY, int deltaX, int deltaY) {
        boolean result = true;
        for (int i = 0; i < this.table.length; i++) {
            Figure3T figure = table[startY][startX];
            if (!predicate.test(figure)) {
                return false;
            }
            startX += deltaX;
            startY += deltaY;
        }
        return result;
    }

    private boolean isWinner(Predicate<Figure3T> predicate) {
        return isFillBy(predicate, 0, 0, 1, 0)
                || isFillBy(predicate, 0, 1, 1, 0)
                || isFillBy(predicate, 0, 2, 1, 0)
                || isFillBy(predicate, 0, 0, 0, 1)
                || isFillBy(predicate, 1, 0, 0, 1)
                || isFillBy(predicate, 2, 0, 0, 1)
                || isFillBy(predicate, 0, 0, 1, 1)
                || isFillBy(predicate, this.table.length - 1, 0, -1, 1);
    }

    public boolean isWinnerX() {
        return isWinner(Figure3T::hasMarkX);
    }

    public boolean isWinnerO() {
        return isWinner(Figure3T::hasMarkO);
    }

    public boolean hasGap() {
        return Stream
                .of(table)
                .flatMap(Stream::of)
                .filter(f -> !f.hasMarkX())
                .filter(f -> !f.hasMarkO())
                .collect(Collectors.toList())
                .size() > 0;
    }
}
