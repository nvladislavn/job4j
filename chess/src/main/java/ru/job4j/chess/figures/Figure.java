package ru.job4j.chess.figures;

public interface Figure {
    Cell position();

    Cell[] way(Cell source, Cell dest);

    default String icon() {
        return String.format(
                "%s.png", this.getClass().getSimpleName()
        );

    }

    /**
     * он должен создавать объект Figure с координатой Cell dest.
     * @param dest
     * @return
     */
    Figure copy(Cell dest);

    default String getFigureName() {
        return String.format(this.getClass().getSimpleName());
    }
}
