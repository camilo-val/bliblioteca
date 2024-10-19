<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Optional"%>
<%@page import="com.bliblioteca.models.Book"%>
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
                    <form class="form-login" action="UpdateBookController" method="POST">
                        <% Optional<Book> book = (Optional<Book>) request.getSession().getAttribute("book");%>
                        <h1>Registro de usuario</h1>
                        <input type="hidden" name="id" value="<%=book.get().getId()%>">
                        <input name="title" type="text" placeholder="Titulo" value="<%=book.get().getTitle()%>">
                        <input name="author" type="text" placeholder="Autor" value="<%=book.get().getAuthor()%>">
                        <input name="year" type="date" placeholder="Año Publicación" value="<%=book.get().getYearPublication()%>">
                        <input name="isbn" type="text" placeholder="SIBN" value="<%=book.get().getIsbn()%>">
                        <select name="state">
                            <% if (book.get().getState()) {%>
                            <option value="<%= book.get().getState()%>">Activo</option>
                            <% } else {%>
                            <option value="<%= book.get().getState()%>">Inactivo</option>
                            <% }%>
                            <option value="true">Activo</option>
                            <option value="false">Inactivo</option>
                        </select>
                        <input name="gender" type="text" placeholder="Genero" value="<%=book.get().getGender()%>">
                        <div class="container-button">
                            <button type="submit">Modificar</button>
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