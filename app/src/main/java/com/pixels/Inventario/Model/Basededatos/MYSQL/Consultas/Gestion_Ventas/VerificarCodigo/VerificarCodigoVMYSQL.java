package com.pixels.Inventario.Model.Basededatos.MYSQL.Consultas.Gestion_Ventas.VerificarCodigo;

import android.content.Context;
import android.widget.Toast;

import com.pixels.Inventario.Model.Basededatos.MYSQL.Conexion;
import com.pixels.Inventario.Model.Basededatos.MediadorBaseDatos;
import com.pixels.Inventario.Model.DatosE.DatosVenta;
import com.pixels.Inventario.Model.DatosE.Producto;
import com.pixels.Inventario.ViewModel.Gestion_Productos.VerificarCodigo.VerificarCodigoViewModel;
import com.pixels.Inventario.ViewModel.Gestion_Ventas.VerificarCodigo.VerificarCodigoFVViewModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VerificarCodigoVMYSQL extends Conexion implements MediadorBaseDatos {
    public List<DatosVenta> ventas;
    private VerificarCodigoFVViewModel ViewModel;
    public String Codigo;
    private boolean verificarE=false;
    public VerificarCodigoVMYSQL(Context context, VerificarCodigoFVViewModel viewModel, String codigo) {
        super(context);
        this.ViewModel=viewModel;
        this.Codigo=codigo;
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
                ResultSet rs = st.executeQuery("SELECT * FROM Venta WHERE codigo="+Codigo+"");
                ventas=new ArrayList<>();
                while (rs.next()) {
                    ventas.add(new DatosVenta(true,rs.getInt(1),rs.getString(2),rs.getInt(3)));
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
        if(ventas.size()==0){
            ventas.add(new DatosVenta(false,0,"",0));
        }
        ViewModel.resultado.setValue(ventas);
    }
}
