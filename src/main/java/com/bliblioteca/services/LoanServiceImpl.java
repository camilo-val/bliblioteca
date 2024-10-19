
package com.bliblioteca.services;

import com.bliblioteca.models.Loan;
import com.bliblioteca.repositorie.LoanJpaController;
import com.bliblioteca.repositorie.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LoanServiceImpl implements LoanService{

private static final long serialVersionUID = 1L; 
    
    private final LoanJpaController repository = new LoanJpaController();
    private BookService service = new BookServiceImpl();

    
    @Override
    public List<Loan> findAll() {
           return repository.findLoanEntities();
    }

    @Override
    public Optional<Loan> findById(Long id) {
        return Optional.ofNullable(repository.findLoan(id));
    }
    
    @Override
    public Boolean create(Loan Loan) {
       repository.create(Loan);
       return true;
    }
    
    @Override
    public Boolean modified(Loan Loan) {
        Optional<Loan> LoandB = findById(Loan.getId());
        if(LoandB.isPresent()){
            try {
                repository.edit(Loan);
                return true;
            } catch (Exception ex) {
                return false;
            }
        }
        return false;
    }

    @Override
    public Boolean destroy(Long id) {
                Optional<Loan> LoandB = findById(id);
        if(LoandB.isPresent()){
            try {
                repository.destroy(id);
                return true;
            } catch (NonexistentEntityException ex) {
                return false;            
            }
        }
        return false;
    }

    @Override
    public List<Loan> findAllHistory() {
        List<Loan> loansDB = findAll();
        List<Loan> loansHistory = new ArrayList<>();

        if(!loansDB.isEmpty() && loansDB != null){
             for(Loan loanHistory : loansDB){
                 if(!loanHistory.getState().equals("En prestamo")){
                     loansHistory.add(loanHistory);
                 }
             }
        }
        return loansHistory;
    }
    @Override
    public List<Loan> findAllActive() {
        List<Loan> loansDB = findAll();
        List<Loan> loansHistory = new ArrayList<>();

        if(!loansDB.isEmpty() && loansDB != null){
             for(Loan loanHistory : loansDB){
                 if(loanHistory.getState().equals("En prestamo")){
                     loansHistory.add(loanHistory);
                 }
             }
        }
        return loansHistory;
    }

    @Override
    public Optional<Loan> findByIdUser(Long l) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Optional<Loan> findByIdBook(Long l) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
