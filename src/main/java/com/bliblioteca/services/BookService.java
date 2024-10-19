
package com.bliblioteca.services;

import com.bliblioteca.models.Book;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Camilo
 */
public interface BookService {
    List<Book> findAll();
    Optional<Book> findById(Long id);
    List<Book> findByParameter(String search);
    Boolean create(Book user);
    Boolean modified(Book user);
    Boolean destroy(Long id);
}
