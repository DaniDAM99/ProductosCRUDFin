/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import modelo.Productos;
import modelo.ProductosCRUD;

/**
 * REST Web Service
 *
 * @author DAW-B
 */
@Path("rest2")
public class Rest2Resource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Rest2Resource
     */
    public Rest2Resource() {
    }

    /**
     * Retrieves representation of an instance of controlador.Rest2Resource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/rest2/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public String ping(@PathParam("nombre") String nombre) {
        return "Probando " + nombre;
    }

    @GET
    @Path("/persona")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJson() {
        String persona = "{\"nombre\":\"Juan\", \"pais\":\"España\"}";
        ResponseBuilder res = Response.ok(persona);
        return res.build();
    }

    @GET
    @Path("/productos/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Productos getProducto(@PathParam("id") int id) {
        Productos prod = ProductosCRUD.getProducto(id);
        /*String json = "{\"id\":\"" + prod.getId() + "\", \"nombre\":\""
                + prod.getNombre() + "\", \"categoria\":\"" + prod.getCategoria() + "\", \"imagen\":\"" + prod.getImagen() + "\"}";

        ResponseBuilder res = Response.ok(json);*/
        return prod;
    }

    @GET
    @Path("/productos/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Productos> getProductos() {
        List<Productos> prod = ProductosCRUD.getProductos();
        /*String json = "{\"id\":\"" + prod.getId() + "\", \"nombre\":\""
                + prod.getNombre() + "\", \"categoria\":\"" + prod.getCategoria() + "\", \"imagen\":\"" + prod.getImagen() + "\"}";

        ResponseBuilder res = Response.ok(json);*/
        return prod;
    }
    
    @PUT
    @Path("/productos/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Productos updateProductos(Productos prod) {
        ProductosCRUD.actualizaProducto(prod);
        prod = ProductosCRUD.getProducto(prod.getId());
        /*String json = "{\"id\":\"" + prod.getId() + "\", \"nombre\":\""
                + prod.getNombre() + "\", \"categoria\":\"" + prod.getCategoria() + "\", \"imagen\":\"" + prod.getImagen() + "\"}";

        ResponseBuilder res = Response.ok(json);*/
        return prod;
    }
    
    @POST
    @Path("/productos/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Productos insertProductos(Productos prod) {
        ProductosCRUD.insertaProducto(prod);
        return prod;
    }
    
    @DELETE
    @Path("/productos/{id}")
    public void insertProductos(@PathParam("id") int id) {
        ProductosCRUD.borrarProducto(id);
    }
}
