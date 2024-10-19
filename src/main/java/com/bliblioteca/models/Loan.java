package com.bliblioteca.models;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Loan implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private User user;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Book book;
    private LocalDate dateLoan;
    private String state;
    private LocalDate devolutionDate;

    public Loan(Long id, User user, Book book, LocalDate dateLoan, String state, LocalDate devolutionDate) {
        this.id = id;
        this.user = user;
        this.book = book;
        this.dateLoan = dateLoan;
        this.state = state;
        this.devolutionDate = devolutionDate;
    }
     public Loan(User user, Book book, LocalDate dateLoan, String state, LocalDate devolutionDate) {
        this.user = user;
        this.book = book;
        this.dateLoan = dateLoan;
        this.state = state;
        this.devolutionDate = devolutionDate;
    }

    public Loan() {
    }
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getDateLoan() {
        return dateLoan;
    }

    public void setDateLoan(LocalDate dateLoan) {
        this.dateLoan = dateLoan;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public LocalDate getDevolutionDate() {
        return devolutionDate;
    }

    public void setDevolutionDate(LocalDate devolutionDate) {
        this.devolutionDate = devolutionDate;
    }

    
    
}
