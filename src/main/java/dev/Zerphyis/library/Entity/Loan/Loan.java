package dev.Zerphyis.library.Entity.Loan;

import dev.Zerphyis.library.Entity.Books.Books;
import dev.Zerphyis.library.Entity.Datas.DataLoanEntry;
import dev.Zerphyis.library.Entity.User.Users;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "emprestimos")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "livros_id")
    private Books book;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "usuarios_id")
    private Users users;
    @NotNull
    private LocalDate dateLoan;
    @Future
    private LocalDate expectedReturnDate;
    @Future
    private LocalDate ActualReturnDate;
    private BigDecimal fine;

    public Loan(){

    }
    public Loan(DataLoanEntry dataLoanEntry, Books book, Users users) {
        this.book = book;
        this.users = users;
        this.dateLoan = dataLoanEntry.dateLoan();
        this.expectedReturnDate = this.dateLoan.plusDays(14);
    }


    public Books getBook() {
        return book;
    }

    public void setBook(Books book) {
        this.book = book;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public LocalDate getDateLoan() {
        return dateLoan;
    }

    public void setDateLoan(LocalDate dateLoan) {
        this.dateLoan = dateLoan;
    }

    public LocalDate getExpectedReturnDate() {
        return expectedReturnDate;
    }

    public void setExpectedReturnDate(LocalDate expectedReturnDate) {
        this.expectedReturnDate = expectedReturnDate;
    }

    public LocalDate getActualReturnDate() {
        return ActualReturnDate;
    }

    public void setActualReturnDate(LocalDate actualReturnDate) {
        ActualReturnDate = actualReturnDate;
    }

    public BigDecimal getFine() {
        return fine;
    }

    public void setFine(BigDecimal fine) {
        this.fine = fine;
    }
}
