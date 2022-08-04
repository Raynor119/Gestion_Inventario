package com.pixels.Inventario.Model.DatosE;

public class VentasProductos {
    private int Id,CodigoV,CostePV,PrecioPV,Iva;
    private String CodigoP,EstadoDevolucion,ObservacionD;
    private double CantidadV;

    public VentasProductos(int id,int codigoV,String codigoP, double cantidadV,int costePV,int precioPV,int iva,String estadoDevolucion,String observacionD) {
        this.Id=id;
        this.CodigoV = codigoV;
        this.CantidadV = cantidadV;
        this.CodigoP = codigoP;
        this.CostePV=costePV;
        this.PrecioPV=precioPV;
        this.EstadoDevolucion=estadoDevolucion;
        this.ObservacionD=observacionD;
        this.Iva=iva;
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

    public int getIva() {
        return Iva;
    }

    public void setIva(int iva) {
        Iva = iva;
    }

    public String getCodigoP() {
        return CodigoP;
    }

    public void setCodigoP(String codigoP) {
        CodigoP = codigoP;
    }

    public String getEstadoDevolucion() {
        return EstadoDevolucion;
    }

    public void setEstadoDevolucion(String estadoDevolucion) {
        EstadoDevolucion = estadoDevolucion;
    }

    public String getObservacionD() {
        return ObservacionD;
    }

    public void setObservacionD(String observacionD) {
        ObservacionD = observacionD;
    }

    public double getCantidadV() {
        return CantidadV;
    }

    public void setCantidadV(double cantidadV) {
        CantidadV = cantidadV;
    }
}
