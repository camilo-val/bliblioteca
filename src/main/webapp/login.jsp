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
            <div>
                <form class="form-login" action="LoginController" method="POST">
                    <h1>Registro de usuario</h1>
                    <input name="email" type="text" placeholder="Correo">
                    <input name="password" type="password" placeholder="ContraseÃ±a">
                    <div>Â¿No eres usuario? <a href="registerUser.jsp">Registrate</a></div>
                    <% String message = (request.getParameter("message"));
                        if(message != null && message.equals("false")){
                    %>
                    <div class="message-error">
                        <p>Usuario y/o contraseña invalida</p>
                    </div>
                    <% }%>
                    <div class="container-button mb-3">
                        <button type="submit">Iniciar sesion</button>
                    </div>
                </form>
            </div>
        </main>

    </body>

    </html>