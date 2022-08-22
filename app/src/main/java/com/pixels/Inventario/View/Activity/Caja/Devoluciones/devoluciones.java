package com.pixels.Inventario.View.Activity.Caja.Devoluciones;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.pixels.Inventario.Model.DatosE.VentasProductoD;
import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Activity.Caja.Devoluciones.AlertDialog.alerObservacion;
import com.pixels.Inventario.View.Activity.Caja.Devoluciones.AlertDialog.alertbuscarPV;
import com.pixels.Inventario.View.Activity.Caja.Devoluciones.RecyclerViewAdaptador.productoRecyclesViewAdapterD;
import com.pixels.Inventario.View.Activity.Caja.TextWatcher.TextCodigoCaja;
import com.pixels.Inventario.ViewModel.Caja.Devoluciones.RealizarDevolucionViewModel;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class devoluciones extends AppCompatActivity {

    public TextInputEditText Codigo;
    public TextInputLayout CCodigo;
    public TextView impretotal,Total,Titulo;
    public Button Button;
    public CardView Escaner,Buscar;
    public RecyclerView tableproductos;
    public List<VentasProductoD> Productos=new ArrayList<>();
    public static List<VentasProductoD> ProductosV=new ArrayList<>();
    public static boolean verificarEnter=true;
    public int indexProducto=0;
    public int[] i = {0};
    private String CodigoV="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caja);
        Bundle bundle=getIntent().getExtras();
        CodigoV=bundle.getString("codigo");
        Titulo=(TextView) findViewById(R.id.titulo);
        Titulo.setText("Devolucion de la Factura #"+CodigoV);
        Escaner=(CardView) findViewById(R.id.Escaner);
        Buscar=(CardView) findViewById(R.id.Buscar);
        Codigo=(TextInputEditText)findViewById(R.id.codigo);
        CCodigo=(TextInputLayout) findViewById(R.id.EditCodigo);
        impretotal=(TextView) findViewById(R.id.impretotal);
        Total=(TextView)findViewById(R.id.Total);
        Button=(Button)findViewById(R.id.ButtonG);
        tableproductos=(RecyclerView)findViewById(R.id.opcion_list);
        Codigo.setFocusableInTouchMode(true);
        Codigo.requestFocus();
        TextCodigoCaja verificarC=new TextCodigoCaja(devoluciones.this);
        Codigo.addTextChangedListener(verificarC.codigo(Codigo,CCodigo));
        Codigo.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // si presiona enter
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER) || (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    if(Codigo.getText().toString().equals("")){
                        if(verificarEnter){
                            CCodigo.setError("Digite el Codigo del Producto");
                            Codigo.setFocusableInTouchMode(true);
                            Codigo.requestFocus();
                        }else{
                            verificarEnter=true;
                        }
                    }else{
                        boolean vercodigo=false;
                        for(int i=0;i<ProductosV.size();i++){
                            if(ProductosV.get(i).getCodigoP().equals(Codigo.getText().toString())){
                                vercodigo=true;
                                indexProducto=i;
                            }
                        }

                        if(vercodigo){
                            if(i[0]==0){
                                i[0]++;
                                boolean productorepi=false;

                                for (int b=0;b<Productos.size();b++){
                                    if(Productos.get(b).getCodigoP().equals(ProductosV.get(indexProducto).getCodigoP())){
                                        productorepi=true;
                                    }
                                }
                                if(productorepi){
                                    Codigo.setText("");
                                    CCodigo.setError("El producto ya esta regitrado para la devolucion");
                                    Codigo.setFocusableInTouchMode(true);
                                    verificarEnter=false;
                                    Codigo.requestFocus();
                                }else{
                                    if(ProductosV.get(indexProducto).getEstadoDevolucion().equals("si")){
                                        Codigo.setText("");
                                        CCodigo.setError("El producto ya ha sido devuelto");
                                        Codigo.setFocusableInTouchMode(true);
                                        verificarEnter=false;
                                        Codigo.requestFocus();
                                    }else{
                                        alerObservacion observacion=new alerObservacion(devoluciones.this);
                                        observacion.pedirObservaciones();
                                        iniciarRecyclerView();
                                        Codigo.setText("");
                                        verificarEnter=false;
                                        Codigo.setFocusableInTouchMode(true);
                                        Codigo.requestFocus();
                                    }
                                }
                            }else{
                                i[0]=0;
                            }
                        }else{
                            if(i[0]==0){
                                i[0]++;
                                Codigo.setText("");
                                CCodigo.setError("El Codigo del Producto no esta Registrado en la Venta");
                                Codigo.setFocusableInTouchMode(true);
                                verificarEnter=false;
                                Codigo.requestFocus();
                            }else{
                                i[0]=0;
                            }
                        }

                    }
                    return true;
                }
                return false;
            }
        });
        Button.setText("Realizar Devolucion");
        Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Productos.size()==0){
                    CCodigo.setError("Registre al menos un producto para realizar la devolucion");
                }else{
                    RealizarDevolucionViewModel Devolucion= ViewModelProviders.of(devoluciones.this).get(RealizarDevolucionViewModel.class);
                    Devolucion.reset();
                    Devolucion.realizarDevolucion(devoluciones.this,CodigoV);
                    Observer<Boolean> observer=new Observer<Boolean>() {
                        @Override
                        public void onChanged(Boolean aBoolean) {
                            if(aBoolean){

                            }else{
                                Toast.makeText(devoluciones.this, "Error al Realizar la Devolucion", Toast.LENGTH_LONG).show();
                            }
                        }
                    };
                    Devolucion.getResultado().observe(devoluciones.this,observer);
                }
            }
        });
        Escaner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i[0]=0;
                new IntentIntegrator(devoluciones.this).initiateScan();
            }
        });
        Buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertbuscarPV buscar=new alertbuscarPV(devoluciones.this);
                buscar.buscarproductos();
            }
        });
    }
    public void iniciarRecyclerView(){
        tableproductos.setAdapter(null);
        if(Productos.size()==0){
            impretotal.setText("");
            Total.setText("");
        }else{
            tableproductos.setAdapter(new productoRecyclesViewAdapterD(devoluciones.this));
            int total=0;
            for(int b=0;b<Productos.size();b++){
                int suptotal=(int)(Productos.get(b).getPrecioPV()*Productos.get(b).getCantidadV());
                total=total+suptotal;
            }
            NumberFormat formato= NumberFormat.getNumberInstance();
            impretotal.setText("Devolucion Total: ");
            Total.setText("$ "+formato.format(total));
        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null)
            if (result.getContents() != null){
                Codigo.setText(result.getContents()+"");
                boolean vercodigo=false;
                for(int i=0;i<ProductosV.size();i++){
                    if(ProductosV.get(i).getCodigoP().equals(Codigo.getText().toString())){
                        vercodigo=true;
                        indexProducto=i;
                    }
                }

                if(vercodigo){
                    if(i[0]==0){
                        i[0]++;
                        boolean productorepi=false;

                        for (int b=0;b<Productos.size();b++){
                            if(Productos.get(b).getCodigoP().equals(ProductosV.get(indexProducto).getCodigoP())){
                                productorepi=true;
                            }
                        }
                        if(productorepi){
                            Codigo.setText("");
                            CCodigo.setError("El producto ya esta regitrado para la devolucion");
                            Codigo.setFocusableInTouchMode(true);
                            Codigo.requestFocus();
                        }else{
                            if(ProductosV.get(indexProducto).getEstadoDevolucion().equals("si")){
                                Codigo.setText("");
                                CCodigo.setError("El producto ya ha sido devuelto");
                                Codigo.setFocusableInTouchMode(true);
                                Codigo.requestFocus();
                            }else{
                                alerObservacion observacion=new alerObservacion(devoluciones.this);
                                observacion.pedirObservaciones();
                                iniciarRecyclerView();
                                Codigo.setText("");
                                Codigo.setFocusableInTouchMode(true);
                                Codigo.requestFocus();
                            }
                        }
                    }else{
                        i[0]=0;
                    }
                }else{
                    if(i[0]==0){
                        i[0]++;
                        Codigo.setText("");
                        CCodigo.setError("El Codigo del Producto no esta Registrado en la Venta");
                        Codigo.setFocusableInTouchMode(true);
                        Codigo.requestFocus();
                    }else{
                        i[0]=0;
                    }
                }
            }else{
                CCodigo.setError("Error al escanear el código de barras");
                Codigo.setText("");
                Codigo.setFocusableInTouchMode(true);
                Codigo.requestFocus();
            }
    }

}
