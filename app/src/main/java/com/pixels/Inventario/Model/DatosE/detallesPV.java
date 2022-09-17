package com.pixels.Inventario.Model.DatosE;

public class detallesPV {

    private int Id,CodigoV;
    private String CodigoP,Nombre,TipoC,ObservacionD;
    private double CantidadV,CantidadD;
    private int CostePV,PrecioPV,IvaPV;

    public detallesPV(int id,int codigoV,String codigoP,String nombre,String tipoC,double cantidadV,double cantidadD,int costePV,int precioPV,int ivaPV,String observacionD){
        this.Id=id;
        this.CodigoV=codigoV;
        this.CodigoP=codigoP;
        this.Nombre=nombre;
        this.TipoC=tipoC;
        this.CantidadV=cantidadV;
        this.CantidadD=cantidadD;
        this.CostePV=costePV;
        this.PrecioPV=precioPV;
        this.IvaPV=ivaPV;
        this.ObservacionD=observacionD;
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

    public String getCodigoP() {
        return CodigoP;
    }

    public void setCodigoP(String codigoP) {
        CodigoP = codigoP;
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

    public double getCantidadD() {
        return CantidadD;
    }

    public void setCantidadD(double cantidadD) {
        CantidadD = cantidadD;
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

    public int getIvaPV() {
        return IvaPV;
    }

    public void setIvaPV(int ivaPV) {
        IvaPV = ivaPV;
    }
}
