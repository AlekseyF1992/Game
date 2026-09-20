package ru.netology.javaqa;

public class NotRegisteredException extends RuntimeException {
    public NotRegisteredException(String playerName) {
        super("Player '" + playerName + "' is not registered");
    }
}