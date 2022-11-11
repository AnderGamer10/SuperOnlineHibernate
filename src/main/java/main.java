import Clases.*;

import java.util.Scanner;

public class main {
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args){
        Inventario.getInstance();

        int opcion = 1;

        System.out.println("========================================================================");
        System.out.println("=                 SISTEMA DE GESTION DE SUPER-ON-LINE                  =");
        System.out.println("========================================================================\n\n");


        while (opcion != 0) {
            mostrarMenuInventario();
            System.out.println("Opción? ");
            opcion = leerOpcion(7); // hay 6 acciones principales sobre el inventario + terminar la aplicaciÃ³n
            switch(opcion) {
                case 0:	System.out.println("Termina la aplicación");
                    System.exit(0);
                case 1: Inventario.mostrarProductos();
                    pause();
                    break;
                case 2:     //actualizar la cantidad de un producto en almancÃ©n
                    Inventario.mostrarProductos();
                    Scanner es = new Scanner (System.in);
                    System.out.println("Selecciona un producto");
                    int codigo = es.nextInt();
                    System.out.println("Introduce una cantidad");
                    int cantidad = es.nextInt();
                    Inventario.actualizarCantidad(codigo,cantidad);
                    pause();

                    opcion=1; break;
                case 3:
                    //aÃ±adir un nuevo producto al almancÃ©n
                    while (opcion!=0) {
                        mostrarMenuAddNuevoProducto();
                        System.out.println("Opción? ");
                        opcion = leerOpcion(6); // 5 clases de productos y salida del menÃº
                        if (opcion!=0) {
                            Producto P = nuevoProducto(opcion);
                            Inventario.addNuevoProducto(P);
                            System.out.println();
                            System.out.println();
                            pause();
                        }
                    }
                    opcion=1; break;
                case 4:
                    Inventario.mostrarProductos();
                    Inventario.eliminarProducto();
                    pause();
                    break;
                case 5: Inventario.mostarProductosEnviables();
                    pause();
                    break;
            }
        }
    }

    public static void mostrarMenuInventario() {// ver productos del super ordenados
        System.out.println("====================================================================");
        System.out.println("=            MENU PRINCIPAL - GESTIÓN DE INVENTARIO                =");
        System.out.println("====================================================================");
        System.out.println("            tecla:1   - Mostrar inventario");
        System.out.println("            tecla:2   - Actualizar existencias");
        System.out.println("            tecla:3   - Añadir nuevo producto");
        System.out.println("            tecla:4   - Eliminar un producto");
        System.out.println("            tecla:5   - Mostrar productos enviables");
        System.out.println("            tecla:0   - Terminar");
        System.out.println("====================================================================");
    }


    public static void mostrarMenuAddNuevoProducto() {// ver productos y escoger uno
        System.out.println("====================================================");
        System.out.println("=   Elige el tipo de producto que quieres añadir:  =");
        System.out.println("====================================================");
        System.out.println("         tecla:1 - Lacteo");
        System.out.println("         tecla:2 - Frutas u hortalizas");
        System.out.println("         tecla:3 - Bebidas");
        System.out.println("         tecla:4 - Herramientas");
        System.out.println("         tecla:5 - Otros");
        System.out.println("         tecla:0 - Cancelar");
        System.out.println("====================================================");
    }

    public static Producto nuevoProducto (int n) {
        Scanner es = new Scanner (System.in);
        Producto P;
        switch(n) {
            case 1: P = new Lacteo(es);break;
            case 2: P = new FrutaHortaliza(es);break;
            case 3: P = new Bebida(es);break;
            case 4: P = new Herramienta(es);break;
            default: P = new Otros(es);break;
        }
        return P;
    }

    private static void pause() {
        System.out.println("(pulsa 0 para continuar...");
        leerOpcion(1);
    }


    private static int leerOpcion(int max) {
        boolean terminar = false;
        int n = 0;
        while (!terminar) {
            try {
                n = in.nextInt();
                in.nextLine();
                if (n>=max || n<0) {
                    throw new Exception();
                }
                terminar = true;
            } catch (Exception e) {
                System.out.println("Opción incorrecta! elije de nuevo");
                in.nextLine();
            }
        }
        return n;
    }
}