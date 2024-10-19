package com.bliblioteca.controllers;

import com.biblioteca.utils.EncryptionUtil;
import static com.biblioteca.utils.EncryptionUtil.generateKey;
import com.bliblioteca.models.User;
import com.bliblioteca.services.UserService;
import com.bliblioteca.services.UserServiceImpl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

@WebServlet(name = "UserController", urlPatterns = {"/UserController"})
public class CreateUserController extends HttpServlet {

    private UserService service = new UserServiceImpl();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        String flag = request.getParameter("flag");
        HttpSession session = request.getSession(true);
        List<User> users = new ArrayList<>();
        session.setAttribute("flag: ", flag);
        if (flag != null && flag.equals("U")) {
            Optional<User> userUpdate = (Optional<User>) session.getAttribute("userSession");
            users.add(userUpdate.get());
            session.setAttribute("users", users);
        } else {
            users = service.findAll();
            session.setAttribute("users", users);
        }
        response.sendRedirect("viewUser.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        HttpSession session = request.getSession();
        String name = request.getParameter("name"),
                lastname = request.getParameter("lastname"),
                email = request.getParameter("email"),
                password = request.getParameter("password"),
                rol = request.getParameter("rol");
        try {
            SecretKey key = generateKey();
            EncryptionUtil.encrypt(password, key);
        } catch (Exception ex) {
            Logger.getLogger(CreateUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        User user;
        Boolean isCreated = false;
        String validateInputs = "";
        if (rol != null || "Administrador".equals(rol)) {
            user = new User(name, lastname, email, password, Boolean.TRUE, rol);
        } else {
            user = new User(name, lastname, email, password, Boolean.TRUE, "Usuario");
        }

        validateInputs = service.isCorrectInput(user);
        if (validateInputs.equalsIgnoreCase("Aprobado")) {
            if (rol != null || "Administrador".equals(rol)) {

                service.create(user);
                response.sendRedirect("UserController");
            } else {
                isCreated = service.create(user);
                response.sendRedirect("registerUser.jsp?message=" + validateInputs);
            }
        } else {
            response.sendRedirect("registerUser.jsp?message=" + validateInputs);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
