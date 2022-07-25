package com.pixels.Inventario.Model.DatosE;

public class Venta {
    private int Codigo,Efectivo,TotalV;
    private String Fecha;

    public Venta(int codigo,String fecha, int efectivo, int totalV) {
        this.Codigo = codigo;
        this.Efectivo = efectivo;
        this.TotalV = totalV;
        this.Fecha = fecha;
    }

    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int codigo) {
        Codigo = codigo;
    }

    public int getEfectivo() {
        return Efectivo;
    }

    public void setEfectivo(int efectivo) {
        Efectivo = efectivo;
    }

    public int getTotalV() {
        return TotalV;
    }

    public void setTotalV(int totalV) {
        TotalV = totalV;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }
}
