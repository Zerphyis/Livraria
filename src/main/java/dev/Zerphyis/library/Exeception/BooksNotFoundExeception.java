package dev.Zerphyis.library.Exeception;

public class BooksNotFoundExeception extends RuntimeException {
    public BooksNotFoundExeception(String message) {
        super(message);
    }
}
