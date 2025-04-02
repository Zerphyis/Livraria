package dev.Zerphyis.library.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import dev.Zerphyis.library.Entity.Books.Books;
import dev.Zerphyis.library.Entity.Datas.DataLoanEntry;
import dev.Zerphyis.library.Entity.Datas.DataLoanExit;
import dev.Zerphyis.library.Entity.Datas.DataUsers;
import dev.Zerphyis.library.Entity.Loan.Loan;
import dev.Zerphyis.library.Entity.User.Users;
import dev.Zerphyis.library.Repository.BooksRepository;
import dev.Zerphyis.library.Repository.LoanRepository;
import dev.Zerphyis.library.Repository.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class LoanServiceTest {
    @Mock
    private LoanRepository loanRepository;
    @Mock
    private BooksRepository booksRepository;
    @Mock
    private UsersRepository usersRepository;

    @InjectMocks
    private LoanService loanService;

    private Books book;
    private Users user;
    private Loan loan;
    private DataLoanEntry dataLoanEntry;

    @BeforeEach
    void setUp() {
        book = new Books("Clean Code", LocalDate.of(2008, 8, 1), "Prentice Hall", "Programming", 5, null);
        user = new Users(new DataUsers("John Doe", "john@example.com", "123456789"));

        dataLoanEntry = new DataLoanEntry(1L, 1L, LocalDate.now());
        loan = new Loan(dataLoanEntry, book, user);
    }

    @Test
    void testCreateLoan() {
        when(booksRepository.findById(1L)).thenReturn(Optional.of(book));
        when(usersRepository.findById(1L)).thenReturn(Optional.of(user));
        when(loanRepository.save(any(Loan.class))).thenReturn(loan);

        Loan createdLoan = loanService.createLoan(dataLoanEntry);

        assertNotNull(createdLoan);
        assertEquals(book.getTitle(), createdLoan.getBook().getTitle());
        assertEquals(user.getName(), createdLoan.getUsers().getName());
    }

    @Test
    void testReturnBook() {
        when(loanRepository.findById(1L)).thenReturn(Optional.of(loan));
        when(booksRepository.save(any(Books.class))).thenReturn(book);
        when(loanRepository.save(any(Loan.class))).thenReturn(loan);

        DataLoanExit dataLoanExit = loanService.returnBook(1L, LocalDate.now().plusDays(10));

        assertNotNull(dataLoanExit);
        assertEquals(book.getTitle(), dataLoanExit.nameBook());
        assertEquals(user.getName(), dataLoanExit.userName());
        assertNotNull(dataLoanExit.fine(), "A multa não deve ser null");
        assertTrue(dataLoanExit.fine().compareTo(BigDecimal.ZERO) >= 0);
    }
}