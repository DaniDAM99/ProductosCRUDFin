<%@page import="modelo.Productos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <a class="navbar-brand" href="#">Navbar</a>

            <li class="nav-item">
                <a class="btn btn-outline-light" href="servletProductos?op=listar">Listar</a><br>
            </li>
            <li class="nav-item">
                <a class="btn btn-outline-light" href="servletProductos?op=insert1">Insertar</a> 
            </li>
        </nav>
        <% Productos producto = (Productos) request.getAttribute("miProducto");%>
        <h1>Nuevo producto</h1>
        <form action="servletProductos" method="get">
            <p><label>Nombre: <input type="text" name="nombre" value="<%=producto.getNombre()%>" required > </label></p>
            <p><label>Imagen: <input type="text" name="imagen" value="<%=producto.getImagen()%>" required > </label></p>
            <p><label>Categoría: <input type="text" name="categoria" value="<%=producto.getCategoria()%>" required > </label></p>
            <p><label>Precio: <input type="text" name="precio" value="<%=producto.getPrecio()%>" required > </label></p>
            <input type="text" name="op" required value="update2"> 
            <input type="submit" value="Actualizar" >       
        </form>
    </body>

    <script>
        window.onbeforeunload = confirmExit;
        function confirmExit()
        {
            return "Seguro que quiere salir?";
        }
    </script>
</html>