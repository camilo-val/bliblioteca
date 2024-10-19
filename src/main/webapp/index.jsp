<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">        <link rel="stylesheet" href="./css/normalize.css">
    <link rel="stylesheet" href="./css/app.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
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
        </main>
    </div>
</body>
</html>