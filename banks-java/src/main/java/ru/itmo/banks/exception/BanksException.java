package ru.itmo.banks.exception;

public class BanksException extends RuntimeException {
    public BanksException(String message) {
        super(message);
    }
}
