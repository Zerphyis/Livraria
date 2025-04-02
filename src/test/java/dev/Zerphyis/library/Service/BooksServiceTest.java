package dev.Zerphyis.library.Service;

import static org.junit.jupiter.api.Assertions.*;
import dev.Zerphyis.library.Entity.Author.Author;
import dev.Zerphyis.library.Entity.Books.Books;
import dev.Zerphyis.library.Entity.Datas.Books.DataBooksEntry;
import dev.Zerphyis.library.Entity.Datas.Books.DataBooksExit;
import dev.Zerphyis.library.Repository.AuthorRepository;
import dev.Zerphyis.library.Repository.BooksRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

class BooksServiceTest {
    @Mock
    private BooksRepository booksRepository;

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private BooksService booksService;

    private DataBooksEntry dataBooksEntry;
    private Books book;
    private Author author;

    @BeforeEach
    void setUp() {
        author = new Author(1L, "John Doe", "USA", LocalDate.of(1980, 5, 10));

        dataBooksEntry = new DataBooksEntry(
                "Book Title", 1L, LocalDate.now(), "Publisher", "Fiction", 10
        );

        book = new Books(1L, "Book Title", author, LocalDate.now(), "Publisher", "Fiction", 10);
    }

    @Test
    void shouldRegisterBook() {
        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));
        when(booksRepository.save(any(Books.class))).thenReturn(book);

        DataBooksExit result = booksService.registerBook(dataBooksEntry);

        assertNotNull(result);
        assertEquals("Book Title", result.title());
        assertEquals("John Doe", result.authorName());
        verify(booksRepository).save(any(Books.class));
    }

    @Test
    void shouldGetAllBooks() {
        when(booksRepository.findAll()).thenReturn(List.of(book));

        List<DataBooksExit> result = booksService.getAllBooks();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals("Book Title", result.get(0).title());
    }

    @Test
    void shouldGetBookById() {
        when(booksRepository.findById(1L)).thenReturn(Optional.of(book));

        Optional<DataBooksExit> result = booksService.getBookById(1L);

        assertTrue(result.isPresent());
        assertEquals("Book Title", result.get().title());
    }

    @Test
    void shouldUpdateBook() {
        when(booksRepository.findById(1L)).thenReturn(Optional.of(book));
        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));
        when(booksRepository.save(any(Books.class))).thenReturn(book);

        Optional<DataBooksExit> result = booksService.updateBook(1L, dataBooksEntry);

        assertTrue(result.isPresent());
        assertEquals("Book Title", result.get().title());
        assertEquals("John Doe", result.get().authorName());
    }

    @Test
    void shouldDeleteBook() {
        when(booksRepository.existsById(1L)).thenReturn(true);
        doNothing().when(booksRepository).deleteById(1L);

        assertDoesNotThrow(() -> booksService.deleteBook(1L));
        verify(booksRepository).deleteById(1L);
    }

    @Test
    void shouldThrowExceptionWhenAuthorNotFound() {
        when(authorRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            booksService.registerBook(dataBooksEntry);
        });

        assertEquals("Autor não encontrado", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenBookNotFoundForUpdate() {
        when(booksRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            booksService.updateBook(1L, dataBooksEntry);
        });

        assertEquals("Livro com ID 1 não encontrado.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenDeletingNonexistentBook() {
        when(booksRepository.existsById(1L)).thenReturn(false);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            booksService.deleteBook(1L);
        });

        assertEquals("Livro com ID 1 não encontrado.", exception.getMessage());
    }
}