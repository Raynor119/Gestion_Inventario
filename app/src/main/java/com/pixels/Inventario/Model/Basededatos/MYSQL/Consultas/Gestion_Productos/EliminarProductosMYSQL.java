package com.pixels.Inventario.Model.Basededatos.MYSQL.Consultas.Gestion_Productos;

import android.content.Context;
import android.widget.Toast;

import com.pixels.Inventario.Model.Basededatos.MYSQL.Conexion;
import com.pixels.Inventario.Model.Basededatos.MediadorBaseDatos;
import com.pixels.Inventario.Model.DatosE.Producto;
import com.pixels.Inventario.ViewModel.Gestion_Productos.AgregarProductos.AgregarProductosViewModel;
import com.pixels.Inventario.ViewModel.Gestion_Productos.EditarProducto.VerDatosProductoViewModel;
import com.pixels.Inventario.ViewModel.Gestion_Productos.EliminarProducto.EliminarProductoViewModel;
import com.pixels.Inventario.ViewModel.Gestion_Productos.ProductosRecyclerViewModel;
import com.pixels.Inventario.ViewModel.Gestion_Productos.VerificarCodigo.VerificarCodigoViewModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

public class EliminarProductosMYSQL  extends Conexion implements MediadorBaseDatos {
    private String Codigo;
    private boolean verficar;
    private EliminarProductoViewModel ViewModel;
    private boolean verificarE=false;
    public EliminarProductosMYSQL(Context context,String codigo,EliminarProductoViewModel viewModel) {
        super(context);
        this.Codigo=codigo;
        this.verficar=true;
        this.ViewModel=viewModel;
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
        },10000);
    }
    @Override
    protected String doInBackground(String... params) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection= DriverManager.getConnection(Url,Usuario,Contra);
            if(verificarE){
                return "Error en la conexion";
            }else{
                Statement st = connection.createStatement();
                st.executeUpdate("UPDATE VentasProductos SET codigoP=NULL WHERE codigoP='"+Codigo+"'");
                connection.close();
                connection= DriverManager.getConnection(Url,Usuario,Contra);
                st = connection.createStatement();
                st.executeUpdate("DELETE FROM Producto WHERE codigo='"+Codigo+"'");
                connection.close();
                return "";
            }
        }catch (Exception e){
            return "Error al Eliminar el producto: "+e;
        }
    }
    @Override
    protected void onPostExecute(String result) {
        verificarE=true;
        if(result.equals("")){
            ConsultaBaseDatos();
        }else {
            verficar=false;
            ConsultaBaseDatos();
            Toast.makeText(Context, result, Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void ConsultaBaseDatos() {
        ViewModel.resultado.setValue(verficar);
    }
}
