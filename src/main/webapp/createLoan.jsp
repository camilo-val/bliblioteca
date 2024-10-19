<%@page import="com.bliblioteca.models.Book"%>
<%@page import="com.bliblioteca.models.User"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <title>Start Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./css/normalize.css">
        <link rel="stylesheet" href="./css/app.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link
            href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap"
            rel="stylesheet">
    </head>

    <body>
        <%@include file="./layout/validateSession.jsp"%> 
        <%@include file="./layout/header.jsp" %>
            <div class="home-container">
                <%@include file="./layout/sidebar.jsp" %>
                    <main class="main">
                        <div class="container-form">
                            <form class="form-login" action="LoanController" method="POST">
                                <h1>Registro de Prestamos</h1>
                                <%List<User> users = (List<User>)request.getSession().getAttribute("users");
                                    List<Book> books = (List<Book>)request.getSession().getAttribute("books"); %>
                                <select name="user">
                                    <%for(User userDb: users){%>
                                    <option value="<%= userDb.getId()%>"><%=userDb.getEmail()%></option>
                                    <%}%>
                                </select>
                                <select name="book">
                                   <%for(Book book: books){%>
                                    <%  
                                      if(book.getState().equals(true)){%>
                                    <option value="<%= book.getId()%>"><%=book.getTitle()%> <%=book.getAuthor()%></option>
                                    <%}}%>
                                </select>
                                <input name="dateLoan" type="date" placeholder="Año Publicación">
                                <select name="state">
                                    <option value="En prestamo">En prestamo</option>
                                </select>
                                <div class="container-button">
                                    <button type="submit">Crear</button>
                                <div class="return-button">
                                        <a href="index.jsp">Volver</a>
                                </div>
                                </div>
                            </form>
                        </div>
                    </main>
            </div>
    </body>

    </html>