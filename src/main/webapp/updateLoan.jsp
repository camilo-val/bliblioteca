<%@page import="com.bliblioteca.models.Loan"%>
<%@page import="java.util.Optional"%>
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
                            <%%>
                            <form class="form-login" action="UpdateLoanController" method="POST">
                                <h1>Registro de Prestamos</h1>
                                <%List<User> users = (List<User>)request.getSession().getAttribute("users");
                                List<Book> books = (List<Book>)request.getSession().getAttribute("books"); 
                                Optional<Loan> loan = (Optional<Loan>) request.getSession().getAttribute("loan");%>
                                <input type="hidden" name="id" value="<%=loan.get().getId()%>">
                                <select name="user">
                                    <option value="<%= loan.get().getUser().getId() %>"><%=loan.get().getUser().getEmail()%> <%=loan.get().getUser().getName()%></option>
                                    <%for(User userUpdate : users){%>
                                    <option value="<%= userUpdate.getId()%>"><%=userUpdate.getEmail()%></option>
                                    <%}%>
                                </select>
                                <select name="book">
                                    <option value="<%= loan.get().getBook().getId() %>"><%=loan.get().getBook().getTitle()%> <%=loan.get().getBook().getAuthor()%></option>
                                   <%for(Book book: books){%>
                                    <option value="<%= book.getId()%>"><%=book.getTitle()%> <%=book.getAuthor()%></option>
                                    <%}
                                    %>
                                </select>
                                    <input name="dateLoan" type="date" placeholder="Año Publicación" hidden="hidden" value="<%= loan.get().getDateLoan()%>">
                                <select name="state">
                                    <option value="<%= loan.get().getState()%>"><%=loan.get().getState()%></option>
                                    <option value="Devuelto">Devuelto</option>
                                    <option value="No devuelto">No devuelto</option>
                                    <option value="En prestamo">En prestamo</option>
                                </select>
                                    <% request.getSession().removeAttribute("books");
                                    request.getSession().removeAttribute("loan");
                                    request.getSession().removeAttribute("users");%>
                                <div class="container-button">
                                    <button type="submit">Modificar</button>
                                <div class="return-button">
                                        <a href="LoanController">Volver</a>
                                </div>
                                </div>
                            </form>
                        </div>
                    </main>
            </div>
    </body>

    </html>