/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author DAW-B
 */
public class ProductosCRUD {

    public static List<Productos> getProductos() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("com.mycompany_productosCRUD_war_1.0-SNAPSHOTPU");
        EntityManager manager = factory.createEntityManager();
        String sql = "SELECT * FROM productos";
        Query q = manager.createNativeQuery(sql, Productos.class);
        List<Productos> productosBD = q.getResultList();

        return productosBD;
    }
    
    
    public static List<Productos> getProductosPaginado(int offset, int tamanio_pagina) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("com.mycompany_productosCRUD_war_1.0-SNAPSHOTPU");
        EntityManager manager = factory.createEntityManager();
        String sql = "SELECT * FROM productos";
        Query q = manager.createNativeQuery(sql, Productos.class);
        q.setFirstResult(offset);
        q.setMaxResults(tamanio_pagina);
        
        List<Productos> productosBD = q.getResultList();

        return productosBD;
    }

    public static Productos getProducto(int id) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("com.mycompany_productosCRUD_war_1.0-SNAPSHOTPU");
        EntityManager manager = factory.createEntityManager();
        String sql = "SELECT p FROM Productos p WHERE p.id = " + id;
        Query q = manager.createQuery(sql, Productos.class);
        Productos p = (Productos) q.getSingleResult();

        return p;
    }

    public static void insertarProducto() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("com.mycompany_productosCRUD_war_1.0-SNAPSHOTPU");
        EntityManager manager = factory.createEntityManager();
        String sql = "INSERT INTO productos (id, nombre, imagen, categoria, precio) VALUES (NULL, 'Tortas de alcazar', '', 'complementos', '10.2')";
        manager.getTransaction().begin();
        Query q = manager.createQuery(sql, Productos.class);
        manager.getTransaction().begin();
        q.executeUpdate();
    }

    public static int borrarProducto(int id) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("com.mycompany_productosCRUD_war_1.0-SNAPSHOTPU");
        EntityManager manager = factory.createEntityManager();
        String sql = "DELETE from productos WHERE id = " + id;
        Query q = manager.createNativeQuery(sql);
        manager.getTransaction().begin();
        int filasAfectadas = q.executeUpdate(); //para las consultas de modif. datos se usa el método executeUpdate
        manager.getTransaction().commit();
        return filasAfectadas;
    }

    public static int actualizaProducto() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("com.mycompany_productosCRUD_war_1.0-SNAPSHOTPU");
        EntityManager manager = factory.createEntityManager();
        String sql = "UPDATE Productos p SET p.categoria = 'zumos' WHERE p.id = 8";
        Query q = manager.createQuery(sql, Productos.class);
        manager.getTransaction().begin();
        int filasAfectadas = q.executeUpdate();
        manager.getTransaction().commit();
        //manager.close();

        return filasAfectadas;
    }

    public static int actualizaProducto2() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("com.mycompany_productosCRUD_war_1.0-SNAPSHOTPU");
        EntityManager manager = factory.createEntityManager();
        String sql = "UPDATE Productos p SET p.nombre = :nombre, p.imagen = :imagen, p.categoria = :categoria, p.precio = :precio WHERE p.id = 10";
        Query q = manager.createQuery(sql, Productos.class);
        q.setParameter("categoria", "Digestivos");
        q.setParameter("nombre", "Pacharán");
        q.setParameter("imagen", "pacharan.jpg");
        q.setParameter("precio", 4.0);
        manager.getTransaction().begin();
        int filasAfectadas = q.executeUpdate();
        manager.getTransaction().commit();
        //manager.close();
        return filasAfectadas;
    }

    public static int actualizaProducto(Productos miProducto) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("com.mycompany_productosCRUD_war_1.0-SNAPSHOTPU");
        EntityManager manager = factory.createEntityManager();
        String sql = "UPDATE Productos p SET p.nombre = :nombre, p.imagen = :imagen, p.categoria = :categoria, p.precio = :precio WHERE p.id = :id";
        Query q = manager.createQuery(sql, Productos.class);
        q.setParameter("id", miProducto.getId());
        q.setParameter("categoria", miProducto.getCategoria());
        q.setParameter("nombre", miProducto.getNombre());
        q.setParameter("imagen", miProducto.getImagen());
        q.setParameter("precio", miProducto.getPrecio());
        manager.getTransaction().begin();
        int filasAfectadas = q.executeUpdate();
        manager.getTransaction().commit();
        //manager.close();
        return filasAfectadas;
    }

    public static void insertaProducto(Productos producto) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("com.mycompany_productosCRUD_war_1.0-SNAPSHOTPU");
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        manager.merge(producto);
        manager.getTransaction().commit();
    }
}
