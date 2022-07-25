package com.pixels.Inventario.Model.DatosE;

public class VentasProductos {
    private int Id,CodigoV,CantidadV;
    private String CodigoP;

    public VentasProductos(int id,int codigoV,String codigoP, int cantidadV) {
        this.Id=id;
        this.CodigoV = codigoV;
        this.CantidadV = cantidadV;
        this.CodigoP = codigoP;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getCodigoV() {
        return CodigoV;
    }

    public void setCodigoV(int codigoV) {
        CodigoV = codigoV;
    }

    public int getCantidadV() {
        return CantidadV;
    }

    public void setCantidadV(int cantidadV) {
        CantidadV = cantidadV;
    }

    public String getCodigoP() {
        return CodigoP;
    }

    public void setCodigoP(String codigoP) {
        CodigoP = codigoP;
    }
}
