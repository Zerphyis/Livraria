package dev.Zerphyis.library.Repository;

import dev.Zerphyis.library.Entity.Loan.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<Loan,Long> {
}
