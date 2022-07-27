package com.pixels.Inventario.ViewModel.Ajustes.ExportarDatos;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import com.pixels.Inventario.Model.Basededatos.SQLite.BaseDatos.ExportarDatos.ExportarDatosSQLite;

public class ExportarDatosViewModel extends ViewModel {
    public ExportarDatosViewModel(){

    }
    public void exportardatos(String Ip, String NBasedatos, String usuario, String contra, Context context){
        ExportarDatosSQLite exportar=new ExportarDatosSQLite(Ip,NBasedatos,usuario,contra,context);
        exportar.execute("");
    }
}
