<%@page import="java.security.Key"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Optional"%>
<%@page import="com.bliblioteca.models.User"%>
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
                    <form class="form-login" action="UpdateUserController" method="POST">
                        <% Optional<User> userU = (Optional<User>) request.getSession().getAttribute("user");
                        %>
                        <h1>Registro de usuario</h1>
                        <input type="hidden" name="id" value="<%=userU.get().getId()%>">
                        <input name="name" type="text" placeholder="Nombre" value="<%= userU.get().getName()%>">
                        <input name="lastname" type="text" placeholder="Apellido" value="<%= userU.get().getLastname()%>">
                        <input name="email" type="text" placeholder="Correo" value="<%= userU.get().getEmail()%>">
                        <input name="password" type="password" placeholder="Contraseï¿½a" value="<%= userU.get().getPassword()%>">
                        <%   Optional<User> userS = (Optional<User>) request.getSession().getAttribute("userSession");
                            if (userS != null && userS.get().getRol().equals("Administrador")) {
                        %>
                        <select name="rol">
                            <option value="<%= userU.get().getRol()%>"><%= userU.get().getRol()%></option>
                            <option value="Administrador">Administrador</option>
                            <option value="Usuario">Usuario</option>
                        </select>
                        <%}%>
                        <% String message = request.getParameter("message");
                            if (message != null && !message.equalsIgnoreCase("Aprobado")) {
                        %>
                        <div class="message-error">
                            <p><%=message%></p>
                        </div>
                        <% } else if (message != null && message.equals("Aprobado")) { %>
                        <div class="message-done">
                            <p>Usuario registrado exitosamente</p>
                        </div>
                        <% }request.removeAttribute("message");
                        request.removeAttribute("user");%>
                        <div class="container-button">
                            <button type="submit">Modificar</button>

                        </div>
                    </form>
                </div>
            </main>
        </div>

    </body>

</html>