
<%@page import="com.bliblioteca.models.User"%>
<%@page import="java.util.Optional"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.bliblioteca.models.Loan"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
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
            <div class="table-responsive">
                <table>
                    <thead>
                        <th>Id</th>
                        <th>Usuario</th>
                        <th>Libro</th>
                        <th>Fecha Prestamo</th>
                        <th>Estado</th>
                        <%   Optional<User> userS = (Optional<User>) request.getSession().getAttribute("userSession");
                                    if(userS!=null && userS.get().getRol().equals("Administrador")){
                                 %>
                        <th>Acciones</th>
                        <%}%>
                    </thead>
                    <%List<Loan> loans = (List<Loan>) request.getSession().getAttribute("loans");%>
                    <tbody>
                        <%for(Loan loan : loans){%>
                        <tr>
                            <td><%=loan.getId()%></td>
                            <td><%=loan.getUser().getEmail()%> <%=loan.getUser().getName() %></td>
                            <td><%=loan.getBook().getTitle()%> <%=loan.getBook().getAuthor()%></td>
                            <td><%=loan.getDateLoan() %></td>
                            <td><%=loan.getState()%></td>
                            <%   if(userS!=null && userS.get().getRol().equals("Administrador")){
                                 %>
                            <td>
                                <form name="editar" action="UpdateLoanController" method="GET" style="float: left; margin-right: 10px;">
                                    <button class="btn btn-outline-primary btn-block" title="Editar registro">
                                        <i class="fas fa-edit"></i>
                                    </button>
                                    <input type="hidden" name="id" value="<%=loan.getId()%>">
                                </form>
                            </td>
                        </tr>
                        <%}
                    }%>
                    </tbody>
                </table>
            </div>
    </body>
</html>
