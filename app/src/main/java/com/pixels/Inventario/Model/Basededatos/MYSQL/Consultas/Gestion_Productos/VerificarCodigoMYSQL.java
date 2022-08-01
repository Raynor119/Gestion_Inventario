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
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VerificarCodigoMYSQL extends Conexion implements MediadorBaseDatos {
    public List<Producto> productos;
    private VerificarCodigoViewModel ViewModel;
    public String Codigo;
    public VerificarCodigoMYSQL(Context context,VerificarCodigoViewModel viewModel,String codigo) {
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
            ResultSet rs = st.executeQuery("SELECT * FROM Producto");
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
            verificarCodigoProducto(ViewModel,productos,Codigo);
        }else {
            Toast.makeText(Context, result, Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void ObtenerProductos(ProductosRecyclerViewModel viewModel, List<Producto> productos) {
    }
    @Override
    public void verificarCodigoProducto(VerificarCodigoViewModel viewModel, List<Producto> productos, String codigo) {
        boolean verificar=true;
        for(int i=0;i<productos.size();i++){
            if(productos.get(i).getCodigo().equals(codigo)){
                verificar=false;
            }
        }
        viewModel.resultado.setValue(verificar);
    }
    @Override
    public void agregarproductobasedatos(AgregarProductosViewModel viewModel) {
    }

    @Override
    public void EliminarProducto(EliminarProductoViewModel viewModel) {
    }
}
