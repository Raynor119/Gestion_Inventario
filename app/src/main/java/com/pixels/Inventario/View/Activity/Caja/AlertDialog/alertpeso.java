package com.pixels.Inventario.View.Activity.Caja.AlertDialog;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;

import com.pixels.Inventario.Model.DatosE.Producto;
import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Activity.Caja.Caja;

import java.util.List;

public class alertpeso {
    public Caja Context;
    private List<Producto> productos;
    private double Cantidad;
    private boolean Veri;
    private int Posicion;
    public alertpeso(Caja context,List<Producto> productos,double cantidad,boolean veri,int posicion){
        this.Context=context;
        this.productos=productos;
        this.Cantidad=cantidad;
        this.Veri=veri;
        this.Posicion=posicion;
    }

    public void pedircantidad(){
        AlertDialog.Builder builder = new AlertDialog.Builder(Context);
        builder.setCancelable(false);
        LayoutInflater inflater= Context.getLayoutInflater();
        View view=inflater.inflate(R.layout.alertpedircantidad, null);
        final EditText cantidad=(EditText) view.findViewById(R.id.peso);
        cantidad.setText(""+Cantidad);
        builder.setView(view);
        builder.setTitle("Cantidad del Producto");
        AlertDialog dialog = builder.create();
        dialog.show();
        Button botton = (Button) view.findViewById(R.id.ButtonG);
        botton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean veridicar=true;
                if(cantidad.getText().toString().equals("")){
                    cantidad.setError("Digite la Cantidad");
                    veridicar=false;
                }
                if(veridicar){
                    double cantidadpeso=Double.parseDouble(cantidad.getText().toString());
                    if(Veri){
                        Context.Productos.add(new Producto(productos.get(0).getCodigo(),productos.get(0).getNombre(),cantidadpeso,productos.get(0).getTipoC(),productos.get(0).getCosteP(),productos.get(0).getPrecio(),productos.get(0).getIva()));
                    }else{
                        Context.Productos.get(Posicion).setCantidad(cantidadpeso);
                    }
                    Context.iniciarRecyclerView();
                    dialog.cancel();
                }
            }
        });
    }
}
