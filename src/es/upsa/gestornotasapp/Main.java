/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package es.upsa.gestornotasapp;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author administrador
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    //Utilizamos un Hashset para almacenar los usuarios para asegurarnos que no hay usuarios repetidos
    private static Set<UsuarioNormal> usuarios = new HashSet<>();
    private static Gestor gestor = new Gestor();

    //Creamos al administrador y a un usuario de prueba
    private static UsuarioAdministrador admin = new UsuarioAdministrador("admin", "1234", gestor);
    private static UsuarioNormal usuarioPrueba = new UsuarioNormal("alvaro", "123", gestor);

    public static void main(String[] args) {

        //Añadimos el usuario de prueba a la lsita de usuarios
        usuarios.add(usuarioPrueba);

        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("1 - Registrarse");
            System.out.println("2 - Iniciar sesión");
            System.out.println("3 - Salir");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    registrarUsuario(scanner);
                    break;
                case 2:
                    //Llamamos a la funcion de iniciar sesion y esta nos devuelve si se ha registrador un usuario normal o el administrador
                    String user = iniciarSesion(scanner);
                    //Si devuelve null el inicio de sesión no es correcto y se vuelve a ejecutar el bucle
                    if (user != null) {
                        //comprobamos si el usaurio logueado es un admin 
                        if (user.equals("admin")) {
 
                            boolean continuarAdmin = true;
                            while (continuarAdmin) {
                                //Función para mostrar las opciones de menú del administrador
                                admin.mostrarMenu();
                                
                                //Recogemos la opción introducida
                                int adminOpcion = scanner.nextInt();
                                scanner.nextLine();

                                switch (adminOpcion) {
                                    case 1:
                                        admin.visualizarNotas();
                                        break;
                                    case 2:
                                        admin.agregarNota();
                                        break;
                                    case 3:
                                        admin.editarNota();
                                        break;
                                    case 4:
                                        admin.eliminarNota();
                                        break;
                                    case 5:
                                        continuarAdmin = false;
                                        break;
                                    default:
                                        System.out.println("Opción no válida.");
                                }
                            }
                        //en caso contrario siempre va a ser un usuario normal    
                        } else {
                            // llamamos a la función que nos busca por el nombre del usuario logueado y esta nos devuelve un objeto de tipo usuarioNormal que será el usuario logueado 
                            UsuarioNormal usuarioLogueado = buscarUsuarioPorNombre(user);
                            // comprobamos que el usuario este bien logueado y que no devuelva null
                            if (usuarioLogueado != null) {
                                boolean continuarUsuario = true;
                                while (continuarUsuario) {
                                    usuarioLogueado.mostrarMenu();
                                    int userOpcion = scanner.nextInt();
                                    scanner.nextLine();

                                    switch (userOpcion) {
                                        case 1:
                                            usuarioLogueado.visualizarNotas();
                                            break;
                                        case 2:
                                            usuarioLogueado.agregarNota();
                                            break;
                                        case 3:
                                            usuarioLogueado.editarNota();
                                            break;
                                        case 4:
                                            usuarioLogueado.eliminarNota();
                                            break;
                                        case 5:
                                            usuarioLogueado.verNotasPorCategoria();
                                            break;
                                        case 6:
                                            continuarUsuario = false;
                                            break;
                                        default:
                                            System.out.println("Opción no válida.");
                                    }
                                }
                            }
                        }
                    }
                    ;
                    break;
                case 3:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }

        scanner.close();

    }

    //Funciones para gestionar los usuarios
    private static void registrarUsuario(Scanner scanner) {
        System.out.println("\nIngrese su nombre de usuario:");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese su contraseña:");
        String password = scanner.nextLine();

        UsuarioNormal nuevoUsuario = new UsuarioNormal(nombre, password, gestor);
        if (usuarios.add(nuevoUsuario)) {
            System.out.println("Usuario registrado con éxito.\n");
        } else {
            System.out.println("El nombre de usuario ya existe. Intente con otro nombre.\n");
        }
    }

    private static String iniciarSesion(Scanner scanner) {
        System.out.println("\nIngrese su nombre de usuario:");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese su contraseña:");
        String password = scanner.nextLine();

        if (admin.getNombre().equals(nombre) && admin.validarPassword(password)) {
            System.out.println("Inicio de sesión como Administrador exitoso.\n");
            return "admin";
        } else {
            UsuarioNormal usuario = buscarUsuario(nombre, password);
            if (usuario != null) {
                System.out.println("Inicio de sesión como Usuario exitoso.\n");
                return usuario.getNombre();
            } else {
                System.out.println("Nombre de usuario o contraseña incorrectos.\n");
                return null;
            }
        }
    }

    private static UsuarioNormal buscarUsuario(String nombre, String password) {
        for (UsuarioNormal usuario : usuarios) {
            if (usuario.getNombre().equals(nombre) && usuario.validarPassword(password)) {
                return usuario;
            }
        }
        return null;
    }

    private static UsuarioNormal buscarUsuarioPorNombre(String nombre) {
        for (UsuarioNormal usuario : usuarios) {
            if (usuario.getNombre().equals(nombre)) {
                return usuario;
            }
        }
        return null;
    }

}
