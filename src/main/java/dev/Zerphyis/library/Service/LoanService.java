package dev.Zerphyis.library.Service;

import dev.Zerphyis.library.Entity.Books.Books;
import dev.Zerphyis.library.Entity.Datas.DataLoanEntry;
import dev.Zerphyis.library.Entity.Datas.DataLoanExit;
import dev.Zerphyis.library.Entity.Loan.Loan;
import dev.Zerphyis.library.Entity.User.Users;
import dev.Zerphyis.library.Exeception.BooksNotFoundExeception;
import dev.Zerphyis.library.Exeception.LoanNotFoundExecption;
import dev.Zerphyis.library.Exeception.UserNotFoundExeception;
import dev.Zerphyis.library.Repository.BooksRepository;
import dev.Zerphyis.library.Repository.LoanRepository;
import dev.Zerphyis.library.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanService {
    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private BooksRepository booksRepository;
    @Autowired
    private UsersRepository usersRepository;

    @Transactional
    public Loan createLoan(DataLoanEntry dataLoanEntry) {
        Books book = booksRepository.findById(dataLoanEntry.bookId())
                .orElseThrow(() -> new BooksNotFoundExeception("Livro não encontrado"));

        if (book.getQuantityAvailable() <= 0) {
            throw new BooksNotFoundExeception("Livro indisponível para empréstimo");
        }

        Users user = usersRepository.findById(dataLoanEntry.userId())
                .orElseThrow(() -> new UserNotFoundExeception("Usuário não encontrado"));

        book.setQuantityAvailable(book.getQuantityAvailable() - 1);
        booksRepository.save(book);

        Loan loan = new Loan(dataLoanEntry, book, user);
        return loanRepository.save(loan);
    }

    @Transactional
    public DataLoanExit returnBook(Long loanId, LocalDate returnDate) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new LoanNotFoundExecption("Empréstimo não encontrado"));

        long overdueDays = ChronoUnit.DAYS.between(loan.getExpectedReturnDate(), returnDate);
        loan.setActualReturnDate(returnDate);
        if (overdueDays > 0) {
            loan.setFine(BigDecimal.valueOf(overdueDays * 2.00));
        }

        Books book = loan.getBook();
        book.setQuantityAvailable(book.getQuantityAvailable() + 1);
        booksRepository.save(book);

        loanRepository.save(loan);

        return new DataLoanExit(
                loan.getBook().getTitle(),
                loan.getUsers().getName(),
                loan.getDateLoan(),
                loan.getExpectedReturnDate(),
                loan.getActualReturnDate(),
                loan.getFine()
        );
    }

    public List<DataLoanExit> getAllLoans() {
        return loanRepository.findAll().stream().map(loan ->
                new DataLoanExit(
                        loan.getBook().getTitle(),
                        loan.getUsers().getName(),
                        loan.getDateLoan(),
                        loan.getExpectedReturnDate(),
                        loan.getActualReturnDate(),
                        loan.getFine()
                )
        ).collect(Collectors.toList());
    }

    @Transactional
    public String deleteLoan(Long loanId) {
        if (!loanRepository.existsById(loanId)) {
            throw new LoanNotFoundExecption("Empréstimo não encontrado");
        }
        loanRepository.deleteById(loanId);
        return "Empréstimo deletado com sucesso.";
    }
}

