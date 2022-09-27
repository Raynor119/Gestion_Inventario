package com.pixels.Inventario.Model.DatosE;

public class DatosVenta {
    private boolean Verificar;
    private int Id,Efectivo;
    private String Fecha;
    public DatosVenta(boolean verificar,int id,String fecha,int efectivo){
        this.Verificar=verificar;
        this.Id=id;
        this.Fecha=fecha;
        this.Efectivo=efectivo;
    }

    public boolean isVerificar() {
        return Verificar;
    }

    public void setVerificar(boolean verificar) {
        Verificar = verificar;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
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
