package com.danny.propiedades.model;

public class Propiedad {
    private String id;
    private String nombre;
    private String estado;
    private double precio;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

/* {
        "id": "eb1891a2-9731-4ee5-8c15-deb080f4d262",
            "nombre": "macororo 1",
            "estado": "INHABILITADO",
            "precio": 3
    }*/
}
