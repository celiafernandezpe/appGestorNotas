/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.upsa.gestornotasapp;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author administrador
 */
public class Categoria {
    
    private String nombre;
    private List<Nota> notas;

    //Constructor (control + i)
    public Categoria(String nombre) {    
        this.nombre = nombre;
        this.notas = new ArrayList<>();
    }

    //Getter y setter
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Nota> getNotas() {
        return notas;
    }
    
    //No insertamos un setter para la lista de notas dado que la modificamos a traves de los metodos definidos debajo

    
    //Metodos para modificar la lista de notas
    
    //Modificar para ver si la nota ya esta añadida y preguntar si quiere añadirla de nuevo
    //en caso de que no modificar la fecha de modificación a la actual
    public void agregarNota(Nota nota) {
        this.notas.add(nota);
    }
    
    public void eliminarNota(Nota nota) {
        this.notas.remove(nota);
    }
    
}

