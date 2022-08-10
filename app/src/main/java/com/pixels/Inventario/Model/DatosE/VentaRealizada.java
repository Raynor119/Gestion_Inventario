package com.pixels.Inventario.Model.DatosE;

public class VentaRealizada {
    private boolean Verificar;
    private String CodigoVenta;
    public VentaRealizada(boolean verificar,String codigoVenta){
        this.Verificar=verificar;
        this.CodigoVenta=codigoVenta;
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
}
