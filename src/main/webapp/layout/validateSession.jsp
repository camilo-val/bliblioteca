<%@page import="com.bliblioteca.models.User"%>
<%@page import="java.util.Optional"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%

    HttpSession sesion = request.getSession();
    Optional<User> userSession = (Optional<User>) request.getSession().getAttribute("userSession");

    if (userSession == null) {
        boolean error = false;
        session.setAttribute("error", error);//Asigno el error para mensaje
        response.sendRedirect("login.jsp");
    }
%>