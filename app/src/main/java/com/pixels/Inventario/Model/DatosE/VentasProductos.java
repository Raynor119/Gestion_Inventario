package com.pixels.Inventario.Model.DatosE;

public class VentasProductos {
    private int Id,CodigoV,CostePV,PrecioPV;
    private String CodigoP;
    private double CantidadV;

    public VentasProductos(int id,int codigoV,String codigoP, double cantidadV,int costePV,int precioPV) {
        this.Id=id;
        this.CodigoV = codigoV;
        this.CantidadV = cantidadV;
        this.CodigoP = codigoP;
        this.CostePV=costePV;
        this.PrecioPV=precioPV;
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

    public int getCostePV() {
        return CostePV;
    }

    public void setCostePV(int costePV) {
        CostePV = costePV;
    }

    public int getPrecioPV() {
        return PrecioPV;
    }

    public void setPrecioPV(int precioPV) {
        PrecioPV = precioPV;
    }

    public String getCodigoP() {
        return CodigoP;
    }

    public void setCodigoP(String codigoP) {
        CodigoP = codigoP;
    }

    public double getCantidadV() {
        return CantidadV;
    }

    public void setCantidadV(double cantidadV) {
        CantidadV = cantidadV;
    }
}
