package com.pixels.Inventario.Model.Basededatos.SQLite.BaseDatos.ImportarDatos;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import com.pixels.Inventario.Model.DatosE.ImportDatos;
import com.pixels.Inventario.Model.DatosE.Producto;
import com.pixels.Inventario.Model.DatosE.Venta;
import com.pixels.Inventario.Model.DatosE.VentasProductos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ImportarDatosSQLite extends AsyncTask<String, Void , List<ImportDatos>> {
    private String Url,Usuario,Contra;
    private Context Context;
    public ImportarDatosSQLite(String ip, String nbasedatos, String usuario, String contra, Context context){
        this.Url="jdbc:mysql://"+ip+"/"+nbasedatos;
        this.Usuario=usuario;
        this.Contra=contra;
        this.Context=context;
    }
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<ImportDatos> doInBackground(String... params) {
        try {
            List<ImportDatos> resul= new ArrayList<>();
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection= DriverManager.getConnection(Url,Usuario,Contra);
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Producto");
            List<Producto> productos=new ArrayList<>();
            while (rs.next()) {
                productos.add(new Producto(rs.getString(1),rs.getString(2),rs.getDouble(3),rs.getString(4),rs.getInt(5),rs.getInt(6),rs.getInt(7)));
            }
            rs = st.executeQuery("SELECT * FROM Venta");
            List<Venta> ventas=new ArrayList<>();
            while (rs.next()) {
                ventas.add(new Venta(rs.getInt(1),rs.getString(2),rs.getInt(3)));
            }
            rs = st.executeQuery("SELECT * FROM VentasProductos");
            List<VentasProductos> ventasProductos=new ArrayList<>();
            while (rs.next()) {
                ventasProductos.add(new VentasProductos(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getDouble(4),rs.getInt(5),rs.getInt(6),rs.getInt(7),rs.getString(8),rs.getString(9)));
            }
            resul.add(new ImportDatos(productos,ventas,ventasProductos));
            return resul;
        }catch (Exception e){
            List<ImportDatos> resul=null;
            return resul;
        }
    }
    @Override
    protected void onPostExecute(List<ImportDatos> result) {
        ConexionSQLiteImportacionDatos conexion=new ConexionSQLiteImportacionDatos(Context);
        if(result != null){
            conexion.importarDatosProductos(result.get(0).getProductos());
            conexion.importarDatosVentas(result.get(0).getVentas());
            conexion.importarDatosVentasProductos(result.get(0).getVentasProductos());
        }else{
            Toast.makeText(Context, "Error al Importar los Datos", Toast.LENGTH_LONG).show();
        }
    }
}
