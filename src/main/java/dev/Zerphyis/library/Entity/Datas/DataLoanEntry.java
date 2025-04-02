package dev.Zerphyis.library.Entity.Datas;

import java.time.LocalDate;

public record DataLoanEntry(Long bookId, Long userId, LocalDate dateLoan) {
}
