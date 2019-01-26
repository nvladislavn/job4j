package ru.job4j.bank;

/**
 * @author Vladislav Nechaev
 * @since 26.01.2019
 */
public class TransferException extends RuntimeException {

    public TransferException(String message) {
        super(message);
    }
}
