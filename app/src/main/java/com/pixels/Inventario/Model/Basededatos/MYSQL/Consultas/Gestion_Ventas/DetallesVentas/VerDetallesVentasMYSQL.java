package com.pixels.Inventario.Model.Basededatos.MYSQL.Consultas.Gestion_Ventas.DetallesVentas;

import android.content.Context;

import com.pixels.Inventario.Model.Basededatos.MYSQL.Conexion;
import com.pixels.Inventario.Model.Basededatos.MediadorBaseDatos;
import com.pixels.Inventario.Model.DatosE.TotalVentas;
import com.pixels.Inventario.Model.DatosE.detallesPV;
import com.pixels.Inventario.ViewModel.Gestion_Ventas.DetallesVentas.DetallesVentasViewModel;

import java.util.List;

public class VerDetallesVentasMYSQL extends Conexion implements MediadorBaseDatos {
    private DetallesVentasViewModel ViewModel;
    public List<detallesPV> ProductosVendidos;
    private boolean verificarE=false;
    private String Codigo;
    public VerDetallesVentasMYSQL(Context context,DetallesVentasViewModel viewModel,String codigo) {
        super(context);
        this.ViewModel=viewModel;
        this.Codigo=codigo;
        execute("");
        new android.os.Handler().postDelayed(new Runnable() {
            public void run() {
                if(verificarE){
                    onCancelled();
                }else{
                    onCancelled("No se puede conectar a La Base Datos");
                    verificarE=true;
                }
            }
        },31000);
    }

    @Override
    public void ConsultaBaseDatos() {

    }
}
