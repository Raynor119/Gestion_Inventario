package com.pixels.Inventario.View.Activity.Caja.AlertDialog;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.textfield.TextInputLayout;
import com.pixels.Inventario.Model.DatosE.VentaRealizada;
import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Activity.Caja.Caja;
import com.pixels.Inventario.View.Activity.Gestion_Productos.AgregarProductos.TextWatcher.TextMoneda;
import com.pixels.Inventario.ViewModel.Caja.Venta.RealizarVentaViewModel;
import com.pixels.Inventario.ViewModel.Gestion_Productos.AgregarProductos.ConvertirModenaINT;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class alertVenta {
    public Caja Context;
    public alertVenta(Caja context){
        this.Context=context;
    }
    public void pedirEfectivo(){
        AlertDialog.Builder builder = new AlertDialog.Builder(Context);
        builder.setCancelable(false);
        LayoutInflater inflater= Context.getLayoutInflater();
        View view=inflater.inflate(R.layout.alertefectivo, null);
        final EditText cantidad=(EditText) view.findViewById(R.id.Efectivo);
        final TextInputLayout Ccantidad=(TextInputLayout) view.findViewById(R.id.CEfectivo);
        builder.setView(view);
        builder.setTitle("Cantidad de Efectivo Recibida");
        builder.setNegativeButton("salir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
        TextMoneda conversor=new TextMoneda(Context);
        cantidad.addTextChangedListener(conversor.moneda(cantidad));
        Button botton = (Button) view.findViewById(R.id.ButtonG);
        botton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean verificar=true;
                if(cantidad.getText().toString().equals("")){
                    cantidad.setError("Digite la cantidad de Efectivo recibida");
                    verificar=false;
                }
                if(verificar){
                    int total=0;
                    for(int b=0;b<Context.Productos.size();b++){
                        int suptotal=(int)(Context.Productos.get(b).getPrecio()*Context.Productos.get(b).getCantidad());
                        total=total+suptotal;
                    }
                    ConvertirModenaINT convertir=new ConvertirModenaINT();
                    int efectivo=convertir.Convertir(cantidad.getText().toString());
                    if(efectivo<total){
                        cantidad.setError("La cantidad de efectivo recibida tiene que ser mayor o igual al Total de la Venta");
                    }else{
                        int cambio=efectivo-total;
                        RealizarVentaViewModel Venta= ViewModelProviders.of(Context).get(RealizarVentaViewModel.class);
                        Venta.reset();
                        Venta.realizarVenta(Context,efectivo);
                        Observer<List<VentaRealizada>> observer=new Observer<List<VentaRealizada>>() {
                            @Override
                            public void onChanged(List<VentaRealizada> ventaRealizadas) {
                                if(ventaRealizadas.get(0).isVerificar()){
                                    VentaRealizada(ventaRealizadas.get(0).getCodigoVenta(),cambio);
                                    Context.Productos=new ArrayList<>();
                                    Context.iniciarRecyclerView();
                                    dialog.cancel();
                                }else{
                                    Toast.makeText(Context, "Error al Realizar la Venta", Toast.LENGTH_LONG).show();
                                }
                            }
                        };
                        Venta.getResultado().observe(Context,observer);
                    }
                }
            }
        });
    }
    public void VentaRealizada(String CodigoV,int Cambio){
        AlertDialog.Builder builder = new AlertDialog.Builder(Context);
        builder.setCancelable(false);
        builder.setTitle("Venta Realizada");
        LayoutInflater inflater= Context.getLayoutInflater();
        View view=inflater.inflate(R.layout.alertventafinalizada, null);
        NumberFormat formato= NumberFormat.getNumberInstance();
        builder.setView(view);
        TextView cambio=(TextView) view.findViewById(R.id.cambio);
        cambio.setText("$"+formato.format(Cambio));
        builder.setPositiveButton("Compartir La Factura", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setNegativeButton("Ver La Factura", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setNeutralButton("Salir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
