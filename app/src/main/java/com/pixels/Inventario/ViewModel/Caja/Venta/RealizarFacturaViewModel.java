package com.pixels.Inventario.ViewModel.Caja.Venta;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModel;

import com.pixels.Inventario.Model.Basededatos.SQLite.DatosInicio.consultasDatos;
import com.pixels.Inventario.Model.Factura.FacturaPDF;
import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Activity.Caja.Caja;
import com.pixels.Inventario.View.Activity.Caja.Factura.VerFactura;

public class RealizarFacturaViewModel extends ViewModel {
    public FacturaPDF factura;
    public int code=200;
    public RealizarFacturaViewModel() {
    }
    public void crearFactura(Caja context, String codigoV, String fechaV,int efectivo,String decision){
        consultasDatos bd=new consultasDatos(context);
        String nombre=bd.obtenerD().get(0).getNombre();
        String nit=bd.obtenerD().get(0).getNit();
        int permisos= ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        boolean verificar=false;
        if(permisos== PackageManager.PERMISSION_GRANTED){
            verificar=true;
        }else{
            Toast.makeText(context, "Error al generar la factura conceda los permosos de almacenamiento", Toast.LENGTH_LONG).show();
            context.requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},code);
        }
        if(verificar){
            factura=new FacturaPDF(context,codigoV);
            factura.AbrirFactura();
            factura.addDatos("Factura_"+codigoV,"VentaRealizada",""+context.getString(R.string.app_name));
            factura.addTitulos(""+nombre+"      NIT: "+nit,"Codigo de la Factura: "+codigoV,"Fecha: "+fechaV);
            factura.addSubTitulos("PRODUCTOS VENDIDOS");
            factura.addTablaP();
            for(int i=0;i<context.Productos.size();i++){

            }
            factura.addParagraph("IVA $                "+
                                     "");
            factura.addSubTitulos("RESUMEN DE IMPUESTOS");
            factura.CerrarFactura();
            Intent intent=new Intent(context, VerFactura.class);
            intent.putExtra("path",factura.getFactura().getAbsolutePath());
            intent.putExtra("decision",decision);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

}
