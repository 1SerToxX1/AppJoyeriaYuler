/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appjoyeriayuler.model.cliente;

public class Cliente {

    private int idCliente;
    private String nombre;
    private String dni;
    private String ruc;
    private String telefono;

    // Constructor vacío
    public Cliente() {
    }

    // Constructor completo
    public Cliente(int idCliente, String nombre, String dni, String ruc, String telefono) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.dni = dni;
        this.ruc = ruc;
        this.telefono = telefono;
    }

    // Getters y Setters
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
