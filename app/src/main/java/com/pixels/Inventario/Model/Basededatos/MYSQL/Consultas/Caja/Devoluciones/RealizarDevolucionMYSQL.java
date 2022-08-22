package com.pixels.Inventario.Model.Basededatos.MYSQL.Consultas.Caja.Devoluciones;



import android.widget.Toast;

import com.pixels.Inventario.Model.Basededatos.MYSQL.Conexion;
import com.pixels.Inventario.Model.Basededatos.MediadorBaseDatos;

import com.pixels.Inventario.Model.DatosE.VentaRealizada;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import com.pixels.Inventario.View.Activity.Caja.Devoluciones.devoluciones;
import com.pixels.Inventario.ViewModel.Caja.Devoluciones.RealizarDevolucionViewModel;

public class RealizarDevolucionMYSQL extends Conexion implements MediadorBaseDatos {
    private devoluciones Context;
    private RealizarDevolucionViewModel ViewModel;
    private String CodigoV;
    private boolean Verificar=true;

    public RealizarDevolucionMYSQL(devoluciones context,RealizarDevolucionViewModel viewModel,String codigoV) {
        super(context);
        this.Context=context;
        this.ViewModel=viewModel;
        this.CodigoV=codigoV;
        execute("");
    }
    @Override
    protected String doInBackground(String... params) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection= DriverManager.getConnection(Url,Usuario,Contra);
            Statement st = connection.createStatement();
            for(int i=0;i<Context.Productos.size();i++){
                st.executeUpdate("UPDATE VentasProductos SET EstadoDevolucion='si', ObservacionD='"+Context.Productos.get(i).getObservacionD()+"' WHERE codigoV="+CodigoV+" AND Id="+Context.Productos.get(i).getId()+"");
            }
            return "";
        }catch (Exception e){
            return "No se puede conectar a La Base Datos";
        }
    }
    @Override
    protected void onPostExecute(String result) {
        if(result.equals("")){
            ConsultaBaseDatos();
        }else {
            Toast.makeText(Context, result, Toast.LENGTH_LONG).show();
            Verificar=false;
            ConsultaBaseDatos();
        }
    }

    @Override
    public void ConsultaBaseDatos() {
        if(Verificar){
            ViewModel.resultado.setValue(true);
        }else{
            ViewModel.resultado.setValue(false);
        }
    }
}
