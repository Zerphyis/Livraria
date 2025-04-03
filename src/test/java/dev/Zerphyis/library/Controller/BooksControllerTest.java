package dev.Zerphyis.library.Controller;

import dev.Zerphyis.library.Entity.Datas.Books.DataBooksEntry;
import dev.Zerphyis.library.Entity.Datas.Books.DataBooksExit;
import dev.Zerphyis.library.Service.BooksService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class BooksControllerTest {
    private MockMvc mockMvc;

    @Mock
    private BooksService booksService;

    @InjectMocks
    private BooksController booksController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(booksController).build();
    }

    @Test
    void testListAll() throws Exception {
        DataBooksExit book1 = new DataBooksExit(
                "Title 1", "Author 1", LocalDate.of(2025, 1, 1), "Publisher 1", "Fiction", 5
        );
        DataBooksExit book2 = new DataBooksExit(
                "Title 2", "Author 2", LocalDate.of(2025, 1, 2), "Publisher 2", "Non-fiction", 3
        );

        when(booksService.getAllBooks()).thenReturn(List.of(book1, book2));

        mockMvc.perform(get("/livros"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Title 1"))
                .andExpect(jsonPath("$[1].title").value("Title 2"));
    }

    @Test
    void testListById() throws Exception {
        DataBooksExit dataBooksExit = new DataBooksExit(
                "Title", "Author Name", LocalDate.of(2025, 1, 1), "Publisher", "Fiction", 10
        );

        when(booksService.getBookById(1L)).thenReturn(Optional.of(dataBooksExit));

        mockMvc.perform(get("/livros/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Title"));
    }

    @Test
    void testUpdate() throws Exception {
        DataBooksEntry dataBooksEntry = new DataBooksEntry(
                "Updated Title", 1L, LocalDate.of(2025, 1, 1), "Updated Publisher", "Non-fiction", 8
        );

        DataBooksExit dataBooksExit = new DataBooksExit(
                "Updated Title", "Updated Author", LocalDate.of(2025, 1, 1), "Updated Publisher", "Non-fiction", 8
        );

        when(booksService.updateBook(1L, dataBooksEntry)).thenReturn(Optional.of(dataBooksExit));

        mockMvc.perform(put("/livros/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"title\": \"Updated Title\", \"publicationDate\": \"2025-01-01\", \"publisher\": \"Updated Publisher\", \"gender\": \"Non-fiction\", \"quantityAvailable\": 8, \"authorId\": 1 }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Title"));
    }

    @Test
    void testDelete() throws Exception {
        doNothing().when(booksService).deleteBook(1L);

        mockMvc.perform(delete("/livros/1"))
                .andExpect(status().isNoContent());
    }
}