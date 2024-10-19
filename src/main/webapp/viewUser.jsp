<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.bliblioteca.models.User"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">        <link rel="stylesheet" href="./css/normalize.css">
    <link rel="stylesheet" href="./css/app.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link
        href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap"
        rel="stylesheet">
    <title>Document</title>
</head>
<body>
    <%@include file="./layout/validateSession.jsp"%> 
    <%@include file="./layout/header.jsp"%> 
    <div class="home-container">
        <%@include file="./layout/sidebar.jsp"%> 
        <main class="main">
            <% String message = (String) request.getSession().getAttribute("message");
                String event = (String) request.getSession().getAttribute("event");
                if(event != null && event.equals("false")){
            %>
            <div class="message-error">
                
                <p><%=message%></p>
            </div>
            <% request.getSession().removeAttribute("message");
                 request.getSession().removeAttribute("message");
                }else if(event != null && event.equals("true")){ %>
            <div class="message-done">
                <p><%=message%></p>
            </div>
            <% request.getSession().removeAttribute("message");
                request.getSession().removeAttribute("event");}%>
            <div class="table-responsive">
                <table>
                    <thead>
                        <th>Id</th>
                        <th>Nombre</th>
                        <th>Apellido</th>
                        <th>Correo</th>
                        <th>ContraseÃ±a</th>
                        <th>Rol</th>
                        <th>Estado</th>
                        <th>Acciones</th>
                    </thead>
                    <%List<User> users = (List<User>) request.getSession().getAttribute("users");%>
                    <tbody>
                        <%for(User userDb: users){%>
                        <tr>
                            <td><%=userDb.getId()%></td>
                            <td><%=userDb.getName()%></td>
                            <td><%=userDb.getLastname()%></td>
                            <td><%=userDb.getEmail()%></td>
                            <td><%=userDb.getPassword()%></td>
                            <td><%=userDb.getRol()%></td>
                            <td><%=userDb.getState()%></td>
                            <td>
                                <form name="editar" action="UpdateUserController" method="GET" style="float: left; margin-right: 10px;">
                                    <button class="btn btn-outline-primary btn-block" title="Editar registro">
                                        <i class="fas fa-edit"></i>
                                    </button>
                                    <input type="hidden" name="id" value="<%=userDb.getId()%>">
                                </form>
                                 <%   Optional<User> userS = (Optional<User>) request.getSession().getAttribute("userSession");
                                        String flag = (String) request.getSession().getAttribute("flag");
                                    if(userS!=null && userS.get().getRol().equals("Administrador") && flag == null){
                                 %>
                                <form name="eliminar" action="DeleteUserController" method="POST" style="float: left; margin-right: 10px;">
                                    <button class="btn btn-outline-danger btn-block" title="Eliminar registro">
                                        <i class="fas fa-trash-alt"></i>
                                    </button>
                                    <input type="hidden" name="id" value="<%=userDb.getId()%>">
                                </form>
                                <%}%>
                            </td>
                        </tr>
                        <%}
                        request.getSession().removeAttribute("users"); 
                         %>
                    </tbody>
                </table>
            </div>
        </main>
    </div>
</body>
</html>