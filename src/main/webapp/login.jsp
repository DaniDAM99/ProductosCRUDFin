<%-- 
    Document   : login
    Created on : 19-mar-2021, 21:13:57
    Author     : Cristian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Restaurante bosco</h1>
        <form action="servletLogin" method="POST">
            Usuario:<br/>
            <input type="text" name="usuario"><br/>
            Contraseña:<br/>
            <input type="password" name="password"><br/>
            <input type="submit" value="Login">
        </form>
    </body>
</html>
