package com.pixels.Inventario.Model.DatosE;

public class Producto {
    private String Codigo,Nombre,TipoC;
    private int CosteP,Precio;
    private double Cantidad;

    public Producto(String codigo, String nombre, double cantidad,String tipoC, int costeP, int precio) {
        this.Codigo = codigo;
        this.Nombre = nombre;
        this.Cantidad = cantidad;
        this.TipoC=tipoC;
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

    public String getTipoC() {
        return TipoC;
    }

    public void setTipoC(String tipoC) {
        TipoC = tipoC;
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

    public double getCantidad() {
        return Cantidad;
    }

    public void setCantidad(double cantidad) {
        Cantidad = cantidad;
    }
}
