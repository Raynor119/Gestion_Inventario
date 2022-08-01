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
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VerDatosProductoMYSQL extends Conexion implements MediadorBaseDatos {
    private VerDatosProductoViewModel ViewModel;
    private List<Producto> productos;
    private String Codigo;
    public VerDatosProductoMYSQL(Context context, VerDatosProductoViewModel viewModel,String codigo) {
        super(context);
        this.ViewModel=viewModel;
        this.Codigo=codigo;
        execute("");
    }
    @Override
    protected String doInBackground(String... params) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection= DriverManager.getConnection(Url,Usuario,Contra);
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Producto WHERE codigo='"+Codigo+"'");
            productos=new ArrayList<>();
            while (rs.next()) {
                productos.add(new Producto(rs.getString(1),rs.getString(2),rs.getDouble(3),rs.getString(4),rs.getInt(5),rs.getInt(6)));
            }
            return "";
        }catch (Exception e){
            return "No se puede conectar a La Base Datos";
        }
    }
    @Override
    protected void onPostExecute(String result) {
        if(result.equals("")){
            VerDatosProductoCodigo(ViewModel,productos);
        }else {
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
    }

    @Override
    public void VerDatosProductoCodigo(VerDatosProductoViewModel viewModel, List<Producto> producto) {
        viewModel.resultado.setValue(producto);
    }


}
