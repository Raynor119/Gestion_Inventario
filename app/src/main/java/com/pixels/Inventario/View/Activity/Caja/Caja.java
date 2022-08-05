package com.pixels.Inventario.View.Activity.Caja;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.pixels.Inventario.Model.DatosE.Producto;
import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Activity.Caja.AlertDialog.alertpeso;
import com.pixels.Inventario.View.Activity.Caja.RecyclerViewAdaptador.productoVRecyclerViewAdapter;
import com.pixels.Inventario.View.Activity.Caja.TextWatcher.TextCodigoCaja;
import com.pixels.Inventario.View.Activity.Gestion_Productos.AgregarProductos.AgregarProductos;
import com.pixels.Inventario.View.Activity.Gestion_Productos.AgregarProductos.TextWatcher.TextCodigo;
import com.pixels.Inventario.ViewModel.Caja.VerificarCodigo.VerificarCodigoCajaViewModel;
import com.pixels.Inventario.ViewModel.Gestion_Productos.VerificarCodigo.VerificarCodigoViewModel;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class Caja extends AppCompatActivity {
    public TextInputEditText Codigo;
    public TextInputLayout CCodigo;
    public TextView impretotal,Total;
    public Button Button;
    public RecyclerView tableproductos;
    public List<Producto> Productos=new ArrayList<>();;
    public boolean verificarEnter=true;
    public int[] i = {0};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caja);
        Codigo=(TextInputEditText)findViewById(R.id.codigo);
        CCodigo=(TextInputLayout) findViewById(R.id.EditCodigo);
        impretotal=(TextView) findViewById(R.id.impretotal);
        Total=(TextView)findViewById(R.id.Total);
        Button=(Button)findViewById(R.id.ButtonG);
        tableproductos=(RecyclerView)findViewById(R.id.opcion_list);
        Codigo.setFocusableInTouchMode(true);
        Codigo.requestFocus();
        TextCodigoCaja verificarC=new TextCodigoCaja(Caja.this);
        Codigo.addTextChangedListener(verificarC.codigo(Codigo,CCodigo));
        Codigo.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // si presiona enter
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER) || (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    if(Codigo.getText().toString().equals("")){
                        if(verificarEnter){
                            CCodigo.setError("Digite el Codgo del Producto");
                            Codigo.setFocusableInTouchMode(true);
                            Codigo.requestFocus();
                        }else{
                            verificarEnter=true;
                        }
                    }else{
                        VerificarCodigoCajaViewModel verificar= ViewModelProviders.of(Caja.this).get(VerificarCodigoCajaViewModel.class);
                        verificar.reset();
                        verificar.verificarCodigo(Codigo.getText().toString(),Caja.this);
                        final boolean[] vercodigo = {true};
                        Observer<Boolean> observer=new Observer<Boolean>() {
                            @Override
                            public void onChanged(Boolean aBoolean) {
                                if(aBoolean){

                                }else{
                                    vercodigo[0] =false;
                                    CCodigo.setError("El Codigo del Producto no esta Registrado en la Base de Datos");
                                    Codigo.setFocusableInTouchMode(true);
                                    Codigo.requestFocus();
                                }
                            }
                        };
                        verificar.getResultado().observe(Caja.this,observer);
                        if(vercodigo[0]){
                            Observer<List<Producto>> observer1 = new Observer<List<Producto>>() {
                                @Override
                                public void onChanged(List<Producto> productos) {
                                    if(i[0]==0){
                                        i[0]++;
                                        boolean productorepi=false;
                                        int posicion=0;
                                        for (int b=0;b<Productos.size();b++){
                                            if(Productos.get(b).getCodigo().equals(productos.get(0).getCodigo())){
                                                productorepi=true;
                                                posicion=b;
                                            }
                                        }

                                        if(productorepi){
                                            if(productos.get(0).getTipoC().equals("peso")){
                                                alertpeso pedir=new alertpeso(Caja.this,productos,Productos.get(posicion).getCantidad(),false,posicion);
                                                pedir.pedircantidad();
                                            }
                                            if(productos.get(0).getTipoC().equals("unitario")){
                                                double cantiR=Productos.get(posicion).getCantidad()+1;
                                                int canti=(int) cantiR;
                                                Productos.get(posicion).setCantidad(canti);
                                            }
                                        }else{
                                            if(productos.get(0).getTipoC().equals("peso")){
                                                alertpeso pedir=new alertpeso(Caja.this,productos,0,true,0);
                                                pedir.pedircantidad();
                                            }
                                            if(productos.get(0).getTipoC().equals("unitario")){
                                                Productos.add(new Producto(productos.get(0).getCodigo(),productos.get(0).getNombre(),1,productos.get(0).getTipoC(),productos.get(0).getCosteP(),productos.get(0).getPrecio(),productos.get(0).getIva()));
                                            }
                                        }
                                        iniciarRecyclerView();
                                        Codigo.setText("");
                                        verificarEnter=false;
                                        Codigo.setFocusableInTouchMode(true);
                                        Codigo.requestFocus();
                                    }else{
                                        i[0]=0;
                                    }
                                }
                            };
                            verificar.getProductos().observe(Caja.this,observer1);
                        }

                    }
                    return true;
                }
                return false;
            }
        });
        Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Productos.size()==0){
                    CCodigo.setError("Registre al menos un producto para realizar la Venta");
                }
            }
        });
    }
    public void iniciarRecyclerView(){
        tableproductos.setAdapter(null);
        tableproductos.setAdapter(new productoVRecyclerViewAdapter(Caja.this));
        int total=0;
        for(int b=0;b<Productos.size();b++){
            int suptotal=(int)(Productos.get(b).getPrecio()*Productos.get(b).getCantidad());
            total=total+suptotal;
        }
        NumberFormat formato= NumberFormat.getNumberInstance();
        impretotal.setText("Total: ");
        Total.setText("$ "+formato.format(total));

    }

}