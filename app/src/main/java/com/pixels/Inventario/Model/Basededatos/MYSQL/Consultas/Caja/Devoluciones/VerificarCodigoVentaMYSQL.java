package com.pixels.Inventario.Model.Basededatos.MYSQL.Consultas.Caja.Devoluciones;

import android.content.Context;
import android.widget.Toast;

import com.pixels.Inventario.Model.Basededatos.MYSQL.Conexion;
import com.pixels.Inventario.Model.Basededatos.MediadorBaseDatos;
import com.pixels.Inventario.Model.DatosE.Producto;
import com.pixels.Inventario.Model.DatosE.VentasProductoD;
import com.pixels.Inventario.ViewModel.Caja.Devoluciones.VerificarCodigoV.VerificarCodigoVentaViewModel;
import com.pixels.Inventario.ViewModel.Caja.VerificarCodigo.VerificarCodigoCajaViewModel;
import com.pixels.Inventario.ViewModel.Gestion_Productos.VerificarCodigo.VerificarCodigoViewModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VerificarCodigoVentaMYSQL extends Conexion implements MediadorBaseDatos {
    public List<VentasProductoD> productos;
    private VerificarCodigoVentaViewModel ViewModel;
    public String Codigo;
    public VerificarCodigoVentaMYSQL(Context context, VerificarCodigoVentaViewModel viewModel, String codigo) {
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
            ResultSet rs = st.executeQuery("SELECT VentasProductos.Id,VentasProductos.codigoV,VentasProductos.codigoP,Producto.nombre,VentasProductos.CantidadV,VentasProductos.CostePV,VentasProductos.PrecioPV,VentasProductos.IvaPV,VentasProductos.EstadoDevolucion,VentasProductos.ObservacionD FROM VentasProductos INNER JOIN Producto ON VentasProductos.codigoP=Producto.codigo WHERE VentasProductos.codigoV="+Codigo+"");
            productos=new ArrayList<>();
            while (rs.next()) {
                productos.add(new VentasProductoD(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getDouble(5),rs.getInt(6),rs.getInt(7),rs.getInt(8),rs.getString(9),rs.getString(10)));
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
        }
    }
    @Override
    public void ConsultaBaseDatos() {
        boolean verificar=false;
        List<VentasProductoD> productosEnviar=new ArrayList<>();
        for(int i=0;i<productos.size();i++){
            if((productos.get(i).getCodigoV()+"").equals(Codigo)){
                verificar=true;
                productosEnviar.add(new VentasProductoD(productos.get(i).getId(),productos.get(i).getCodigoV(),productos.get(i).getCodigoP(),productos.get(i).getNombre(),productos.get(i).getCantidadV(),productos.get(i).getCostePV(),productos.get(i).getPrecioPV(),productos.get(i).getIva(),productos.get(i).getEstadoDevolucion(),productos.get(i).getObservacionD()));
            }
        }
        ViewModel.resultado.setValue(verificar);
        if(verificar){
            ViewModel.productos.setValue(productosEnviar);
        }
    }
}