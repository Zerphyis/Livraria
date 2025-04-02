package dev.Zerphyis.library.Repository;

import dev.Zerphyis.library.Entity.Loan.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan,Long> {
}
