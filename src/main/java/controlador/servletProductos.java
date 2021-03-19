/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Productos;
import modelo.ProductosCRUD;

/**
 *
 * @author DAW-B
 */
public class servletProductos extends HttpServlet {

    private int id;
    private final int NUM_LINEAS_PAGINAS = 4;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String op = request.getParameter("op");
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet servletProductos</title>");
            out.println("</head>");
            out.println("<body>");

            if (op.equals("listar")) {
                List<Productos> misProductos = ProductosCRUD.getProductos();
                int pagina = 1;
                int offset = 0;
                if(request.getParameter("pagina") != null) {
                    pagina = Integer.parseInt(request.getParameter("pagina"));
                    offset = (pagina-1) * NUM_LINEAS_PAGINAS;
                }
                
                int num_paginas = (int) Math.ceil(misProductos.size() / NUM_LINEAS_PAGINAS);
                misProductos = ProductosCRUD.getProductosPaginado(offset, NUM_LINEAS_PAGINAS);
                
                request.setAttribute("num_paginas", num_paginas);
                request.setAttribute("pagina", pagina);
                request.setAttribute("misProductos", misProductos);
                request.getRequestDispatcher("listar.jsp").forward(request, response);
                /*for (Productos p1 : misProductos) {
                    out.println("<p>" + p1.getNombre() + "</p>");
                }*/
            }
            if (op.equals("insert1")) { //cuando le indicamos que vaya a insertar
                request.getRequestDispatcher("insert.jsp").forward(request, response);
            }
            if (op.equals("insert2")) { //cuando le indicamos que vaya a insertar

                Productos miProducto = new Productos();
                miProducto.setNombre(request.getParameter("nombre"));
                miProducto.setImagen(request.getParameter("imagen"));
                miProducto.setCategoria(request.getParameter("categoria"));
                String precio = request.getParameter("precio");
                miProducto.setPrecio(Float.parseFloat(precio));
                ProductosCRUD.insertaProducto(miProducto);
                out.println("<h1>Proucto insertado " + "<a href='index.jsp'>Volver</a>" + "</h1>");
            }
            if (op.equals("borrar")) { //cuando le indicamos que vaya a insertar
                id = Integer.parseInt(request.getParameter("id"));
                if (ProductosCRUD.borrarProducto(id) > 0) {
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
            }
            if (op.equals("update1")) {
                id = Integer.parseInt(request.getParameter("id"));
                Productos miProducto = ProductosCRUD.getProducto(id);
                System.out.println("miProducto " + miProducto.getNombre());
                request.setAttribute("miProducto", miProducto);
                request.getRequestDispatcher("update.jsp").forward(request, response);
            }
            if (op.equals("update2")) {
                String nombre = request.getParameter("nombre");
                String imagen = request.getParameter("imagen");
                String categoria = request.getParameter("categoria");
                float precio = Float.parseFloat(request.getParameter("precio"));
                Productos miProducto = new Productos(id, nombre, imagen, categoria, precio);
                int filas = ProductosCRUD.actualizaProducto(miProducto);
                out.println("<h3>Se ha actualizado correctamente</h3>");
                out.println("<h4>" + filas + " filas actualizadas</h4>");
            }
            /*Productos pact = new Productos();
            pact.setId(12);
            pact.setNombre("Tarta de limon");
            pact.setPrecio(10);
            pact.setImagen("tarta.jpg");
            pact.setCategoria("complementos");
            
            ProductosCRUD.actualizaProducto(pact);*/
 /*out.println("<p>Borrando producto con id 4...</p>");
            ProductosCRUD.borrarProducto(4);
            out.println("<p>Mostrando de nuevo los datos...</p>");
            List<Productos> misProductos2 = ProductosCRUD.getProductos();
            for (Productos p2 : misProductos2) {
                out.println("<p>" +p2.getNombre() + "</p>");
            }*/
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
