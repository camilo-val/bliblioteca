package com.bliblioteca.services;

import com.bliblioteca.models.Book;
import com.bliblioteca.repositorie.BookJpaController;
import com.bliblioteca.repositorie.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public class BookServiceImpl implements BookService, Serializable {

    private static final long serialVersionUID = 1L;

    private final BookJpaController repository = new BookJpaController();

    @Override
    public List<Book> findAll() {
        return repository.findBookEntities();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return Optional.ofNullable(repository.findBook(id));
    }

    @Override
    public Boolean create(Book book) {
        repository.create(book);
        return true;
    }

    @Override
    public Boolean modified(Book book) {
        Optional<Book> bookDB = findById(book.getId());
        if (bookDB.isPresent()) {
            try {
                repository.edit(book);
                return true;
            } catch (Exception ex) {
                ex.printStackTrace();
                return false;
            }
        }
        return false;
    }

    @Override
    public Boolean destroy(Long id) {
        Optional<Book> bookDB = findById(id);
        if (bookDB.isPresent()) {
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
    public List<Book> findByParameter(String search) {
        List<Book> response = repository.findBooWithParameter("title", search);
        try {
            if (response != null && !response.isEmpty()) {
                return response;
            }
            response = repository.findBooWithParameter("author", search);
            if (response != null && !response.isEmpty()) {
                return response;
            }
            response = repository.findBooWithParameter("gender", search);
            if (response != null && !response.isEmpty()) {
                return response;
            }
        } catch (Exception e) {
            System.out.println("ENTRE ERROR");
            return null;
        }
        return null;
    }

}
