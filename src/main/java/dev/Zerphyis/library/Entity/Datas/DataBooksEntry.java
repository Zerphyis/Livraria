package dev.Zerphyis.library.Entity.Datas;



import java.time.LocalDate;

public record DataBooksEntry(String title, Long authorId, LocalDate publicationDate, String publisher, String gender,Integer quantityAvailable) {
}
