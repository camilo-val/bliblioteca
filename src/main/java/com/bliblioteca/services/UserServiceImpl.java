package com.bliblioteca.services;

import com.bliblioteca.models.User;
import com.bliblioteca.repositorie.UserJpaController;
import com.bliblioteca.repositorie.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import javax.persistence.NoResultException;

public class UserServiceImpl implements UserService,Serializable{
    private static final long serialVersionUID = 1L; 
    
    private final UserJpaController repository = new UserJpaController();
    private final LoanService Loan = new LoanServiceImpl();
    @Override
    public List<User> findAll() {
        return repository.findUserEntities();
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(repository.findUser(id));
    }
    
    @Override
    public Boolean create(User user) {
       List<User> usersBd = findAll();
       if(usersBd.isEmpty()){
        for(User userBd: usersBd){
            if(userBd.getEmail().equalsIgnoreCase(user.getEmail())){
                return false;
            }
        }
       }
       repository.create(user);
       return true;
    }
    
    @Override
    public Boolean modified(User user) {
                    System.out.println("ENTREE: ");
        Optional<User> userdB = findById(user.getId());
        if(userdB.isPresent()){
        System.out.println("ENTREE 2: ");
            try {
                repository.edit(user);
                return true;
            } catch (Exception ex) {
                return false;
            }
        }
        return false;
    }

    @Override
    public Boolean destroy(Long id) {
        Optional<User> userdB = findById(id);
        if(userdB.isPresent()){
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
    public Optional<User> findByEmail(String email) {
        try{
            return Optional.ofNullable(repository.findUserEmail(email));
        }catch(NoResultException e){
            return null;
        }
    }

    @Override
    public Boolean isLogin(String email, String password) {
        Optional<User> userDB = findByEmail(email);
        try {
            if(userDB.isPresent()){
                return userDB.get().getEmail().equalsIgnoreCase(email) &&
                    userDB.get().getPassword().equals(password) && userDB.get().getState().equals(true);
            }
        } catch (NullPointerException e) {
            
        return false;
        }
        return false;
    }
    @Override
    public String isCorrectInput (User user){
        User userDb = new User();
        try {
            userDb = findByEmail(user.getEmail()).get();
        } catch (NullPointerException e) {
            userDb = new User();
        }
        final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if(user.getName()==null || user.getName().trim().isEmpty()){
            System.out.println("Etmreee: ");
            return "El nombre no puede ir vacio";
        }
        if(user.getLastname()==null || user.getLastname().trim().isEmpty()){
            return "El apellido no puede ir vacio";
        }
        if(user.getEmail()==null || user.getEmail().trim().isEmpty()){
            return "El correo no puede ir vacio";
        }
        if(user.getEmail().equals(userDb.getEmail())){
            return "El correo Ya esta registrado";
        }
        if (!Pattern.matches(EMAIL_REGEX, user.getEmail())) {
            return"Formato de correo invalido";
        }
        if(user.getPassword()== null || user.getPassword().trim().isEmpty()){
            return "La contraseña no puede ir vacia";
        }
        if(user.getRol()==null || user.getName().trim().isEmpty()){
            return "El rol no puede ir vacio";
        }
        if(!user.getRol().equals("Usuario") && !user.getRol().equals("Administrador")){
            return "Rol seleccionado invalidoo";
        }
         if(user.getState()==null){
            return "El estado no puede ir vacio";
        }
        if(!user.getState().equals(true) && !user.getState().equals(false)){
            return "Estado seleccionado invalidoo";
        }
        return "Aprobado";
    }
    
}
