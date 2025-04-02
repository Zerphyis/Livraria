package dev.Zerphyis.library.Controller;

import dev.Zerphyis.library.Entity.Datas.DataLoanEntry;
import dev.Zerphyis.library.Entity.Datas.DataLoanExit;
import dev.Zerphyis.library.Entity.Loan.Loan;
import dev.Zerphyis.library.Service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/emprestimos")
public class LoanController {
    @Autowired
    private LoanService loanService;

    @PostMapping()
    public ResponseEntity<DataLoanExit> createLoan(@RequestBody DataLoanEntry dataLoanEntry) {
        Loan loan = loanService.createLoan(dataLoanEntry);
        DataLoanExit dataLoanExit = new DataLoanExit(
                loan.getBook().getTitle(),
                loan.getUsers().getName(),
                loan.getDateLoan(),
                loan.getExpectedReturnDate(),
                loan.getActualReturnDate(),
                loan.getFine()
        );
        return ResponseEntity.ok(dataLoanExit);
    }
    @PutMapping("/{id}")
    public ResponseEntity<DataLoanExit> returnBook(@PathVariable Long id, @RequestParam("returnDate") String returnDate) {
        LocalDate parsedReturnDate = LocalDate.parse(returnDate);
        return ResponseEntity.ok(loanService.returnBook(id, parsedReturnDate));
    }

    @GetMapping()
    public ResponseEntity<List<DataLoanExit>> getAllLoans() {
        return ResponseEntity.ok(loanService.getAllLoans());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLoan(@PathVariable Long id) {
        loanService.deleteLoan(id);
        return ResponseEntity.ok("Empréstimo deletado com sucesso");
    }
}
