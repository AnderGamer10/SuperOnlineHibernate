package Clases;

import Interfaces.Enviable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Inventario {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("aplicacion");;
    private static EntityManager manager = emf.createEntityManager();

   private static Path archivoProductos = Path.of("src/data/productos.txt");
    private static ArrayList<Producto> listaProductos;
    private static Inventario instance;
    private Inventario(){
        listaProductos = new ArrayList<Producto>();
    }
    public static Inventario getInstance(){
        if (instance == null){
            instance = new Inventario();
        }
        return instance;
    }

    public static void mostrarProductos(){
        List<Producto> producto = (List<Producto>) manager.createQuery("FROM Producto").getResultList();

        producto.forEach((e) -> System.out.printf(e.toString()));

        System.out.printf("Hay %d productos.", producto.size());

    }

    public static void addNuevoProducto(Producto tipo){
        manager.getTransaction().begin();
        manager.persist(tipo);
        manager.getTransaction().commit();
    }

    public static Producto actualizarCantidad(int codigo, int cantidad){
        Query query = manager.createQuery("FROM Producto WHERE id = :codigo ");
        query.setParameter("codigo", codigo);

        Producto producto = (Producto) query.getResultList().get(0);
        producto.setCantidad(cantidad);

        return producto;
    }

    public static void mostarProductosEnviables(){
        List<Producto> producto = (List<Producto>) manager.createQuery("FROM Producto").getResultList();

        producto.forEach((e) -> {
            if (e instanceof Enviable) {
                System.out.println(e.volcar());
            }
        });
    }
//    public static int tamaño(){
//        List<Producto> producto = (List<Producto>) manager.createQuery("FROM Producto").getResultList();
//        return producto.size();
//    }
    public static void eliminarProducto(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nombre del producto:");
        String nombre = sc.nextLine();

        Query query = manager.createQuery("DELETE FROM Producto WHERE nombre = :nombre ");
        query.setParameter("nombre", nombre);

        manager.getTransaction().begin();
        query.executeUpdate();
        manager.getTransaction().commit();
    }
}

