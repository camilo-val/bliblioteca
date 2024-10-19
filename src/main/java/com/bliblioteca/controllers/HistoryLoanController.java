package com.bliblioteca.controllers;

import com.bliblioteca.models.Loan;
import com.bliblioteca.services.BookService;
import com.bliblioteca.services.BookServiceImpl;
import com.bliblioteca.services.LoanService;
import com.bliblioteca.services.LoanServiceImpl;
import com.bliblioteca.services.UserService;
import com.bliblioteca.services.UserServiceImpl;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "HistoryLoanController", urlPatterns = {"/HistoryLoanController"})
public class HistoryLoanController extends HttpServlet {
    
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
        List<Loan> loans = service.findAllHistory();
        session.setAttribute("loans", loans);
        response.sendRedirect("viewCloseLoan.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
