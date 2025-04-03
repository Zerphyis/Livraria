package dev.Zerphyis.library.Controller;

import dev.Zerphyis.library.Entity.Author.Author;
import dev.Zerphyis.library.Entity.Datas.DataAuthor;
import dev.Zerphyis.library.Exeception.AuthorNotFoundExeception;
import dev.Zerphyis.library.Service.AuthoresService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Optional;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class AuthorControllerTest {
    @Mock
    private AuthoresService service;

    @InjectMocks
    private AuthorController authorController;

    private MockMvc mockMvc;

    private LocalDate birthDate = LocalDate.of(1990, 1, 1);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(authorController).build();
    }

    @Test
    void testListAll() throws Exception {
        List<Author> authors = List.of(
                new Author(1L, "John", "Doe", birthDate),
                new Author(2L, "Jane", "Doe", LocalDate.of(1992, 5, 10))
        );

        when(service.getAllAuthors()).thenReturn(authors);

        mockMvc.perform(get("/autores"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("John"))
                .andExpect(jsonPath("$[1].name").value("Jane"));

        verify(service, times(1)).getAllAuthors();
    }

    @Test
    void testListById() throws Exception {
        Author author = new Author(1L, "John", "Doe", birthDate);

        when(service.getAuthorById(1L)).thenReturn(Optional.of(author));

        mockMvc.perform(get("/autores/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John"));

        verify(service, times(1)).getAuthorById(1L);
    }

    @Test
    void testListByIdNotFound() throws Exception {
        when(service.getAuthorById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/autores/1"))
                .andExpect(status().isNotFound());

        verify(service, times(1)).getAuthorById(1L);
    }

    @Test
    void testUpdate() throws Exception {
        DataAuthor data = new DataAuthor("John", "Doe", birthDate);
        Author updatedAuthor = new Author(1L, "John", "Doe", birthDate);

        when(service.updateAuthor(1L, data)).thenReturn(Optional.of(updatedAuthor));

        mockMvc.perform(put("/autores/1")
                        .contentType("application/json")
                        .content("{\"name\": \"John\", \"nationality\": \"Doe\", \"dateBirth\": \"1990-01-01\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John"));

        verify(service, times(1)).updateAuthor(1L, data);
    }

    @Test
    void testUpdateNotFound() throws Exception {
        DataAuthor data = new DataAuthor("John", "Doe", birthDate);

        when(service.updateAuthor(1L, data)).thenReturn(Optional.empty());

        mockMvc.perform(put("/autores/1")
                        .contentType("application/json")
                        .content("{\"name\": \"John\", \"nationality\": \"Doe\", \"dateBirth\": \"1990-01-01\"}"))
                .andExpect(status().isNotFound());

        verify(service, times(1)).updateAuthor(1L, data);
    }

    @Test
    void testDelete() throws Exception {
        doNothing().when(service).deleteAuthor(1L);

        mockMvc.perform(delete("/autores/1"))
                .andExpect(status().isNoContent());

        verify(service, times(1)).deleteAuthor(1L);
    }

    @Test
    void testDeleteNotFound() throws Exception {
        doThrow(new AuthorNotFoundExeception("Autor com ID 1 não encontrado")).when(service).deleteAuthor(1L);

        mockMvc.perform(delete("/autores/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Autor com ID 1 não encontrado"));

        verify(service, times(1)).deleteAuthor(1L);
    }
}