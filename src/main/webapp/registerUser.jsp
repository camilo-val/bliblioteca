<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <main class="container-login">
            <div>
                <img src="./img/img-login.jpg" alt="imagen login">
            </div>
            <div class="container-form">
                <form class="form-login" action="UserController" method="POST">
                    <h1>Registro de usuario</h1>
                    <input name="name" type="text" placeholder="Nombre">
                    <input name="lastname" type="text" placeholder="Apellido">
                    <input name="email" type="text" placeholder="Correo">
                    <input name="password" type="password" placeholder="Contraseï¿½a">
                    <% String message = request.getParameter("message");
                        if(message != null && !message.equalsIgnoreCase("Aprobado")){
                    %>
                    <div class="message-error">
                        <p><%=message%></p>
                    </div>
                    <% }else if(message != null && message.equals("Aprobado")){ %>
                    <div class="message-done">
                        <p>Usuario registrado exitosamente</p>
                    </div>
                    <% }%>
                    <div class="container-button">
                        <button type="submit">Crear</button>
                        <div class="return-button">
                            <a href="index.jsp">Volver</a>
                        </div> 
                    </div>
                </form>
            </div>
        </main>

    </body>

    </html>