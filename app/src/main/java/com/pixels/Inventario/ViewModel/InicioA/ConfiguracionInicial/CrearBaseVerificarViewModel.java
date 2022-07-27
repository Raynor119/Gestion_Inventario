package com.pixels.Inventario.ViewModel.InicioA.ConfiguracionInicial;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.pixels.Inventario.Model.Basededatos.MYSQL.VerificacionI.VerificarCreacionBaseDatos;

public class CrearBaseVerificarViewModel extends ViewModel {
    public MutableLiveData<Boolean> resultado;
    private VerificarCreacionBaseDatos bdMysql;

    public CrearBaseVerificarViewModel(){
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
        bdMysql = new VerificarCreacionBaseDatos(Ip,NBasedatos,usuario,contra,CrearBaseVerificarViewModel.this);
        bdMysql.execute("");
    }
}
