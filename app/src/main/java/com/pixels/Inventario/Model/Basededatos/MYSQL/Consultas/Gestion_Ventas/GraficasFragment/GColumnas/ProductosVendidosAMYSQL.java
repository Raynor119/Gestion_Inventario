package com.pixels.Inventario.Model.Basededatos.MYSQL.Consultas.Gestion_Ventas.GraficasFragment.GColumnas;

import android.content.Context;
import android.widget.Toast;

import com.pixels.Inventario.Model.Basededatos.MYSQL.Conexion;
import com.pixels.Inventario.Model.Basededatos.MediadorBaseDatos;
import com.pixels.Inventario.Model.DatosE.DatosColumn;
import com.pixels.Inventario.ViewModel.Gestion_Ventas.GraficasFragment.GColumnas.GraficaColumnARecyclerViewModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductosVendidosAMYSQL extends Conexion implements MediadorBaseDatos {
    public GraficaColumnARecyclerViewModel ViewModel;
    public List<DatosColumn> ProductosV;
    private boolean verificarE=false;
    private String Consulta="";
    public ProductosVendidosAMYSQL(android.content.Context context, GraficaColumnARecyclerViewModel viewModel, String consulta) {
        super(context);
        this.ViewModel=viewModel;
        this.Consulta=consulta;
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
        },31000);
    }
    @Override
    protected String doInBackground(String... params) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            DriverManager.setLoginTimeout(30);
            Connection connection= DriverManager.getConnection(Url,Usuario,Contra);
            if(verificarE){
                return "Error en la conexion";
            }else{
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(Consulta);
                ProductosV=new ArrayList<>();
                while (rs.next()) {
                    ProductosV.add(new DatosColumn(rs.getString(1),rs.getString(2),rs.getInt(3)));
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
        }
    }
    @Override
    public void ConsultaBaseDatos() {
        ViewModel.resultado.setValue(ProductosV);
    }
}
