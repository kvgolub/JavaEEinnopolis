package ru.innopolis.exception;

public class NoIdObjectException extends IndexOutOfBoundsException {
    public NoIdObjectException(String message) {
        super(message);
    }
}
