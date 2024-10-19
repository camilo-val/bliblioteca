package com.bliblioteca.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String author;
    private LocalDate yearPublication;
    private String isbn;
    private String gender;
    private Boolean state;
    @OneToMany(mappedBy = "book")
    private List<Loan> loans;

    public Book() {
    }

    public Book(Long id, String title, String author, LocalDate yearPublication, String isbn, String gender, Boolean state) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.yearPublication = yearPublication;
        this.isbn = isbn;
        this.gender = gender;
        this.state = state;
    }
    
        public Book( String title, String author, LocalDate yearPublication, String isbn, String gender, Boolean state) {
        this.title = title;
        this.author = author;
        this.yearPublication = yearPublication;
        this.isbn = isbn;
        this.gender = gender;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getYearPublication() {
        return yearPublication;
    }

    public void setYearPublication(LocalDate yearPublication) {
        this.yearPublication = yearPublication;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

}
