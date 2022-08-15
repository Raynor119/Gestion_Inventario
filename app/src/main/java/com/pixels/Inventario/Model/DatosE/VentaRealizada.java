package com.pixels.Inventario.Model.DatosE;

public class VentaRealizada {
    private boolean Verificar;
    private String CodigoVenta;
    private String Fecha;
    public VentaRealizada(boolean verificar,String codigoVenta,String fecha){
        this.Verificar=verificar;
        this.CodigoVenta=codigoVenta;
        this.Fecha=fecha;
    }

    public boolean isVerificar() {
        return Verificar;
    }

    public void setVerificar(boolean verificar) {
        Verificar = verificar;
    }

    public String getCodigoVenta() {
        return CodigoVenta;
    }

    public void setCodigoVenta(String codigoVenta) {
        CodigoVenta = codigoVenta;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }
}
