package dev.Zerphyis.library.Entity.Loan;

import jakarta.persistence.*;

@Entity
@Table(name = "emprestimos")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public Loan(){

    }

}
