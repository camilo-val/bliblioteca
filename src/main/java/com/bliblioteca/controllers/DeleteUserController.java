/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.bliblioteca.controllers;

import com.bliblioteca.models.User;
import com.bliblioteca.services.UserService;
import com.bliblioteca.services.UserServiceImpl;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "DeleteUserController", urlPatterns = {"/DeleteUserController"})
public class DeleteUserController extends HttpServlet {

    private UserService service = new UserServiceImpl();

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
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        HttpSession session = request.getSession();
        Long id = Long.valueOf(request.getParameter("id"));
        Boolean isDeleted = service.destroy(id);
        session.setAttribute("event", isDeleted.toString());
        if(isDeleted){
            session.setAttribute("message", "Usuario Eliminado Exitosamente");
        }else{
            session.setAttribute("message", "Error Al Eliminar El Usuario");

        }
        response.sendRedirect("UserController");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
