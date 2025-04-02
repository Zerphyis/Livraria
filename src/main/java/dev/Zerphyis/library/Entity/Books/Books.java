package dev.Zerphyis.library.Entity.Books;

import dev.Zerphyis.library.Entity.Author.Author;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

@Entity
@Table(name = "livros")
public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String title;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Author author;
    @PastOrPresent
    private LocalDate publicationDate;
    @NotBlank
    private String publisher;
    @NotBlank
    private String gender;
    @NotNull
    private Integer quantityAvailable;

    public Books(){

    }

    public Books(String title, LocalDate publicationDate, String publisher, String gender, Integer quantityAvailable, Author author) {
        this.title = title;
        this.publicationDate = publicationDate;
        this.publisher = publisher;
        this.gender = gender;
        this.quantityAvailable = quantityAvailable;
        this.author = author;
    }

    public Books(Long id, String title, Author author, LocalDate publicationDate, String publisher, String gender, Integer quantityAvailable) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
        this.publisher = publisher;
        this.gender = gender;
        this.quantityAvailable = quantityAvailable;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(Integer quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }
}
