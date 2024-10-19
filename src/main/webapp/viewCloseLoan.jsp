<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.bliblioteca.models.Loan"%>

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
                        <th>Usuario</th>
                        <th>Libro</th>
                        <th>Fecha Prestamo</th>
                        <th>Fecha Cierre</th>
                        <th>Estado</th>
                    </thead>
                    <%List<Loan> loans = (List<Loan>) request.getSession().getAttribute("loans");%>
                    <tbody>
                        <%for(Loan loan : loans){%>
                        <tr>
                            <td><%=loan.getId()%></td>
                            <td><%=loan.getUser().getEmail()%> <%=loan.getUser().getName() %></td>
                            <td><%=loan.getBook().getTitle()%> <%=loan.getBook().getAuthor()%></td>
                            <td><%=loan.getDateLoan() %></td>
                            <td><%=loan.getDevolutionDate()%></td>
                            <td><%=loan.getState()%></td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
            </div>
        </main>
    </div>
</body>
</html>