/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.upsa.gestornotasapp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author administrador
 */
public class UsuarioAdministrador extends Usuario implements InterfazGestionarNota {

    private Gestor gestor;

    //Constructor, accedemos a la clase de la que heredamos con la palabra super para llamar a su constrcutor
    public UsuarioAdministrador(String nombre, String password, Gestor gestor) {
        super(nombre, password);
        this.gestor = gestor;
    }

    //Metodo extendido de la clase abstracta Usuario
    @Override
    public void mostrarMenu() {
        System.out.println("\nOpciones de Administración:");
        System.out.println("1 - Ver listado de notas");
        System.out.println("2 -  Agregar Nota (como administrador)");
        System.out.println("3 - Editar Nota");
        System.out.println("4 - Eliminar Nota");
        System.out.println("5 - Salir\n");
    }

    //Metodos abstractos de la InterfazGestionarNotas
    @Override
    public void visualizarNotas() {
        List<Categoria> categorias = gestor.getCategorias();

        // Comprobamos si todavía no se ha introducido ninguna nota
        if (categorias.isEmpty()) {
            System.out.println("\nTodavía no se ha agregado ninguna nota\n");
            return;
        }

        // Definir el formateador para las fechas
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");


        System.out.println("\nListado de notas:");
        for (Categoria categoria : categorias) {
            for (Nota nota : categoria.getNotas()) {
                System.out.println("Título: " + nota.getTitulo());
                System.out.println("Categoría: " + categoria.getNombre());
                System.out.println("Contenido: " + nota.getContenido());
                System.out.println("Fecha creación: " + nota.getFechaCreacion().format(formatter));
                System.out.println("Fecha modificación: " + nota.getFechaUltimaModificacion().format(formatter));
                System.out.println("Usuario: " + nota.getUsuario().getNombre());
                System.out.println();
            }
        }
    }

    @Override
    public void agregarNota() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el título de la nota:");
        String titulo = scanner.nextLine();

        System.out.println("Ingrese el contenido de la nota:");
        String contenido = scanner.nextLine();

        System.out.println("Ingrese la categoría de la nota:");
        String nombreCategoria = scanner.nextLine();

        // Obtener la fecha y hora actual
        LocalDateTime fechaActual = LocalDateTime.now();

        Nota nuevaNota = new Nota(titulo, contenido, fechaActual, fechaActual, this);
        gestor.agregarNota(nombreCategoria, nuevaNota);
    }

    @Override
    public void editarNota() {
        Scanner scanner = new Scanner(System.in);

        //pedimos al usuario los datos de la nota a editar
        System.out.println("\nIngrese el título de la nota que desea editar:");
        String titulo = scanner.nextLine();

        System.out.println("Ingrese la categoría de la nota:");
        String nombreCategoria = scanner.nextLine();

        // buscamos la nota en el gestor para editarla
        Nota notaAEditar = null;
        //recorremos las categorias
        for (Categoria categoria : gestor.getCategorias()) {
            //hasta encontrar la categoria que ha introducido el admin
            if (categoria.getNombre().equals(nombreCategoria)) {
                //recorremos las notas de esa categoría
                for (Nota nota : categoria.getNotas()) {
                    //buscamos la nota por el titulo, no hace falta comprobar el usuario porque el administrador pueder editar cualquier nota
                    if (nota.getTitulo().equals(titulo)) {
                        notaAEditar = nota;
                        break;
                    }
                }
            }
            //si no encontramos la nota salimos del bucle
            if (notaAEditar != null) {
                break;
            }
        }

        //si encontramos la nota le pedimos los datos que quiere editar
        if (notaAEditar != null) {
            System.out.println("\nIngrese el nuevo título de la nota (deje en blanco para no cambiar):");
            String nuevoTitulo = scanner.nextLine();

            System.out.println("Ingrese el nuevo contenido de la nota (deje en blanco para no cambiar):");
            String nuevoContenido = scanner.nextLine();

            System.out.println("Ingrese la nueva categoría de la nota (deje en blanco para no cambiar):");
            String nuevaCategoria = scanner.nextLine();

            gestor.editarNota(nombreCategoria, notaAEditar, nuevoTitulo, nuevoContenido, nuevaCategoria);
            System.out.println("\nNota editada con éxito.");
        } else { //si no hemos encontrado la nota entrará en este else y notificaremos que la nota no ha sido encontrada
            System.out.println("\nNota no encontrada.");
        }
    }

    @Override
    public void eliminarNota() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el título de la nota que desea eliminar:");
        String titulo = scanner.nextLine();

        System.out.println("Ingrese la categoría de la nota:");
        String nombreCategoria = scanner.nextLine();

        // Buscar la nota en el gestor para eliminarla
        Nota notaAEliminar = null;
        for (Categoria categoria : gestor.getCategorias()) {
            if (categoria.getNombre().equals(nombreCategoria)) {
                for (Nota n : categoria.getNotas()) {
                    //no hace falta comprobar que el usuario porque el administrador pueder eliminar cualquier nota
                    if (n.getTitulo().equals(titulo)) {
                        notaAEliminar = n;
                        break;
                    }
                }
            }
            if (notaAEliminar != null) {
                break;
            }
        }

        if (notaAEliminar != null) {
            gestor.eliminarNota(nombreCategoria, notaAEliminar);
            System.out.println("Nota eliminada con éxito.");
        } else {
            System.out.println("Nota no encontrada.");
        }
    }

}
