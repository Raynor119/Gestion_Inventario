package com.pixels.Inventario.Model.DatosE;

public class VentasProductos {
    private int CodigoV,CantidadV;
    private String CodigoP;

    public VentasProductos(int codigoV,String codigoP, int cantidadV) {
        this.CodigoV = codigoV;
        this.CantidadV = cantidadV;
        this.CodigoP = codigoP;
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
