/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.bliblioteca.controllers;

import com.bliblioteca.models.Book;
import com.bliblioteca.models.User;
import com.bliblioteca.services.BookService;
import com.bliblioteca.services.BookServiceImpl;
import com.bliblioteca.services.LoanService;
import com.bliblioteca.services.LoanServiceImpl;
import com.bliblioteca.services.UserService;
import com.bliblioteca.services.UserServiceImpl;
import java.io.IOException;
import java.util.List;
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
@WebServlet(name = "setDataLoanController", urlPatterns = {"/setDataLoanController"})
public class setDataLoanController extends HttpServlet {

        private final UserService serviceUser = new UserServiceImpl();
        private final BookService serviceBook = new BookServiceImpl();
        private LoanService service = new LoanServiceImpl();



    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        HttpSession session = request.getSession(true);
        String flag = request.getParameter("flag");
        List<User> users = serviceUser.findAll();
        session.setAttribute("users", users);
        List<Book> books = serviceBook.findAll();
        session.setAttribute("books", books);
        response.sendRedirect("createLoan.jsp");   
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
                processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
