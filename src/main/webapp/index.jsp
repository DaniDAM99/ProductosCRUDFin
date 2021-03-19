<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <%
        HttpSession sesion = request.getSession();
        String usuarioLogeado = (String) sesion.getAttribute("usuarioLogeado");
        if (usuarioLogeado == null) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }

    %>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
    </head>
    <body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand" href="#">Navbar</a>
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="btn btn-outline-light" href="servletProductos?op=listar">Listar</a><br>
            </li>
            <li class="nav-item">
                <a class="btn btn-outline-light" href="servletProductos?op=insert1">Insertar</a> 
            </li>
        </ul>
        <ul class="navbar-nav" style="right: 0;">
            <li class="nav-item"><p style="color: white; right: 0;"><%= usuarioLogeado %></p></li>
            <li class="nav-item">
                <a class="btn btn-outline-light" href="servletLogin">Log Out</a> 
            </li>
        </ul>
    </nav>
</body>
</html>
