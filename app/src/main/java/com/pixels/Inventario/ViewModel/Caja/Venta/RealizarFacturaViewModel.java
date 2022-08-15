package com.pixels.Inventario.ViewModel.Caja.Venta;

import androidx.lifecycle.ViewModel;

import com.pixels.Inventario.Model.Basededatos.SQLite.DatosInicio.DatosInicio;
import com.pixels.Inventario.Model.Basededatos.SQLite.DatosInicio.consultasDatos;
import com.pixels.Inventario.Model.Factura.FacturaPDF;
import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Activity.Caja.Caja;

public class RealizarFacturaViewModel extends ViewModel {
    public FacturaPDF factura;
    public RealizarFacturaViewModel() {
    }
    public void crearFactura(Caja context, String codigoV,int efectivo){
        consultasDatos bd=new consultasDatos(context);
        factura=new FacturaPDF(context,codigoV,efectivo);
        factura.AbrirFactura();
        factura.addDatos("Factura_"+codigoV,"VentaRealizada",""+context.getString(R.string.app_name));
    }

}
