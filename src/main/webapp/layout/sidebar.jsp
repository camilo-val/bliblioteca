<%@page import="com.bliblioteca.models.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Optional"%>
<!DOCTYPE html>
<html lang="en">

<body>
    <div class="container-sidebar">
        <div class="sidebar" id="sidebar">
            <ul>
                <li>
                    <a href="#">Inicio</a>
                </li>
                <li>
                    <a href="#">Usuarios</a>
                    <ul class="submenu">
                        <%   Optional<User> user = (Optional<User>) request.getSession().getAttribute("userSession");
                            if(user!=null && user.get().getRol().equals("Administrador")){
                        %>
                        <li><a href="createUser.jsp">Crear Usuario</a></li>
                        <li><a href="UserController?flag=null">Ver Usuario</a></li>
                        <%}%>
                        <li><a href="UserController?flag=U">Modificar Mi Usuario</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#">Libros</a>
                    <ul class="submenu">
                        <% if(user!=null && user.get().getRol().equals("Administrador")){
                        %>
                        <li><a href="createBook.jsp" name ="createBook">Crear Libro</a></li>
                        <%}%>
                        <li><a href="BookController">Ver Libro</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#">Prestamo</a>
                    <ul class="submenu">
                        <% if(user!=null && user.get().getRol().equals("Administrador")){
                        %>
                        <li><a href="setDataLoanController">Crear Prestamo</a></li>
                        <%}%>
                        <li><a href="LoanController">Ver Prestamo</a></li>
                        <%if(user!=null && user.get().getRol().equals("Administrador")){
                        %>
                        <li><a href="HistoryLoanController">Ver Prestamo Cerrados</a></li>
                        <%}%>
                    </ul>
                </li>
            </ul>
        </div>

    </div>
    <script src="./js/script.js"></script>
    
</body>

</html>