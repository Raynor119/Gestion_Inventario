package com.pixels.Inventario.ViewModel.Ajustes.ImportarDatos;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import com.pixels.Inventario.Model.Basededatos.SQLite.BaseDatos.ImportarDatos.ImportarDatosSQLite;

public class ImportarDatosViewModel extends ViewModel {
    public ImportarDatosViewModel(){

    }
    public void importarDatos(String Ip, String NBasedatos, String usuario, String contra, Context context){
        ImportarDatosSQLite importacion=new ImportarDatosSQLite(Ip,NBasedatos,usuario,contra,context);
        importacion.execute("");
    }
}
