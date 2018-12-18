//package job4j.chess;
//
//import Cell;
//import Figure;
//import job4j.chess.GameException.*;
//
//public class Board {
//
//    private int item;
//    private Figure[] figures = new Figure[32];
//
//    public void add(Figure figure) {
//        this.figures[item++] = figure;
//    }
//
//    public boolean move(Cell source, Cell dest) throws ImpossibleMoveException,
//                                                        OccupiedWayException,
//                                                        FigureNotFoundException {
//        int position = findByCell(source);
//        if (position == -1) {
//            throw new FigureNotFoundException();
//        }
//        Figure figure = this.figures[position];
//        if (!isPossibleWay(figure.way(source, dest))) {
//            throw new OccupiedWayException();
//        }
//        this.figures[position] = figure.copy(dest);
//        return true;
//    }
//
//    private boolean isPossibleWay(Cell[] way) {
//        boolean isPossible = true;
//        int count = 0;
//        while (isPossible && count < way.length) {
//            for (Figure figure : this.figures) {
//                if (figure != null && figure.position().equals(way[count])) {
//                    isPossible = false;
//                    break;
//                }
//            }
//            count++;
//        }
//        return isPossible;
//    }
//
//    /**
//     * findByCell
//     *
//     * @param source - cell
//     * @return - position of the figure in the figures.
//     */
//    private int findByCell(Cell source) {
//        int res = -1;
//        for (int i = 0; i < this.figures.length; i++) {
//            if (this.figures[i] != null && this.figures[i].position().equals(source)) {
//                res = i;
//                break;
//            }
//        }
//        return res;
//    }
//}
