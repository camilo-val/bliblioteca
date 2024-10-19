package com.bliblioteca.controllers;

import com.bliblioteca.models.Book;
import com.bliblioteca.models.Loan;
import com.bliblioteca.models.User;
import com.bliblioteca.services.BookService;
import com.bliblioteca.services.BookServiceImpl;
import com.bliblioteca.services.LoanService;
import com.bliblioteca.services.LoanServiceImpl;
import com.bliblioteca.services.UserService;
import com.bliblioteca.services.UserServiceImpl;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "UpdateLoanController", urlPatterns = {"/UpdateLoanController"})
public class UpdateLoanController extends HttpServlet {

    private LoanService service = new LoanServiceImpl();
    private UserService serviceUser = new UserServiceImpl();
    private final BookService serviceBook = new BookServiceImpl();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        HttpSession session = request.getSession(true);
        List<User> users = serviceUser.findAll();
        session.setAttribute("users", users);
        Long id = Long.valueOf(request.getParameter("id"));
        List<Book> books = serviceBook.findAll();
        session.setAttribute("books", books);
        Optional<Loan> loan = service.findById(id);
        session.setAttribute("loan", loan);
        response.sendRedirect("updateLoan.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        HttpSession session = request.getSession();
        Long id = Long.valueOf(request.getParameter("id"));
        String user = request.getParameter("user"),
                book = request.getParameter("book"),
                dateLoan = request.getParameter("dateLoan"),
                devolutionDate = request.getParameter("devolutionDate"),
                state = request.getParameter("state");
        Loan loan;
        loan = new Loan(id, serviceUser.findById(Long.valueOf(user)).get(), serviceBook.findById(Long.valueOf(book)).get(), LocalDate.parse(dateLoan), state, LocalDate.now());
        Optional<Book> updateBook = serviceBook.findById(Long.valueOf(book));
        if (state.equals("Devuelto")) {
            updateBook.get().setState(Boolean.TRUE);
            serviceBook.modified(updateBook.get());
        }

        Boolean isUpdated = service.modified(loan);
        session.setAttribute("event", isUpdated);
        if (isUpdated) {
            session.setAttribute("message", "Prestamo Modificado Exitosamente");
        } else {
            session.setAttribute("message", "Error Al Modificar El Prestamo");

        }
        response.sendRedirect("LoanController");

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
