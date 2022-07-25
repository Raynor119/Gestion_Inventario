package com.pixels.Inventario.ViewModel.Ajustes.EliminarBaseDatos;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import com.pixels.Inventario.Model.Basededatos.SQLite.BaseDatos.EliminarDatos.EliminarBDSQLite;


public class EliminarBDSQLiteViewModel extends ViewModel {
    public  EliminarBDSQLiteViewModel(){

    }
    public void EliminarBaseDatos(Context context){
        EliminarBDSQLite bd =new EliminarBDSQLite(context);
        bd.EliminarBasedatos();
    }
}
