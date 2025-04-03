package dev.Zerphyis.library.Exeception;

public class LoanNotFoundExecption extends RuntimeException {
    public LoanNotFoundExecption(String message) {
        super(message);
    }
}
