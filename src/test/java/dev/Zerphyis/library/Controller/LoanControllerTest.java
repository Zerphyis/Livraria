package dev.Zerphyis.library.Controller;


import dev.Zerphyis.library.Entity.Books.Books;
import dev.Zerphyis.library.Entity.Datas.DataLoanEntry;
import dev.Zerphyis.library.Entity.Datas.DataLoanExit;
import dev.Zerphyis.library.Entity.User.Users;
import dev.Zerphyis.library.Service.LoanService;
import dev.Zerphyis.library.Entity.Loan.Loan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
@SpringBootTest
@AutoConfigureMockMvc

class LoanControllerTest {


    private MockMvc mockMvc;

    @Mock
    private LoanService loanService;

    @InjectMocks
    private LoanController loanController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(loanController).build();
    }

    @Test
    public void testCreateLoan() throws Exception {
        DataLoanEntry dataLoanEntry = new DataLoanEntry(1L, 1L, LocalDate.now());

        Books mockBook = new Books();
        mockBook.setTitle("Livro Teste");

        Users mockUser = new Users();
        mockUser.setName("Usuário Teste");

        Loan mockLoan = new Loan(dataLoanEntry, mockBook, mockUser);
        mockLoan.setActualReturnDate(LocalDate.now());
        mockLoan.setFine(BigDecimal.ZERO);

        when(loanService.createLoan(dataLoanEntry)).thenReturn(mockLoan);

        mockMvc.perform(post("/emprestimos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"bookId\": 1, \"userId\": 1, \"dateLoan\": \"2025-04-03\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nameBook").value("Livro Teste"))
                .andExpect(jsonPath("$.userName").value("Usuário Teste"))
                .andExpect(jsonPath("$.fine").value(0));
    }

    @Test
    public void testReturnBook() throws Exception {
        Long loanId = 1L;
        LocalDate returnDate = LocalDate.now();
        DataLoanExit mockDataLoanExit = new DataLoanExit("Livro Teste", "Usuário Teste", LocalDate.now(), LocalDate.now().plusDays(7), returnDate, BigDecimal.ZERO);

        when(loanService.returnBook(loanId, returnDate)).thenReturn(mockDataLoanExit);

        mockMvc.perform(put("/emprestimos/{id}", loanId)
                        .param("returnDate", returnDate.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nameBook").value("Livro Teste"))
                .andExpect(jsonPath("$.userName").value("Usuário Teste"))
                .andExpect(jsonPath("$.fine").value(0));
    }

    @Test
    public void testGetAllLoans() throws Exception {
        DataLoanExit mockDataLoanExit = new DataLoanExit("Livro Teste", "Usuário Teste", LocalDate.now(), LocalDate.now().plusDays(7), LocalDate.now(), BigDecimal.ZERO);

        when(loanService.getAllLoans()).thenReturn(List.of(mockDataLoanExit));

        mockMvc.perform(get("/emprestimos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nameBook").value("Livro Teste"))
                .andExpect(jsonPath("$[0].userName").value("Usuário Teste"))
                .andExpect(jsonPath("$[0].fine").value(0));
    }

    @Test
    public void testDeleteLoan() throws Exception {
        Long loanId = 1L;

        when(loanService.deleteLoan(loanId)).thenReturn("Empréstimo deletado com sucesso");

        mockMvc.perform(delete("/emprestimos/{id}", loanId))
                .andExpect(status().isOk())
                .andExpect((result) ->
                        assertEquals("Empréstimo deletado com sucesso", result.getResponse().getContentAsString()));
    }
}