package com.pixels.Inventario.Model.Basededatos.SQLite.BaseDatos.Consultas.Caja;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.pixels.Inventario.Model.Basededatos.MediadorBaseDatos;
import com.pixels.Inventario.Model.Basededatos.SQLite.BaseDatos.BaseDatosSQLite;
import com.pixels.Inventario.Model.DatosE.VentaRealizada;
import com.pixels.Inventario.View.Caja.Caja;
import com.pixels.Inventario.ViewModel.Caja.Venta.RealizarVentaViewModel;

import java.util.ArrayList;
import java.util.List;

public class RealizarVentaSQLite extends BaseDatosSQLite implements MediadorBaseDatos {

    private Caja Context;
    private int Efectivo;
    private RealizarVentaViewModel ViewModel;
    private String CodigoV="";
    private String FechaV="";

    public RealizarVentaSQLite(Caja context, int efectivo, RealizarVentaViewModel viewModel) {
        super(context);
        this.Context=context;
        this.Efectivo=efectivo;
        this.ViewModel=viewModel;
        VentaSQlite();
    }
    public void VentaSQlite(){
        SQLiteDatabase bd=getWritableDatabase();
        if(bd!=null)
        {
            try{
                bd.execSQL("INSERT INTO Venta (Fecha,Efectivo) VALUES(datetime('now','localtime'),"+Efectivo+")");
                bd=getReadableDatabase();
                Cursor cursor=bd.rawQuery("SELECT * FROM Venta ORDER by codigo DESC LIMIT 1",null);
                if(cursor.moveToFirst()){
                    do{
                        CodigoV=cursor.getString(0);
                        FechaV=cursor.getString(1);
                    }while(cursor.moveToNext());
                }
                for(int i=0;i< Context.Productos.size();i++){
                    bd=getWritableDatabase();
                    bd.execSQL("INSERT INTO VentasProductos (codigoV,codigoP,CantidadV,CostePV,PrecioPV,IvaPV,EstadoDevolucion,ObservacionD) VALUES("+CodigoV+",'"+Context.Productos.get(i).getCodigo()+"',"+Context.Productos.get(i).getCantidad()+","+Context.Productos.get(i).getCosteP()+","+Context.Productos.get(i).getPrecio()+","+Context.Productos.get(i).getIva()+",'no',NULL)");
                    bd=getReadableDatabase();
                    cursor=bd.rawQuery("SELECT cantidad FROM Producto WHERE codigo='"+Context.Productos.get(i).getCodigo()+"'",null);
                    double cantidaP=0;
                    if(cursor.moveToFirst()){
                        do{
                            cantidaP=cursor.getDouble(0);
                        }while(cursor.moveToNext());
                    }
                    double cantidadDiferente=0;
                    if((cantidaP-Context.Productos.get(i).getCantidad())<0){
                        cantidadDiferente=Context.Productos.get(i).getCantidad()+(cantidaP-Context.Productos.get(i).getCantidad());
                    }else{
                        cantidadDiferente=Context.Productos.get(i).getCantidad();
                    }
                    bd=getWritableDatabase();
                    bd.execSQL("UPDATE Producto SET cantidad=cantidad-"+cantidadDiferente+" WHERE codigo='"+Context.Productos.get(i).getCodigo()+"' AND cantidad!=0");
                }
            }catch (Exception e){
                Toast.makeText(Context, "Error en La Base Datos", Toast.LENGTH_LONG).show();
                List<VentaRealizada> resultado=new ArrayList<>();
                resultado.add(new VentaRealizada(false,"",""));
                ViewModel.resultado.setValue(resultado);
            }
            bd.close();
        }
        ConsultaBaseDatos();
    }

    @Override
    public void ConsultaBaseDatos() {
        if(CodigoV.equals("") || FechaV.equals("")){
            List<VentaRealizada> resultado=new ArrayList<>();
            resultado.add(new VentaRealizada(false,"",""));
            ViewModel.resultado.setValue(resultado);
        }else{
            List<VentaRealizada> resultado=new ArrayList<>();
            resultado.add(new VentaRealizada(true,CodigoV,FechaV));
            ViewModel.resultado.setValue(resultado);
        }
    }
}
