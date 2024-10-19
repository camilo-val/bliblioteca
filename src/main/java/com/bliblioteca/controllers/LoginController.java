/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bliblioteca.controllers;

import com.bliblioteca.models.User;
import com.bliblioteca.services.UserService;
import com.bliblioteca.services.UserServiceImpl;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet{
        
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
            session.invalidate();
            response.sendRedirect("login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        HttpSession session = request.getSession();
        String email = request.getParameter("email"),
                password = request.getParameter("password");
        Boolean isLogin = service.isLogin(email,password);
        if (isLogin) {
            session.setAttribute("userSession", service.findByEmail(email));
            session.setAttribute("isLogin", isLogin);
            response.sendRedirect("index.jsp");
        }else{
           response.sendRedirect("login.jsp?message="+isLogin.valueOf(isLogin));

        }
                
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    
}
