package com.pixels.Inventario.Model.Basededatos.MYSQL;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import com.pixels.Inventario.Model.Basededatos.SQLite.DatosInicio.consultasDatos;
import com.pixels.Inventario.Model.DatosE.datosI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class Conexion extends AsyncTask<String, Void , String> {
    private String Url,Usuario,Contra;
    private List<datosI> datosIS;
    public Context Context;

    public Conexion(Context context){
        consultasDatos datosI=new consultasDatos(context);
        this.datosIS=datosI.obtenerD();
        this.Url="jdbc:mysql://"+datosIS.get(0).getIp()+":3306/"+datosIS.get(0).getNbasedatos();
        this.Usuario=datosIS.get(0).getUsuario();
        this.Contra=datosIS.get(0).getUcontra();
        this.Context=context;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
    @Override
    protected String doInBackground(String... params) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection= DriverManager.getConnection(Url,Usuario,Contra);
            return "Hay Conexion en la Base de Datos";
        }catch (Exception e){
            return "No se puede conectar a La Base Datos";
        }
    }
    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(Context, result, Toast.LENGTH_LONG).show();

    }
}
