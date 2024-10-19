
package com.bliblioteca.services;

import com.bliblioteca.models.Loan;
import java.util.List;
import java.util.Optional;

public interface LoanService {
    List<Loan> findAll();
    Optional<Loan> findById(Long id);
    Optional<Loan> findByIdUser(Long id);
    Optional<Loan> findByIdBook(Long id);
    List<Loan> findAllHistory();
    List<Loan> findAllActive();
    Boolean create(Loan loan);
    Boolean modified(Loan loan);
    Boolean destroy(Long id);
}
