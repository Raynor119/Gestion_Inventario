package com.pixels.Inventario.Model.Basededatos.MYSQL.Consultas.Caja.Devoluciones;



import android.widget.Toast;

import com.pixels.Inventario.Model.Basededatos.MYSQL.Conexion;
import com.pixels.Inventario.Model.Basededatos.MediadorBaseDatos;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


import com.pixels.Inventario.View.Caja.Devoluciones.devoluciones;
import com.pixels.Inventario.ViewModel.Caja.Devoluciones.RealizarDevolucionViewModel;

public class RealizarDevolucionMYSQL extends Conexion implements MediadorBaseDatos {
    private devoluciones Context;
    private RealizarDevolucionViewModel ViewModel;
    private String CodigoV;
    private boolean Verificar=true;
    private boolean verificarE=false;
    public RealizarDevolucionMYSQL(devoluciones context,RealizarDevolucionViewModel viewModel,String codigoV) {
        super(context);
        this.Context=context;
        this.ViewModel=viewModel;
        this.CodigoV=codigoV;
        execute("");
        new android.os.Handler().postDelayed(new Runnable() {
            public void run() {
                if(verificarE){
                    onCancelled();
                }else{
                    onCancelled("No se puede conectar a La Base Datos");
                    verificarE=true;
                }
            }
        },11000);
    }
    @Override
    protected String doInBackground(String... params) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            DriverManager.setLoginTimeout(10);
            Connection connection= DriverManager.getConnection(Url,Usuario,Contra);
            if(verificarE){
                return "Error en la conexion";
            }else{
                Statement st = connection.createStatement();
                for(int i=0;i<Context.Productos.size();i++){
                    st.executeUpdate("UPDATE VentasProductos SET CantidadD="+Context.Productos.get(i).getCantidadD()+",EstadoDevolucion='si', ObservacionD='"+Context.Productos.get(i).getObservacionD()+"' WHERE codigoV="+CodigoV+" AND Id="+Context.Productos.get(i).getId()+"");
                }
                return "";
            }
        }catch (Exception e){
            return "No se puede conectar a La Base Datos";
        }
    }
    @Override
    protected void onPostExecute(String result) {
        verificarE=true;
        if(result.equals("")){
            ConsultaBaseDatos();
        }else {
            if(result.equals("Error en la conexion")){

            }else{
                Toast.makeText(Context, result, Toast.LENGTH_LONG).show();
            }
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
