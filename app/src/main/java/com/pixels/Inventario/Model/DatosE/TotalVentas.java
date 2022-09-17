package com.pixels.Inventario.Model.DatosE;

public class TotalVentas {
    private int CodigoV,CProductoV,TotalV,CostoV,PerdidaD,TotalD,Efectivo;
    private String Fecha;
    private double GananciaNeta,TotalIvaP;
    public TotalVentas(int codigoV,int cProductoV,int totalV,double gananciaNeta,double totalIvaP,int costoV,int perdidaD,int totalD,String fecha,int efectivo){
        this.CodigoV=codigoV;
        this.CProductoV=cProductoV;
        this.TotalV=totalV;
        this.GananciaNeta=gananciaNeta;
        this.TotalIvaP=totalIvaP;
        this.CostoV=costoV;
        this.PerdidaD=perdidaD;
        this.TotalD=totalD;
        this.Fecha=fecha;
        this.Efectivo=efectivo;
    }

    public int getEfectivo() {
        return Efectivo;
    }

    public void setEfectivo(int efectivo) {
        Efectivo = efectivo;
    }

    public int getCodigoV() {
        return CodigoV;
    }

    public void setCodigoV(int codigoV) {
        CodigoV = codigoV;
    }

    public int getCProductoV() {
        return CProductoV;
    }

    public void setCProductoV(int CProductoV) {
        this.CProductoV = CProductoV;
    }

    public int getTotalV() {
        return TotalV;
    }

    public void setTotalV(int totalV) {
        TotalV = totalV;
    }

    public int getCostoV() {
        return CostoV;
    }

    public void setCostoV(int costoV) {
        CostoV = costoV;
    }

    public int getPerdidaD() {
        return PerdidaD;
    }

    public void setPerdidaD(int perdidaD) {
        PerdidaD = perdidaD;
    }

    public int getTotalD() {
        return TotalD;
    }

    public void setTotalD(int totalD) {
        TotalD = totalD;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public double getGananciaNeta() {
        return GananciaNeta;
    }

    public void setGananciaNeta(double gananciaNeta) {
        GananciaNeta = gananciaNeta;
    }

    public double getTotalIvaP() {
        return TotalIvaP;
    }

    public void setTotalIvaP(double totalIvaP) {
        TotalIvaP = totalIvaP;
    }
}
