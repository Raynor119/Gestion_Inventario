package com.pixels.Inventario.Model.Basededatos.MYSQL.Consultas.Gestion_Productos;

import android.content.Context;
import android.widget.Toast;

import com.pixels.Inventario.Model.Basededatos.MYSQL.Conexion;
import com.pixels.Inventario.Model.Basededatos.MediadorBaseDatos;
import com.pixels.Inventario.Model.DatosE.Producto;
import com.pixels.Inventario.ViewModel.Gestion_Productos.AgregarProductos.AgregarProductosViewModel;
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
    public EliminarProductosMYSQL(Context context,String codigo,EliminarProductoViewModel viewModel) {
        super(context);
        this.Codigo=codigo;
        this.verficar=true;
        this.ViewModel=viewModel;
        execute("");
    }
    @Override
    protected String doInBackground(String... params) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection= DriverManager.getConnection(Url,Usuario,Contra);
            Statement st = connection.createStatement();
            st.executeUpdate("UPDATE VentasProductos SET codigoP=NULL WHERE codigoP='"+Codigo+"'");
            connection.close();
            connection= DriverManager.getConnection(Url,Usuario,Contra);
            st = connection.createStatement();
            st.executeUpdate("DELETE FROM Producto WHERE codigo='"+Codigo+"'");
            connection.close();
            return "";
        }catch (Exception e){
            return "Error al Eliminar el producto: "+e;
        }
    }
    @Override
    protected void onPostExecute(String result) {
        if(result.equals("")){
            EliminarProducto(ViewModel);
        }else {
            verficar=false;
            EliminarProducto(ViewModel);
            Toast.makeText(Context, result, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void ObtenerProductos(ProductosRecyclerViewModel viewModel, List<Producto> productos) {
    }
    @Override
    public void verificarCodigoProducto(VerificarCodigoViewModel viewModel, List<Producto> productos, String codigo) {
    }
    @Override
    public void agregarproductobasedatos(AgregarProductosViewModel viewModel) {
    }

    @Override
    public void EliminarProducto(EliminarProductoViewModel viewModel) {
        viewModel.resultado.setValue(verficar);
    }
}
