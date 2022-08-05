package com.pixels.Inventario.View.Activity.Caja.AlertDialog;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;

import com.google.android.material.textfield.TextInputLayout;
import com.pixels.Inventario.Model.DatosE.Producto;
import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Activity.Caja.Caja;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
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
        final AutoCompleteTextView spinner=(AutoCompleteTextView) view.findViewById(R.id.spinner_dropdown);
        final TextInputLayout TipoC=(TextInputLayout) view.findViewById(R.id.spinner);
        cantidad.setText(""+Cantidad);
        spinner.setText("Kilograms(Kg)");
        String [] tipoC={"Gramos(g)","Kilogramos(Kg)"};
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(Context, R.layout.tipocantidad,tipoC);
        spinner.setAdapter(adapter);
        final boolean[] verificarg = {true};
        final boolean[] verificark = { false };
        spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(spinner.getText().toString().equals("Gramos(g)")){
                    if(verificarg[0]){
                        double conversion=Double.parseDouble(cantidad.getText().toString());
                        double canntidadconvertida=conversion*(1000);
                        BigDecimal bd = new BigDecimal(canntidadconvertida);
                        bd = bd.setScale(3, RoundingMode.HALF_UP);
                        cantidad.setText(""+bd.doubleValue());
                        verificarg[0]=false;
                        verificark[0]=true;
                    }
                }
                if(spinner.getText().toString().equals("Kilogramos(Kg)")){
                   if(verificark[0]){
                       double conversion=Double.parseDouble(cantidad.getText().toString());
                       double canntidadconvertida=conversion*(0.001);
                       BigDecimal bd = new BigDecimal(canntidadconvertida);
                       bd = bd.setScale(3, RoundingMode.HALF_UP);
                       cantidad.setText(""+bd.doubleValue());
                       verificark[0] =false;
                       verificarg[0] =true;
                   }
                }
                TipoC.setErrorEnabled(false);
            }
        });

        builder.setView(view);
        builder.setTitle("Cantidad del Producto");
        builder.setNegativeButton("salir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
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
                double verificarCero=Double.parseDouble(cantidad.getText().toString());
                if(verificarCero==0.0){
                    cantidad.setError("no se puede digitar 0");
                    veridicar=false;
                }
                if(spinner.getText().toString().equals("")){
                    TipoC.setError("Seleccione el Tipo de Cantidad");
                    veridicar=false;
                }
                if(veridicar){
                    double cantidadpeso=Double.parseDouble(cantidad.getText().toString());
                    if(spinner.getText().toString().equals("Gramos(g)")){
                        cantidadpeso=cantidadpeso*(0.001);
                    }
                    BigDecimal bd = new BigDecimal(cantidadpeso);
                    bd = bd.setScale(3, RoundingMode.HALF_UP);
                    cantidadpeso=bd.doubleValue();
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
