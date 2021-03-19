<%@page import="java.util.List"%>
<%@page import="modelo.Productos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
        <link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
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
        <% 
            List<Productos> misProductos = (List<Productos>) request.getAttribute("misProductos");
            int pagina = (Integer) request.getAttribute("pagina");
            int num_paginas = (Integer) request.getAttribute("num_paginas");
        %>
        
        <p>Mostrando página ${pagina} de ${num_paginas}</p>
        
        <div class="container">
            <div class="card">
                <div class="card-header">
                    <h3>Menú del restaurante Bosco</h3>
                </div>
                <div class="body">
                    <table class="table table-striped"">
                        <tr>
                            <th>Nombre</th>
                            <th>Imagen</th>
                            <th>Categoria</th>
                            <th>Precio</th>
                            <th></th>
                            <th></th>
                        </tr>
                        <% for (Productos p : misProductos) {
                                String cadenaBorrar = "<a onclick='return Confirmation()' href='servletProductos?op=borrar&id=" + p.getId() + "'>X</a>";
                                String cadenaActualizar = "<a href='servletProductos?op=update1&id=" + p.getId() + "'>Actualizar</a>";
                        %>

                        <tr>
                            <td><%=p.getNombre()%></td>
                            <td><%=p.getImagen()%></td>
                            <td><%=p.getCategoria()%></td>
                            <td><%=p.getPrecio()%></td>
                            <td><%=cadenaBorrar%></td>
                            <td><%=cadenaActualizar%></td>
                        </tr>
                        <%}%>
                    </table>
                </div>
                <div class="card-fotter" style="text-align: center">
                    <p>Volver al menú principal</p>
                </div>
            </div>
            <%for(int i = 1; i <= num_paginas; i++){%>
                <% if(pagina != i) {%>
                    <a href="servletProductos?op=listar&pagina=<%= i %>"><%= i %></a>
                <% } else { %>
                <%= i %>
                <%}%>
            <%}%>
        </div>
        
        
        <script type="text/javascript">
            function Confirmation() {
                if (confirm("¿Está seguro?")) {
                    alert("Procedo a borrar");
                    return true;
                } else {
                    alert("Cancelar..");
                    return false;
                }
            }
        </script>
    </body>
</html>
