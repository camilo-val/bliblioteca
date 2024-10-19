package com.bliblioteca.controllers;

import com.bliblioteca.models.Book;
import com.bliblioteca.models.Loan;
import com.bliblioteca.services.BookService;
import com.bliblioteca.services.BookServiceImpl;
import com.bliblioteca.services.LoanService;
import com.bliblioteca.services.LoanServiceImpl;
import com.bliblioteca.services.UserService;
import com.bliblioteca.services.UserServiceImpl;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LoanController", urlPatterns = {"/LoanController"})
public class CreateLoanControll extends HttpServlet {
    
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
        List<Loan> loans = new ArrayList<>();
        loans = service.findAllActive();
        session.setAttribute("loans", loans);
        response.sendRedirect("viewLoan.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        HttpSession session = request.getSession();
        String  user =request.getParameter("user"),
                book = request.getParameter("book"),
                dateLoan = request.getParameter("dateLoan"),
                devolutionDate = request.getParameter("devolutionDate"),
                state = request.getParameter("state");
        Loan loan;
        Optional<Book> updateState = serviceBook.findById(Long.valueOf(book));
        updateState.get().setState(Boolean.FALSE);
        serviceBook.modified(updateState.get());
        loan = new Loan(serviceUser.findById( Long.valueOf(user)).get(), serviceBook.findById(Long.valueOf(book)).get(), LocalDate.parse(dateLoan) , state, null);
        Boolean isCreated = service.create(loan);
        if(isCreated){
            response.sendRedirect("LoanController");

        }

                
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
