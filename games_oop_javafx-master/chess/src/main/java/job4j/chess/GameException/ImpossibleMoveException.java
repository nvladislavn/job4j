package job4j.chess.GameException;

public class ImpossibleMoveException extends RuntimeException {

    public ImpossibleMoveException() {
    }

    public ImpossibleMoveException(String message) {
        super(message);
    }
}
