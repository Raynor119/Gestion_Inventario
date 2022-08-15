package com.pixels.Inventario.ViewModel.Caja.Venta;

import android.content.Intent;

import androidx.lifecycle.ViewModel;

import com.pixels.Inventario.Model.Basededatos.SQLite.DatosInicio.consultasDatos;
import com.pixels.Inventario.Model.Factura.FacturaPDF;
import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Activity.Caja.Caja;
import com.pixels.Inventario.View.Activity.Caja.Factura.VerFactura;

public class RealizarFacturaViewModel extends ViewModel {
    public FacturaPDF factura;
    public RealizarFacturaViewModel() {
    }
    public void crearFactura(Caja context, String codigoV, String fechaV,int efectivo,String decision){
        consultasDatos bd=new consultasDatos(context);
        String nombre=bd.obtenerD().get(0).getNombre();
        String nit=bd.obtenerD().get(0).getNit();
        factura=new FacturaPDF(context,codigoV);
        factura.AbrirFactura();
        factura.addDatos("Factura_"+codigoV,"VentaRealizada",""+context.getString(R.string.app_name));
        factura.addTitulos(""+nombre+"      NIT: "+nit,"Codigo de la Factura: "+codigoV,"Fecha: "+fechaV);
        //factura.addTablaP();
        factura.CerrarFactura();
        Intent intent=new Intent(context, VerFactura.class);
        intent.putExtra("path",factura.getFactura().getAbsolutePath());
        intent.putExtra("decision",decision);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
