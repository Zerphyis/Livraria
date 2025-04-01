package dev.Zerphyis.library.Entity.User;

import dev.Zerphyis.library.Entity.Datas.DataUsers;
import dev.Zerphyis.library.Entity.Loan.Loan;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Table(name = "usuarios")
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String phone;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "emprestimos_id")
    private List<Loan> loansActive;

    public Users(){

    }
    public Users(DataUsers data){
        this.name= data.name();
        this.email= data.email();
        this.phone= data.phone();

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Loan> getLoansActive() {
        return loansActive;
    }

    public void setLoansActive(List<Loan> loansActive) {
        this.loansActive = loansActive;
    }
}
