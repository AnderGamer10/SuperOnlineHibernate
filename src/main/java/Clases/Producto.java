package Clases;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Scanner;

@Entity
@Table(name = "Producto")
abstract public class Producto implements Serializable {
    private static final long serialVersionUID = 1L;
    private double iva, precio, peso;
    private int codigo, cantidad;
    private String nombre;

    public Producto(){

    }
    public Producto(int codigo, String nombre,double precio, double peso,int cantidad) {
        this.precio = precio;
        this.peso = peso;
        this.codigo = codigo;
        this.cantidad = cantidad;
        this.nombre = nombre;
    }
    public Producto(Scanner in){
//        codigo = Inventario.tamaño()+1;
        System.out.println("Introduce un nombre:");
        while (true){
            try{
                nombre = in.nextLine();
                break;
            }catch (Exception e){
                System.out.println("nombre incorrecto, vuelva a introducirlo");
            }
        }

        System.out.println("Introduce un precio:");
        while (true){
            try{
                precio = in.nextDouble();
                break;
            }catch (Exception e){
                System.out.println("Precio incorrecto, vuelva a introducirlo");
            }finally {
                in.nextLine();
            }
        }

        System.out.println("Introduce un peso:");
        while (true){
            try{
                peso = in.nextDouble();
                break;
            }catch (Exception e){
                System.out.println("Peso incorrecto, vuelva a introducirlo");
            }finally {
                in.nextLine();
            }
        }

        System.out.println("Introduce una cantidad:");
        while (true){
            try{
                cantidad = in.nextInt();
                break;
            }catch (Exception e){
                System.out.println("Cantidad incorrecta, vuelva a introducirlo");
            }finally {
                in.nextLine();
            }
        }
    }

    public double calcularPrecioIVA(){
        return (precio * getIva()) * precio;
    }
    public void imprimir(){
        System.out.print("Id: " + getCodigo() + " Nombre: " + getNombre() + " Stock " + getCantidad() + " Precio " + getPrecio());
    }
    public void imprimirEnvio(){
        System.out.println("Id: " + getCodigo() + " Nombre: " + getNombre() + " Peso " + getPeso() + " IVA: " + calcularPrecioIVA());
    }
    public String volcar(){
        return codigo + " " + nombre + " " + precio + " " + cantidad + " " + peso + " ";
    }

    @Column(name = "iva")
    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    @Column(name = "precio")
    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Column(name = "peso")
    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    @Id
    @GeneratedValue
    @Column(name = "id", unique = true , nullable = false )
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    @Column(name = "cantidad")
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Column(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Id= " + codigo +
                ", Nombre= " + nombre +
                ", Precio= " + precio +
                ", Peso= " + peso +
                ", Cantidad= " + cantidad +
                "\n";
    }
}