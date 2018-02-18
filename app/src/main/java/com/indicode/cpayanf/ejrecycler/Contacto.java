package com.indicode.cpayanf.ejrecycler;

/**
 * Created by gmrlenovopayan on 2018-02-17.
 */

public class Contacto{
    private int foto;
    private String nombre;
    private String telefono;
    private String email;

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Contacto(String nombre, String telefono, String email, int foto){
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.foto = foto;
    }

}
