package com.pixels.Inventario.ViewModel.InicioA.ConfiguracionInicial;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import com.pixels.Inventario.Model.Basededatos.SQLite.DatosInicio.consultasDatos;

public class datosInicioViewModel extends ViewModel {
    private consultasDatos datosInicio;
    public datosInicioViewModel(){}
    public void mandarDatos(Context context,String contra,String contrasena,String basedatos,String ip,String nbasedatos,String usuario,String ucontra,String nit,String nombre){
        datosInicio= new consultasDatos(context);
        datosInicio.Imodificar("1",contra,contrasena,basedatos,ip,nbasedatos,usuario,ucontra,nit,nombre);
    }
}
