/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.bliblioteca.controllers;

import com.biblioteca.utils.EncryptionUtil;
import com.bliblioteca.models.User;
import com.bliblioteca.services.UserService;
import com.bliblioteca.services.UserServiceImpl;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKey;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Camilo
 */
@WebServlet(name = "UpdateUserController", urlPatterns = {"/UpdateUserController"})
public class UpdateUserController extends HttpServlet {

    private UserService service = new UserServiceImpl();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        HttpSession session = request.getSession(true);
        Long id = Long.valueOf(request.getParameter("id"));
        Optional<User> user = service.findById(id);
        try {
            SecretKey key = (SecretKey) getServletContext().getAttribute("key");
            user.get().setPassword(EncryptionUtil.decrypt(user.get().getPassword(), key));
        } catch (Exception ex) {
            Logger.getLogger(CreateUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        session.setAttribute("user", user);
        response.sendRedirect("updateUser.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        HttpSession session = request.getSession(true);
        Long id = Long.valueOf(request.getParameter("id"));
        String name = request.getParameter("name"),
                lastname = request.getParameter("lastname"),
                email = request.getParameter("email"),
                password = request.getParameter("password"),
                rol = request.getParameter("rol");
        try {
            SecretKey key = (SecretKey) getServletContext().getAttribute("key");
            password = EncryptionUtil.encrypt(password, key);
        } catch (Exception ex) {
            Logger.getLogger(CreateUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        User user = new User(id, name, lastname, email, password, Boolean.TRUE, rol);
        String validateInputs = "";
        if (email.equals(service.findById(id).get().getEmail())) {
            Boolean isUpdated = service.modified(user);
            session.setAttribute("event", isUpdated.toString());
            if (isUpdated) {
                session.setAttribute("message", "Usuario Modificado Exitosamente");
                response.sendRedirect("UserController?flag=U");
            }
        } else {
            validateInputs = service.isCorrectInput(user);
            if (validateInputs.equalsIgnoreCase("Aprobado")) {
                Boolean isUpdated = service.modified(user);
                session.setAttribute("event", isUpdated.toString());
                if (isUpdated) {
                    session.setAttribute("message", "Usuario Modificado Exitosamente");
                    response.sendRedirect("UserController?flag=U");

                } else {
                    session.setAttribute("message", "Error Al Modificar El Usuario");
                }
            } else {
                response.sendRedirect("updateUser.jsp?message=" + validateInputs);
            }
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
