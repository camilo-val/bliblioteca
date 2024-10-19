<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.bliblioteca.models.Book"%>

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
                Boolean event = (Boolean) request.getSession().getAttribute("event");
                if(event != null && event.equals(false)){
            %>
            <div class="message-error">
                <p><%=message%></p>
            </div>
            <% request.getSession().removeAttribute("message");
                 request.getSession().removeAttribute("message");
                }else if(event != null && event.equals(true)){ %>
            <div class="message-done">
                <p><%=message%></p>
            </div>
            <% request.getSession().removeAttribute("message");
                request.getSession().removeAttribute("event");}%>
                <div class="input-search">
                   <form action="FindBookController" method="POST">
                    <input type="search" placeholder="Titulo, Autor o Genero" name="search"> 
                    <button>Buscar</button>
                   </form>
                </div>
            <div class="table-responsive">
                <table>
                    <thead>
                        <th>Id</th>
                        <th>Titulo</th>
                        <th>Autor</th>
                        <th>Año de publicación</th>
                        <th>ISBN</th>
                        <th>Genero</th>
                        <th>Estado</th>
                        <%   Optional<User> userS = (Optional<User>) request.getSession().getAttribute("userSession");
                                    if(userS!=null && userS.get().getRol().equals("Administrador")){
                        %>
                        <th>Acciones</th>
                        <%}%>
                    </thead>
                    <%List<Book> books = (List<Book>) request.getSession().getAttribute("books");%>
                    <tbody>
                        <%for(Book book: books){%>
                        <tr>
                            <td><%=book.getId()%></td>
                            <td><%=book.getTitle()%></td>
                            <td><%=book.getAuthor()%></td>
                            <td><%=book.getYearPublication()%></td>
                            <td><%=book.getIsbn()%></td>
                            <td><%=book.getGender()%></td>
                             <% if (book.getState()) {%>
                            <td>Activo</td>
                            <% } else {%>
                            <td>Inactivo</td>
                            <% }%>
                            <% if(userS!=null && userS.get().getRol().equals("Administrador")){
                                 %>
                            <td>
                                
                                <form name="editar" action="UpdateBookController" method="GET" style="float: left; margin-right: 10px;">
                                    <button class="btn btn-outline-primary btn-block" title="Editar registro">
                                        <i class="fas fa-edit"></i>
                                    </button>
                                    <input type="hidden" name="id" value="<%=book.getId()%>">
                                </form>
                                <form name="eliminar" action="DeleteBookController" method="POST" style="float: left; margin-right: 10px;">
                                    <button class="btn btn-outline-danger btn-block" title="Eliminar registro">
                                        <i class="fas fa-trash-alt"></i>
                                    </button>
                                    <input type="hidden" name="id" value="<%=book.getId()%>">
                                </form>
                            </td><%}%>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
            </div>
        </main>
    </div>
</body>
</html>