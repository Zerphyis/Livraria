package dev.Zerphyis.library.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import dev.Zerphyis.library.Entity.Author.Author;
import dev.Zerphyis.library.Entity.Datas.DataAuthor;
import dev.Zerphyis.library.Repository.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class AuthoresServiceTest {
    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthoresService authoresService;
    LocalDate birthDate = LocalDate.of(1965, 7, 31);

    private Author author;
    private DataAuthor dataAuthor;

    @BeforeEach
    void setUp() {
        LocalDate birthDate = LocalDate.of(1965, 7, 31);
        dataAuthor = new DataAuthor("J.K. Rowling", "British", birthDate);
        author = new Author(dataAuthor);
    }

    @Test
    void testRegisterAuthor() {
        when(authorRepository.save(any(Author.class))).thenReturn(author);
        Author savedAuthor = authoresService.registerAuthor(dataAuthor);

        assertNotNull(savedAuthor);
        assertEquals("J.K. Rowling", savedAuthor.getName());
        assertEquals(LocalDate.of(1965, 7, 31), savedAuthor.getDateBirth());
    }

    @Test
    void testGetAllAuthors() {
        when(authorRepository.findAll()).thenReturn(Arrays.asList(author));
        List<Author> authors = authoresService.getAllAuthors();

        assertFalse(authors.isEmpty());
        assertEquals(1, authors.size());
        assertEquals("J.K. Rowling", authors.get(0).getName());
    }

    @Test
    void testGetAuthorById() {
        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));
        Optional<Author> foundAuthor = authoresService.getAuthorById(1L);

        assertTrue(foundAuthor.isPresent());
        assertEquals("J.K. Rowling", foundAuthor.get().getName());
        assertEquals(LocalDate.of(1965, 7, 31), foundAuthor.get().getDateBirth());
    }

    @Test
    void testDeleteAuthor() {
        when(authorRepository.existsById(1L)).thenReturn(true);
        doNothing().when(authorRepository).deleteById(1L);

        assertDoesNotThrow(() -> authoresService.deleteAuthor(1L));
        verify(authorRepository, times(1)).deleteById(1L);
    }
}