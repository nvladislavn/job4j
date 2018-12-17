package job4j.chess.GameException;

public class OccupiedWayException extends RuntimeException {


    public OccupiedWayException() {
    }

    public OccupiedWayException(String message) {
        super(message);
    }
}
