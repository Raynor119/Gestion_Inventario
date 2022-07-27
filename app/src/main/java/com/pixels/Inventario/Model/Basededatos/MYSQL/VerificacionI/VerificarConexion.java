package com.pixels.Inventario.Model.Basededatos.MYSQL.VerificacionI;

import android.os.AsyncTask;

import com.pixels.Inventario.ViewModel.InicioA.ConfiguracionInicial.VerificarConexionViewModel;

import java.sql.Connection;
import java.sql.DriverManager;

public class VerificarConexion extends  AsyncTask<String, Void , Boolean> {

    private String Url,Usuario,Contra;
    private VerificarConexionViewModel Context;

    public VerificarConexion(String Ip,String NBasedatos,String usuario,String contra,VerificarConexionViewModel context){
        this.Url="jdbc:mysql://"+Ip+"/"+NBasedatos;
        this.Usuario=usuario;
        this.Contra=contra;
        this.Context=context;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
    @Override
    protected Boolean doInBackground(String... params) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection= DriverManager.getConnection(Url,Usuario,Contra);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    @Override
    protected void onPostExecute(Boolean result) {
        Context.resultado.setValue(result);
    }






}
