package kndy.html.menu;

import java.io.PrintWriter;
import java.util.Scanner;

public class Menu {

    static Bloque[] añadirElemento(Bloque[] x, Bloque n) {
        int nuevaPos = x.length;
        Bloque[] res = amplificArray(x);
        res[nuevaPos] = n;
        return res;
    }
    
    static Bloque[] reduceArray(Bloque[] x, int n){
        Bloque[] res = new Bloque[x.length-1];
        boolean suprimido = false;
        for(int i = 0; i<x.length; i++){
            if(i==n){
                suprimido = true;
            }
            else if(!suprimido){
                res[i]=x[i];
            }
            else{
                res[i-1]=x[i];
            }
        }
        return res;
    }

    static Bloque[] amplificArray(Bloque[] x) {
        Bloque[] res = new Bloque[x.length + 1];
        for (int i = 0; i < x.length; i++) {
            res[i] = x[i];
        }
        return res;
    }

    static void escribirArchivo(Bloque[] x, PrintWriter a, String b, String c) {
        a.println("<!DOCTYPE html>\n<html lang=\"es\">\n\t<head>\n\t\t<meta charset=\"UTF-8\" author=\"" + b + "\">\n\t\t<title>" + c + "</title>\n\t</head>\n\t<body>");
        for (int i = 0; i < x.length; i++) {
            a.println(x[i].toString());
        }
        a.print("\t</body>\n</html>");
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        PrintWriter pw;

        Bloque[] elementos = new Bloque[0];

        int menu = -1;
        int submenu;
        String nombreArchivo, autor, title;

        System.out.println("Escribe tu nombre (para el author=\"\")");
        autor = sc.nextLine();
        System.out.println("Escribe el título de la página (para el <title>)");
        title = sc.nextLine();

        while (menu != 0) {
            submenu = -1;
            System.out.println("[1] Añadir");
            System.out.println("[2] Eliminar");
            System.out.println("[3] Exportar");
            System.out.println("\n");
            System.out.println("[0] Salir");
            menu = Integer.parseInt(sc.nextLine());
            switch (menu) {
                case 1 -> {
                    while (submenu == -1) {
                        menu = -1;
                        System.out.println("\n¿Qué deseas añadir?");
                        System.out.println("[1] Tabla");
                        System.out.println("[2] Formulario");
                        System.out.println("\n");
                        System.out.println("[0] Atrás");
                        submenu = Integer.parseInt(sc.nextLine());
                        switch (submenu) {
                            case 1 -> {
                                System.out.println("Introduce ID de la tabla");
                                String id = sc.nextLine();
                                System.out.println("Introduce número de columnas");
                                int columnas = Integer.parseInt(sc.nextLine());
                                System.out.println("Introduce número de filas");
                                int filas = Integer.parseInt(sc.nextLine());
                                Table aa = new Table(filas, columnas, id);
                                elementos = añadirElemento(elementos, aa);
                            }
                            case 0 -> {
                            }
                            default -> {
                                System.err.println("\nComando incorrecto\n");
                            }
                        }
                    }
                }
                case 2 -> {
                    while (submenu != 0) {
                        if (elementos.length == 0) {
                            System.err.println("No hay elementos\n");
                        } else {
                            for (int i = 0; i < elementos.length; i++) {
                                System.out.println("[" + (i + 1) + "] " + elementos[i].getId());
                            }
                            System.out.println("\n\n");
                            System.out.println("[0] Salir");
                        }
                        submenu = Integer.parseInt(sc.nextLine());
                        switch (submenu){
                            case 0:
                                break;
                            default:
                                elementos = reduceArray(elementos, submenu-1);
                                break;
                        }
                    }
                }
                case 3 -> {
                    System.out.println("Introduce el nombre del archivo");
                    nombreArchivo = sc.nextLine();
                    try {
                        pw = new PrintWriter(System.getProperty("user.home") + "\\Documents\\" + nombreArchivo + ".html", "UTF-8");
                        escribirArchivo(elementos, pw, autor, title);
                        pw.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("\nArchivo guardado en " + System.getProperty("user.home") + "\\" + nombreArchivo + ".html\n");
                }
                case 0 -> {
                    System.out.println("Programa finalizado");
                    break;
                }
                default -> {
                    System.err.println("\nComando incorrecto\n");
                }
            }

        }

        sc.close();
    }
}
