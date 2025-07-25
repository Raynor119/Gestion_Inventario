package com.pixels.Inventario.ViewModel.Caja.Venta;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModel;

import com.pixels.Inventario.Model.Basededatos.SQLite.DatosInicio.consultasDatos;
import com.pixels.Inventario.Model.DatosE.Producto;
import com.pixels.Inventario.Model.Factura.FacturaPDF;
import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Caja.Caja;
import com.pixels.Inventario.View.Caja.Factura.VerFactura;
import com.pixels.Inventario.View.Gestion_Ventas.DetallesVentas.DetallesVentas;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class RealizarFacturaViewModel extends ViewModel {
    public FacturaPDF factura;
    public int code=200;
    public RealizarFacturaViewModel() {
    }
    public void crearFactura(Caja context, String codigoV, String fechaV,int efectivo,String decision){
        List<Producto> Productos =context.Productos;
        context.Productos=new ArrayList<>();
        context.iniciarRecyclerView();
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
            factura=new FacturaPDF(context,codigoV,fechaV,bd.obtenerD().get(0).getBasedatos());
            factura.AbrirFactura();
            factura.addDatos("Factura_"+codigoV,"VentaRealizada",""+context.getString(R.string.app_name));
            factura.addTitulos(""+nombre+"      NIT: "+nit,"Codigo de la Factura: "+codigoV,"Fecha: "+fechaV);
            factura.addSubTitulos("PRODUCTOS VENDIDOS");
            factura.addTablaP(Productos);
            int Subtotal=0,iva=0,cambio=0;
            int Total=0;
            for(int i=0;i<Productos.size();i++){
                Total=(int)(Total+(Productos.get(i).getPrecio()*Productos.get(i).getCantidad()));
                double porcentajeiva=1.0+(Productos.get(i).getIva()*0.01);
                BigDecimal precionS = new BigDecimal((Productos.get(i).getPrecio()*Productos.get(i).getCantidad())/porcentajeiva).setScale(0, RoundingMode.HALF_UP);
                int suptotal=(int)(precionS.doubleValue());
                Subtotal=Subtotal+suptotal;
                double poriva=(Productos.get(i).getIva()*0.01);
                BigDecimal precion = new BigDecimal(suptotal*poriva).setScale(0, RoundingMode.HALF_UP);
                double ivaa=precion.doubleValue();
                iva=iva+((int) ivaa);
            }

            cambio=efectivo-Total;
            NumberFormat formato= NumberFormat.getNumberInstance();
            String texto="IVA $                            "+formato.format(iva)+"\n"+
                         "SUBTOTAL $                       "+formato.format(Subtotal)+"\n"+
                         "TOTAL $                          "+formato.format(Total)+"\n"+
                         "EFECTIVO $                       "+formato.format(efectivo)+"\n"+
                         "CAMBIO $                         "+formato.format(cambio);
            factura.addParagraphR(texto);
            factura.addSubTitulos("RESUMEN DE IMPUESTOS");
            factura.addTablaPIVA(Productos);
            factura.addCodigoBarras();
            factura.CerrarFactura();
            Intent intent=new Intent(context, VerFactura.class);
            intent.putExtra("path",factura.getFactura().getAbsolutePath());
            intent.putExtra("decision",decision);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
    public void crearFacturaVer(DetallesVentas context, List<Producto> Productos, String codigoV, String fechaV, int efectivo, String decision){
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
            factura=new FacturaPDF(context,codigoV,fechaV,bd.obtenerD().get(0).getBasedatos());
            factura.AbrirFactura();
            factura.addDatos("Factura_"+codigoV,"VentaRealizada",""+context.getString(R.string.app_name));
            factura.addTitulos(""+nombre+"      NIT: "+nit,"Codigo de la Factura: "+codigoV,"Fecha: "+fechaV);
            factura.addSubTitulos("PRODUCTOS VENDIDOS");
            factura.addTablaP(Productos);
            int Subtotal=0,iva=0,cambio=0;
            int Total=0;
            for(int i=0;i<Productos.size();i++){
                Total=(int)(Total+(Productos.get(i).getPrecio()*Productos.get(i).getCantidad()));
                double porcentajeiva=1.0+(Productos.get(i).getIva()*0.01);
                BigDecimal precionS = new BigDecimal((Productos.get(i).getPrecio()*Productos.get(i).getCantidad())/porcentajeiva).setScale(0, RoundingMode.HALF_UP);
                int suptotal=(int)(precionS.doubleValue());
                Subtotal=Subtotal+suptotal;
                double poriva=(Productos.get(i).getIva()*0.01);
                BigDecimal precion = new BigDecimal(suptotal*poriva).setScale(0, RoundingMode.HALF_UP);
                double ivaa=precion.doubleValue();
                iva=iva+((int) ivaa);
            }

            cambio=efectivo-Total;
            NumberFormat formato= NumberFormat.getNumberInstance();
            String texto="IVA $                            "+formato.format(iva)+"\n"+
                    "SUBTOTAL $                       "+formato.format(Subtotal)+"\n"+
                    "TOTAL $                          "+formato.format(Total)+"\n"+
                    "EFECTIVO $                       "+formato.format(efectivo)+"\n"+
                    "CAMBIO $                         "+formato.format(cambio);
            factura.addParagraphR(texto);
            factura.addSubTitulos("RESUMEN DE IMPUESTOS");
            factura.addTablaPIVA(Productos);
            factura.addCodigoBarras();
            factura.CerrarFactura();
            Intent intent=new Intent(context, VerFactura.class);
            intent.putExtra("path",factura.getFactura().getAbsolutePath());
            intent.putExtra("decision",decision);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

}
