package com.bliblioteca.controllers;

import com.bliblioteca.models.Book;
import com.bliblioteca.services.BookService;
import com.bliblioteca.services.BookServiceImpl;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "BookController", urlPatterns = {"/BookController"})
public class CreateBookController extends HttpServlet {
    
    private BookService service = new BookServiceImpl();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        HttpSession session = request.getSession(true);
        List<Book> users = service.findAll();
        session.setAttribute("books", users);
        response.sendRedirect("viewBook.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        String title = request.getParameter("title"),
                author = request.getParameter("author"),
                year = request.getParameter("year"),
                isbn = request.getParameter("isbn"),
                gender = request.getParameter("gender");
        Book book;
            book = new Book(title, author, LocalDate.parse(year), isbn, gender,Boolean.TRUE);
                    Boolean isCreated = service.create(book);
                    response.sendRedirect("BookController");
 

                
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
