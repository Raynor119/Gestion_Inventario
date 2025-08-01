package com.pixels.Inventario.View.Gestion_Ventas.DetallesVentas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.zxing.BarcodeFormat;

import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.pixels.Inventario.Model.DatosE.Producto;
import com.pixels.Inventario.Model.DatosE.detallesPV;
import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Gestion_Productos.AgregarProductos.AgregarProductos;
import com.pixels.Inventario.View.Gestion_Ventas.DetallesVentas.RecyclerViewAdaptador.ProductosDRecyclerViewAdapter;
import com.pixels.Inventario.ViewModel.Caja.Venta.RealizarFacturaViewModel;
import com.pixels.Inventario.ViewModel.Gestion_Ventas.DetallesVentas.DetallesVentasViewModel;
import com.pixels.Inventario.ViewModel.Gestion_Ventas.VentasAnualesRecyclerViewModel;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class DetallesVentas extends AppCompatActivity {

    private String Fecha,Efectivo,Codigo;
    private TextView TCodigo,TFecha,TEfectivo,TCambio,TSubTotal,TIva,TTotalV,TTextMas,TDevolu,codebar;
    private LinearLayout LDetalles,LDevoluciones;
    private CardView Masdetalles;
    private ImageView IcodigoBarras;
    private NumberFormat formato= NumberFormat.getNumberInstance();
    private RecyclerView recyclerView;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_ventas);
        recyclerView=(RecyclerView) findViewById(R.id.opcion_list);
        Fecha=getIntent().getExtras().getString("fecha");
        Efectivo=getIntent().getExtras().getString("efectivo");
        Codigo=getIntent().getExtras().getString("codigo");
        TCodigo=(TextView) findViewById(R.id.CodigoV);
        TFecha=(TextView) findViewById(R.id.fecha);
        TEfectivo=(TextView) findViewById(R.id.Efectivo);
        IcodigoBarras=(ImageView) findViewById(R.id.codigoBarras);
        codebar=(TextView) findViewById(R.id.codiobar);
        BarcodeEncoder barcodeEncoder=new BarcodeEncoder();
        try {
            Bitmap bitmap=barcodeEncoder.encodeBitmap(Codigo, BarcodeFormat.CODE_128,580,80);
            IcodigoBarras.setImageBitmap(bitmap);
            codebar.setText(""+Codigo);
        } catch (Exception e) {

        }
        TCodigo.setText(""+Codigo);
        TFecha.setText(""+Fecha);
        TEfectivo.setText("$ "+formato.format(Integer.parseInt(Efectivo)));
        TCambio=(TextView) findViewById(R.id.cambioT);
        TSubTotal=(TextView) findViewById(R.id.SubTotal);
        TIva=(TextView) findViewById(R.id.Iva);
        TTotalV=(TextView) findViewById(R.id.TotalV);
        TDevolu=(TextView) findViewById(R.id.Devolu);
        LDetalles=(LinearLayout) findViewById(R.id.LDetalles);
        LDevoluciones=(LinearLayout) findViewById(R.id.LDevoluciones);
        LDetalles.setVisibility(ConstraintLayout.GONE);
        LDevoluciones.setVisibility(ConstraintLayout.GONE);
        Masdetalles=(CardView) findViewById(R.id.masdetalles);
        TTextMas=(TextView) findViewById(R.id.TextMas);
        Masdetalles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(LDetalles.getVisibility()==ConstraintLayout.GONE){
                    LDetalles.setVisibility(ConstraintLayout.VISIBLE);
                    TTextMas.setText("Ocultar detalles");
                }else{
                    LDetalles.setVisibility(ConstraintLayout.GONE);
                    TTextMas.setText("Mas detalles");
                }
            }
        });
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "no se a cargado los datos de la Venta", Toast.LENGTH_LONG).show();
            }
        });
        iniciarReciclerView();
    }
    public void iniciarReciclerView(){
        DetallesVentasViewModel productos= ViewModelProviders.of(DetallesVentas.this).get(DetallesVentasViewModel.class);
        productos.reset();
        productos.VerDetallerVentas(DetallesVentas.this,Codigo);
        Observer<List<detallesPV>> observer=new Observer<List<detallesPV>>() {
            @Override
            public void onChanged(List<detallesPV> detallesPVS){
                int Total=0,SubT=0,IvaT=0,TDevuel=0;
                for(int i=0;i<detallesPVS.size();i++){
                    Total=Total+((int)(detallesPVS.get(i).getCantidadV()*detallesPVS.get(i).getPrecioPV()));
                    double SubTList= (detallesPVS.get(i).getCantidadV()*(detallesPVS.get(i).getPrecioPV()/(1.0+(detallesPVS.get(i).getIvaPV()*0.01))));
                    BigDecimal precionSub = new BigDecimal(SubTList).setScale(0, RoundingMode.HALF_UP);
                    SubT=SubT+((int) precionSub.doubleValue());
                    double ivaList= (detallesPVS.get(i).getCantidadV()*(detallesPVS.get(i).getPrecioPV()/(1.0+(detallesPVS.get(i).getIvaPV()*0.01))))*(detallesPVS.get(i).getIvaPV()*0.01);
                    BigDecimal precionIva = new BigDecimal(ivaList).setScale(0, RoundingMode.HALF_UP);
                    IvaT=IvaT+((int) precionIva.doubleValue());
                    BigDecimal precionDevo = new BigDecimal(detallesPVS.get(i).getCantidadD()*detallesPVS.get(i).getPrecioPV()).setScale(0, RoundingMode.HALF_UP);
                    TDevuel=TDevuel+((int)precionDevo.doubleValue());
                }
                TSubTotal.setText("$ "+formato.format(SubT));
                TIva.setText("$ "+formato.format(IvaT));
                TTotalV.setText("$ "+formato.format(Total));
                TCambio.setText("$ "+formato.format((Integer.parseInt(Efectivo)-Total)));
                if(TDevuel>0){
                    TDevolu.setText("$ "+formato.format(TDevuel));
                    LDevoluciones.setVisibility(ConstraintLayout.VISIBLE);
                }
                recyclerView.setAdapter(null);
                recyclerView.setAdapter(new ProductosDRecyclerViewAdapter(DetallesVentas.this,detallesPVS));
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        RealizarFacturaViewModel Venta= ViewModelProviders.of(DetallesVentas.this).get(RealizarFacturaViewModel.class);
                        List<Producto> Productos=new ArrayList<>();
                        for(int i=0;i<detallesPVS.size();i++){
                            Productos.add(new Producto(detallesPVS.get(i).getCodigoP(),detallesPVS.get(i).getNombre(),detallesPVS.get(i).getCantidadV(),detallesPVS.get(i).getTipoC(),detallesPVS.get(i).getCostePV(),detallesPVS.get(i).getPrecioPV(),detallesPVS.get(i).getIvaPV()));
                        }
                        Venta.crearFacturaVer(DetallesVentas.this,Productos,Codigo,Fecha,Integer.parseInt(Efectivo),"no");
                    }
                });
            }
        };
        productos.getResultado().observe(DetallesVentas.this,observer);
    }
}