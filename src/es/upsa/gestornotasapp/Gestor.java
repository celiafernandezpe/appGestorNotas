/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.upsa.gestornotasapp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author administrador
 */
public class Gestor {

    private List<Categoria> categorias;

    public Gestor() {
        this.categorias = new ArrayList<>();
    }

    public void agregarCategoria(Categoria categoria) {
        categorias.add(categoria);
    }

    public void eliminarCategoria(Categoria categoria) {
        categorias.remove(categoria);
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void agregarNota(String nombreCategoria, Nota nota) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        for (Categoria categoria : categorias) {
            if (categoria.getNombre().equals(nombreCategoria)) {
                for (Nota n : categoria.getNotas()) {
                    if (n.getTitulo().equals(nota.getTitulo())) {
                        System.out.println("Esta nota ya ha sido introducida en esta categoría");
                        System.out.println("Título: " + n.getTitulo());
                        System.out.println("Categoría: " + categoria.getNombre());
                        System.out.println("Contenido: " + n.getContenido());
                        System.out.println("Fecha creación: " + n.getFechaCreacion().format(formatter));
                        System.out.println("Fecha modificación: " + n.getFechaUltimaModificacion().format(formatter));
                        System.out.println("Usuario: " + n.getUsuario().getNombre());
                        System.out.println();
                        return;
                    }
                }
                categoria.agregarNota(nota);
                return;
            }
        }
        // Si no existe la categoría, crearla y agregar la nota
        Categoria nuevaCategoria = new Categoria(nombreCategoria);
        nuevaCategoria.agregarNota(nota);
        categorias.add(nuevaCategoria);
    }

    public void editarNota(String nombreCategoria, Nota nota, String nuevoTitulo, String nuevoContenido, String nuevaCategoria) {
        if (!nuevoTitulo.isEmpty()) {
            nota.setTitulo(nuevoTitulo);
        }
        if (!nuevoContenido.isEmpty()) {
            nota.setContenido(nuevoContenido);
        }
        if (!nuevaCategoria.isEmpty() && !nuevaCategoria.equals(nombreCategoria)) {
            // Eliminar la nota de la categoría actual
            for (Categoria categoria : categorias) {
                if (categoria.getNombre().equals(nombreCategoria)) {
                    categoria.eliminarNota(nota);
                    break;
                }
            }
            // Agregar la nota a la nueva categoría
            agregarNota(nuevaCategoria, nota);
        }
        // Actualizar la fecha de última modificación
        nota.setFechaUltimaModificacion(LocalDateTime.now());
    }

    public void eliminarNota(String nombreCategoria, Nota nota) {
        for (Categoria categoria : categorias) {
            if (categoria.getNombre().equals(nombreCategoria)) {
                categoria.eliminarNota(nota);
                return;
            }
        }
    }

    public List<Nota> getNotas(String nombreCategoria) {
        for (Categoria categoria : categorias) {
            if (categoria.getNombre().equals(nombreCategoria)) {
                return categoria.getNotas();
            }
        }
        return new ArrayList<>();
    }
}
