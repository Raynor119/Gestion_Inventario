package com.pixels.Inventario.Model.DatosE;

public class Venta {
    private int Codigo,Efectivo;
    private String Fecha;

    public Venta(int codigo,String fecha, int efectivo) {
        this.Codigo = codigo;
        this.Efectivo = efectivo;
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

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }
}
