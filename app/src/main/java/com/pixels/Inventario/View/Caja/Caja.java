package com.pixels.Inventario.View.Caja;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.pixels.Inventario.Model.DatosE.Producto;
import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Caja.AlertDialog.alertVenta;
import com.pixels.Inventario.View.Caja.AlertDialog.alertVentaDevolucion;
import com.pixels.Inventario.View.Caja.AlertDialog.alertbuscar;
import com.pixels.Inventario.View.Caja.RecyclerViewAdaptador.productoVRecyclerViewAdapter;
import com.pixels.Inventario.View.Caja.TextWatcher.TextCodigoCaja;
import com.pixels.Inventario.View.Caja.VerificacionCodigo.VerificarCodigoC;
import com.pixels.Inventario.View.Caja.VerificacionCodigo.VerificarCodigoD;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class Caja extends AppCompatActivity {
    public TextInputEditText Codigo;
    public TextInputLayout CCodigo;
    public TextView impretotal,Total;
    public Button Button;
    public CardView Escaner,Buscar;
    public RecyclerView tableproductos;
    public List<Producto> Productos=new ArrayList<>();;
    public boolean verificarEnter=true;
    public static int[] i = {0};
    public static boolean vdevolucion=true;
    alertVentaDevolucion alert=new alertVentaDevolucion(Caja.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caja);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setTitle("");
        setSupportActionBar(toolbar);
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
        TextCodigoCaja verificarC=new TextCodigoCaja(Caja.this);
        Codigo.addTextChangedListener(verificarC.codigo(Codigo,CCodigo));
        Codigo.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // si presiona enter
                if ((keyCode == KeyEvent.KEYCODE_ENTER)) {

                    if(Codigo.getText().toString().equals("")){
                        if(verificarEnter){
                            CCodigo.setError("Digite el Codigo del Producto");
                            Codigo.setFocusableInTouchMode(true);
                            Codigo.requestFocus();
                        }else{
                            verificarEnter=true;
                        }
                    }else{

                        if(verificarEnter){
                            if(i[0]==0) {
                                i[0]++;
                                VerificarCodigoC codigo=new VerificarCodigoC(Caja.this);
                                codigo.verificarCodigo(true);
                                verificarEnter=false;
                            }else{
                                i[0]=0;
                                verificarEnter=true;
                            }
                        }else{
                            if(i[0]==1){
                                i[0]=0;
                                if(i[0]==0) {
                                    i[0]++;
                                    VerificarCodigoC codigo=new VerificarCodigoC(Caja.this);
                                    codigo.verificarCodigo(true);
                                    verificarEnter=false;
                                }else{
                                    i[0]=0;
                                    verificarEnter=true;
                                }
                            }else {
                                verificarEnter=true;
                            }
                        }
                    }
                    return true;
                }else{
                    if(verificarEnter){

                    }else {
                        verificarEnter = true;
                        Toast.makeText(getApplicationContext(), "se Actualizo1", Toast.LENGTH_LONG).show();
                    }
                    Toast.makeText(getApplicationContext(), "se Actualizo1"+keyCode, Toast.LENGTH_LONG).show();
                }
                return false;
            }
        });
        Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Productos.size()==0){
                    CCodigo.setError("Registre al menos un producto para realizar la Venta");
                }else{
                    alertVenta alertventa=new alertVenta(Caja.this);
                    alertventa.pedirEfectivo();
                }
            }
        });
        Escaner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i[0]=0;
                new IntentIntegrator(Caja.this).initiateScan();
            }
        });
        Buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertbuscar buscar=new alertbuscar(Caja.this);
                buscar.buscarproductos();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menud,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id= item.getItemId();
        if(id == R.id.opcion){
            alert.pediridventa();
        }
        return super.onOptionsItemSelected(item);
    }

    public void iniciarRecyclerView(){
        tableproductos.setAdapter(null);
        if(Productos.size()==0){
            impretotal.setText("");
            Total.setText("");
        }else{
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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null){
            if(vdevolucion){
                if (result.getContents() != null){
                    Codigo.setText(result.getContents()+"");
                    VerificarCodigoC codigo=new VerificarCodigoC(Caja.this);
                    codigo.verificarCodigo(false);
                }else{
                    CCodigo.setError("Error al escanear el código de barras");
                    Codigo.setText("");
                    Codigo.setFocusableInTouchMode(true);
                    Codigo.requestFocus();
                }
            }else{
                if (result.getContents() != null){
                    alert.Codigo.setText(result.getContents()+"");
                    VerificarCodigoD codigo=new VerificarCodigoD(alert);
                    codigo.verificarcodigo(false);
                }else{
                    alert.CCodigo.setError("Error al escanear el código de barras");
                    alert.Codigo.setText("");
                    alert.Codigo.setFocusableInTouchMode(true);
                    alert.Codigo.requestFocus();
                }
            }
        }

    }


}