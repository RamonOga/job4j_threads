package ru.job4j.cuncurrentmap;

public class OptimisticException extends RuntimeException {
    public OptimisticException(String message) {
        super(message);
    }
}
