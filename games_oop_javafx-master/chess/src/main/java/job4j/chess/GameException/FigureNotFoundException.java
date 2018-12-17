package job4j.chess.GameException;

public class FigureNotFoundException extends RuntimeException {

    public FigureNotFoundException() {

    }

    public FigureNotFoundException(String message) {
        super(message);
    }
}
