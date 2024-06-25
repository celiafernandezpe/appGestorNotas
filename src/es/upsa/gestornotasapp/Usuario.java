/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.upsa.gestornotasapp;

/**
 *
 * @author administrador
 */
public abstract class Usuario {
    
    protected String nombre;
    protected String password;
    
    public Usuario(String nombre, String password) {
        this.nombre = nombre;
        this.password = password;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public boolean validarPassword(String password) {
        return this.password.equals(password);
    }
    
    public abstract void mostrarMenu();
    
}
