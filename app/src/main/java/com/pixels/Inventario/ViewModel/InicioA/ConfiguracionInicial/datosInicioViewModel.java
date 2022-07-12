package com.pixels.Inventario.ViewModel.InicioA.ConfiguracionInicial;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import com.pixels.Inventario.Model.Basededatos.SQLite.DatosInicio;

public class datosInicioViewModel extends ViewModel {
    private DatosInicio datosInicio;
    public datosInicioViewModel(){}
    public void mandarDatos(Context context,String contra,String contrasena,String basedatos,String ip,String nbasedatos,String usuario,String ucontra){
        datosInicio= new DatosInicio(context);
        datosInicio.Imodificar("1",contra,contrasena,basedatos,ip,nbasedatos,usuario,ucontra);
    }
}
