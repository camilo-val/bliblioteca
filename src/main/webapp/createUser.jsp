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
                            <form class="form-login" action="UserController" method="POST">
                                <h1>Registro de usuario</h1>
                                <input name="name" type="text" placeholder="Nombre">
                                <input name="lastname" type="text" placeholder="Apellido">
                                <input name="email" type="text" placeholder="Correo">
                                <input name="password" type="password" placeholder="Contraseï¿½a">
                                <select name="rol">
                                    <option value="Administrador">Administrador</option>
                                    <option value="Usuario">Usuario</option>
                                </select>
                                <div class="container-button">
                                    <button type="submit">Crear</button>
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