package com.pixels.Inventario.ViewModel.InicioA.ConfiguracionInicial;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.pixels.Inventario.Model.Basededatos.MYSQL.VerificacionI.VerificarConexion;

public class VerificarConexionViewModel extends ViewModel {
    public MutableLiveData<Boolean> resultado;
    private VerificarConexion bdMysql;

    public VerificarConexionViewModel(){
        this.resultado=new MutableLiveData<>();
    }
    public void reset(){
        this.resultado=new MutableLiveData<>();
    }
    public void TerminarProceso(){
        bdMysql.cancel(true);
    }
    public LiveData<Boolean> getResultado(){
        return resultado;
    }
    public void VerificarConexion(String Ip,String NBasedatos,String usuario,String contra){
       bdMysql = new VerificarConexion(Ip,NBasedatos,usuario,contra,VerificarConexionViewModel.this);
       bdMysql.execute("");
    }
}
