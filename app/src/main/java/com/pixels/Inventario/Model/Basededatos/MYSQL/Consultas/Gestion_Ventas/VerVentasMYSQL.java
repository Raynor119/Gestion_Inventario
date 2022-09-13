package com.pixels.Inventario.Model.Basededatos.MYSQL.Consultas.Gestion_Ventas;

import android.content.Context;
import android.widget.Toast;

import com.pixels.Inventario.Model.Basededatos.MYSQL.Conexion;
import com.pixels.Inventario.Model.Basededatos.MediadorBaseDatos;
import com.pixels.Inventario.Model.DatosE.Producto;
import com.pixels.Inventario.Model.DatosE.TotalVentas;
import com.pixels.Inventario.ViewModel.Gestion_Productos.ProductosRecyclerViewModel;
import com.pixels.Inventario.ViewModel.Gestion_Ventas.VentasRecyclerViewModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VerVentasMYSQL extends Conexion implements MediadorBaseDatos {
    public VentasRecyclerViewModel ViewModel;
    public List<TotalVentas> ventas;
    private boolean verificarE=false;
    private String Consulta="";
    public VerVentasMYSQL(Context context, VentasRecyclerViewModel viewModel,String consulta) {
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
                ResultSet rs = st.executeQuery(Consulta);
                ventas=new ArrayList<>();
                while (rs.next()) {
                    ventas.add(new TotalVentas(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getDouble(4),rs.getDouble(5),rs.getInt(6),rs.getInt(7),rs.getInt(8),rs.getString(9)));
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
        ViewModel.resultado.setValue(ventas);
    }
}
