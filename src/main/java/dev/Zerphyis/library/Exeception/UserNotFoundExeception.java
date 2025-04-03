package dev.Zerphyis.library.Exeception;

public class UserNotFoundExeception extends RuntimeException {
    public UserNotFoundExeception(String message) {
        super(message);
    }
}
