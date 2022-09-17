package com.pixels.Inventario.Model.Basededatos.MYSQL.Consultas.Gestion_Ventas.DetallesVentas;

import android.content.Context;
import android.widget.Toast;

import com.pixels.Inventario.Model.Basededatos.MYSQL.Conexion;
import com.pixels.Inventario.Model.Basededatos.MediadorBaseDatos;
import com.pixels.Inventario.Model.DatosE.TotalVentas;
import com.pixels.Inventario.Model.DatosE.detallesPV;
import com.pixels.Inventario.ViewModel.Gestion_Ventas.DetallesVentas.DetallesVentasViewModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VerDetallesVentasMYSQL extends Conexion implements MediadorBaseDatos {
    private DetallesVentasViewModel ViewModel;
    public List<detallesPV> ProductosVendidos;
    private boolean verificarE=false;
    private String Codigo;
    public VerDetallesVentasMYSQL(Context context,DetallesVentasViewModel viewModel,String codigo) {
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
                ResultSet rs = st.executeQuery("SELECT VentasProductos.Id,VentasProductos.codigoV,VentasProductos.codigoP,Producto.nombre,Producto.TipoC,VentasProductos.CantidadV,VentasProductos.CantidadD,VentasProductos.CostePV,VentasProductos.PrecioPV,VentasProductos.IvaPV,VentasProductos.ObservacionD FROM VentasProductos INNER JOIN Producto on VentasProductos.codigoP=Producto.codigo WHERE VentasProductos.codigoV="+Codigo+"");
                ProductosVendidos=new ArrayList<>();
                while (rs.next()) {
                    ProductosVendidos.add(new detallesPV(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDouble(6),rs.getDouble(7),rs.getInt(8),rs.getInt(9),rs.getInt(10),rs.getString(11)));
                }
                rs = st.executeQuery("SELECT * FROM VentasProductos WHERE codigoV="+Codigo+" AND codigoP IS NULL");
                while (rs.next()) {
                    ProductosVendidos.add(new detallesPV(rs.getInt(1),rs.getInt(2),"null","Producto Desconocido","null",rs.getDouble(4),rs.getDouble(5),rs.getInt(6),rs.getInt(7),rs.getInt(8),rs.getString(9)));
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
        ViewModel.resultado.setValue(ProductosVendidos);
    }
}
