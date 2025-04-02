package dev.Zerphyis.library.Entity.Datas;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DataLoanExit(String nameBook, String userName, LocalDate dateLoan, LocalDate  expectedReturnDate, LocalDate ActualReturnDate,
                           BigDecimal fine) {
}
