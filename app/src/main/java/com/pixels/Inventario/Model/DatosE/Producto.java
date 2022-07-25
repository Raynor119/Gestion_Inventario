package com.pixels.Inventario.Model.DatosE;

public class Producto {
    private String Codigo,Nombre;
    private int Cantidad,CosteP,Precio;

    public Producto(String codigo, String nombre, int cantidad, int costeP, int precio) {
        this.Codigo = codigo;
        this.Nombre = nombre;
        this.Cantidad = cantidad;
        this.CosteP = costeP;
        this.Precio = precio;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int cantidad) {
        Cantidad = cantidad;
    }

    public int getCosteP() {
        return CosteP;
    }

    public void setCosteP(int costeP) {
        CosteP = costeP;
    }

    public int getPrecio() {
        return Precio;
    }

    public void setPrecio(int precio) {
        Precio = precio;
    }
}
