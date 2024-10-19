/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.bliblioteca.controllers;

import com.bliblioteca.models.Book;
import com.bliblioteca.services.BookService;
import com.bliblioteca.services.BookServiceImpl;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;
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
@WebServlet(name = "UpdateBookController", urlPatterns = {"/UpdateBookController"})
public class UpdateBookController extends HttpServlet {

    private BookService service = new BookServiceImpl();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        processRequest(request, response);
        HttpSession session = request.getSession(true);
        Long id = Long.valueOf(request.getParameter("id"));
        Optional<Book> book = service.findById(id);
        session.setAttribute("book", book);
        response.sendRedirect("updateBook.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        HttpSession session = request.getSession(true);
        Long id = Long.valueOf(request.getParameter("id"));
        String title = request.getParameter("title"),
                author = request.getParameter("author"),
                year = request.getParameter("year"),
                isbn = request.getParameter("isbn"),
                state = request.getParameter("state"),
                gender = request.getParameter("gender");
        Book book = new Book(id,title, author, LocalDate.parse(year), isbn, gender,Boolean.valueOf(state));
        Boolean isUpdated = service.modified(book);
        session.setAttribute("event", isUpdated);
        if(isUpdated){
            session.setAttribute("message", "Libro Modificado Exitosamente");
        }else{
            session.setAttribute("message", "Error Al Modificar El Libro");

        }
        response.sendRedirect("BookController");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
