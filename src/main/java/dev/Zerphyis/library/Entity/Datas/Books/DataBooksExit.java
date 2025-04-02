package dev.Zerphyis.library.Entity.Datas.Books;



import java.time.LocalDate;

public record DataBooksExit(String title, String authorName, LocalDate publicationDate, String publisher, String gender, Integer quantityAvailable) {
}
