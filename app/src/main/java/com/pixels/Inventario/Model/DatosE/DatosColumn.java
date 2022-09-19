package com.pixels.Inventario.Model.DatosE;

public class DatosColumn {
    private String Codigo,Nombre;
    private int TotalV;
    public DatosColumn(String codigo,String nombre,int totalV){
        this.Codigo=codigo;
        this.Nombre=nombre;
        this.TotalV=totalV;
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

    public int getTotalV() {
        return TotalV;
    }

    public void setTotalV(int totalV) {
        TotalV = totalV;
    }
}
