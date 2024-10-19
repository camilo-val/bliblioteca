
package com.bliblioteca.services;

import com.bliblioteca.models.User;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Camilo
 */
public interface UserService {
    List<User> findAll();
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    Boolean create(User user);
    Boolean modified(User user);
    Boolean destroy(Long id);
    Boolean isLogin(String email, String password);
    String isCorrectInput (User user);
}
