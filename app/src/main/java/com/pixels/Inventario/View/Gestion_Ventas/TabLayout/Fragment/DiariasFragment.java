package com.pixels.Inventario.View.Gestion_Ventas.TabLayout.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.pixels.Inventario.Model.Basededatos.SQLite.DatosInicio.consultasDatos;
import com.pixels.Inventario.Model.DatosE.TotalVentas;
import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Gestion_Ventas.TabLayout.Fragment.GraficasFragment.GColumnas.GraficaColumnaD;
import com.pixels.Inventario.View.Gestion_Ventas.TabLayout.Fragment.GraficasFragment.GLineas.GraficaLinearD;
import com.pixels.Inventario.View.Gestion_Ventas.TabLayout.Fragment.RecyclerViewAdaptador.VentasRecyclerViewAdapter;
import com.pixels.Inventario.ViewModel.Gestion_Ventas.VentasDiariasRecyclerViewModel;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.List;

public class DiariasFragment extends Fragment {

    private TextInputEditText calendarioEditText;
    public RecyclerView reciclerView;
    private TextView costototal,totalP,totalVn,totalDevo,impuesto,totalGananNeta,TotalVendido;
    private CardView BGraficaC;
    private LinearLayout LGrafica;
    private CardView BGraficaL;
    private LinearLayout LGraficaL;

    public DiariasFragment(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_diarias, container, false);
        reciclerView= rootView.findViewById(R.id.venta_list);
        calendarioEditText=(TextInputEditText) rootView.findViewById(R.id.fecha);
        costototal=(TextView) rootView.findViewById(R.id.CostoTotal);
        //totalP=(TextView) rootView.findViewById(R.id.Perdida);
        totalDevo=(TextView) rootView.findViewById(R.id.TotalDevolu);
        impuesto=(TextView) rootView.findViewById(R.id.IvaP);
        totalGananNeta=(TextView) rootView.findViewById(R.id.GananN);
        totalVn=(TextView) rootView.findViewById(R.id.TotalVendidoN);
        TotalVendido=(TextView) rootView.findViewById(R.id.TotalVendido);
        LGrafica=(LinearLayout) rootView.findViewById(R.id.LGrafica);
        LGrafica.setVisibility(ConstraintLayout.VISIBLE);
        BGraficaC=(CardView) rootView.findViewById(R.id.mostrar);
        LGraficaL=(LinearLayout) rootView.findViewById(R.id.LGraficaL);
        LGraficaL.setVisibility(ConstraintLayout.GONE);
        BGraficaL=(CardView) rootView.findViewById(R.id.mostrarL);
        calendarioEditText.setEnabled(false);
        calendarioEditText.setText(getDia());
        calendarioEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                iniciarRecyclerView(calendarioEditText.getText().toString());
                iniciarGraficaColumnas(calendarioEditText.getText().toString());
            }
        });
        CardView Bcalendario=(CardView) rootView.findViewById(R.id.calendario);
        Bcalendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int dia=1,mes=1,anno;
                int cont=0;
                String date="";
                for(int i=0;i<calendarioEditText.getText().length();i++){
                    if((calendarioEditText.getText().charAt(i)+"").equals("/")){
                        if(cont==0){
                            dia=Integer.parseInt(date);
                            date="";
                        }
                        if (cont==1){
                            mes=Integer.parseInt(date);
                            date="";
                        }
                        cont++;
                    }else {
                        date = date + (calendarioEditText.getText().charAt(i));
                    }
                }
                anno=Integer.parseInt(date);
                DatePickerDialog datePickerDialog= new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int Mes, int Dia) {
                        int mes=Mes+1;
                        calendarioEditText.setText(Dia+"/"+(mes)+"/"+year);
                        iniciarRecyclerView(calendarioEditText.getText().toString());
                        iniciarGraficaColumnas(calendarioEditText.getText().toString());
                    }
                },anno,mes-1,dia);
                datePickerDialog.show();
            }
        });

        iniciarRecyclerView(calendarioEditText.getText().toString());

        FloatingActionButton fab = rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarRecyclerView(calendarioEditText.getText().toString());
                iniciarGraficaColumnas(calendarioEditText.getText().toString());
            }
        });
        iniciarGraficaColumnas(calendarioEditText.getText().toString());
        BGraficaC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(LGrafica.getVisibility()==ConstraintLayout.GONE){
                    LGrafica.setVisibility(ConstraintLayout.VISIBLE);
                    iniciarGraficaColumnas(calendarioEditText.getText().toString());
                }else{
                    LGrafica.setVisibility(ConstraintLayout.GONE);
                }
            }
        });
        BGraficaL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(LGraficaL.getVisibility()==ConstraintLayout.GONE){
                    LGraficaL.setVisibility(ConstraintLayout.VISIBLE);
                    iniciarRecyclerView(calendarioEditText.getText().toString());
                }else{
                    LGraficaL.setVisibility(ConstraintLayout.GONE);
                }
            }
        });
        return rootView;
    }
    public void iniciarGraficaColumnas(String Ffecha){
       try{
           GraficaColumnaD graficaColumna=new GraficaColumnaD(Ffecha);
           graficaColumna.Fecha=Ffecha;
           getChildFragmentManager().beginTransaction().replace(R.id.container,graficaColumna).commit();
       }catch (Exception e){

       }
    }
    public void iniciarRecyclerView(String Ffecha){
        reciclerView.setAdapter(null);
        resertTextView();
        VentasDiariasRecyclerViewModel ventas= ViewModelProviders.of(getActivity()).get(VentasDiariasRecyclerViewModel.class);
        ventas.reset();
        ventas.buscarVentas(getActivity(),getConsulta(Ffecha));
        final Observer<List<TotalVentas>> observer= new Observer<List<TotalVentas>>() {
            @Override
            public void onChanged(List<TotalVentas> ventasD) {

                reciclerView.setAdapter(new VentasRecyclerViewAdapter(ventasD,DiariasFragment.this.getActivity()));
                int costototalIndex=0;
                //int totalPerdida=0;
                int totalDevolu=0;
                double impuestoiva=0;
                double GananN=0;
                int totalVN=0;
                int totalV=0;
                for(int i=0;i<ventasD.size();i++){
                    costototalIndex=costototalIndex+ventasD.get(i).getCostoV();
                    //total de perdidas se quita por que un producto devuelto no es necesariamente es una perdida
                    //totalPerdida=totalPerdida+ventasD.get(i).getPerdidaD();
                    totalDevolu=totalDevolu+ventasD.get(i).getTotalD();
                    impuestoiva=impuestoiva+ventasD.get(i).getTotalIvaP();
                    GananN=GananN+ventasD.get(i).getGananciaNeta();
                    totalVN=totalVN+(ventasD.get(i).getTotalV()-ventasD.get(i).getTotalD());
                    totalV=totalV+ventasD.get(i).getTotalV();
                }
                NumberFormat formato= NumberFormat.getNumberInstance();
                BigDecimal bdIva = new BigDecimal(impuestoiva);
                bdIva = bdIva.setScale(0, RoundingMode.HALF_UP);
                BigDecimal bdGN = new BigDecimal(GananN);
                bdGN = bdGN.setScale(0, RoundingMode.HALF_UP);
                costototal.setText("$ "+formato.format(costototalIndex));
                totalDevo.setText("$ "+formato.format(totalDevolu));
                impuesto.setText("$ "+formato.format(bdIva.doubleValue()));
                totalGananNeta.setText("$ "+formato.format(bdGN.doubleValue()));
                totalVn.setText("$ "+formato.format(totalVN));
                TotalVendido.setText("$ "+formato.format(totalV));

                LGraficaL.setVisibility(ConstraintLayout.VISIBLE);
                try{
                    GraficaLinearD graficaColumna=new GraficaLinearD(ventasD);
                    graficaColumna.VentasD=ventasD;
                    getChildFragmentManager().beginTransaction().replace(R.id.containerL,graficaColumna).commit();
                }catch (Exception e){

                }
            }
        };
        ventas.getResultado().observe(getActivity(),observer);
    }

    public void resertTextView(){
        costototal.setText("$ 0");
        //totalP.setText("$ 0");
        totalDevo.setText("$ 0");
        impuesto.setText("$ 0");
        totalGananNeta.setText("$ 0");
        totalVn.setText("$ 0");
        TotalVendido.setText("$ 0");
    }

    public String getConsulta(String fechaE){
        int dia=1,mes=1,anno;
        int cont=0;
        String date="";
        for(int i=0;i<fechaE.length();i++){
            if((fechaE.charAt(i)+"").equals("/")){
                if(cont==0){
                    dia=Integer.parseInt(date);
                    date="";
                }
                if (cont==1){
                    mes=Integer.parseInt(date);
                    date="";
                }
                cont++;
            }else {
                date = date + (fechaE.charAt(i));
            }
        }
        anno=Integer.parseInt(date);

        String c="";
        consultasDatos dinici=new consultasDatos(getActivity());
        if(dinici.obtenerD().get(0).getBasedatos().equals("SQLITE")){
            if(mes<10){
                if(dia<10){
                    c="WHERE DATE(Venta.Fecha) = '"+anno+"-"+("0"+mes)+"-"+("0"+dia)+"' GROUP BY Venta.codigo";
                }else{
                    c="WHERE DATE(Venta.Fecha) = '"+anno+"-"+("0"+mes)+"-"+dia+"' GROUP BY Venta.codigo";
                }
            }else{
                if(dia<10){
                    c="WHERE DATE(Venta.Fecha) = '"+anno+"-"+mes+"-"+("0"+dia)+"' GROUP BY Venta.codigo";
                }else{
                    c="WHERE DATE(Venta.Fecha) = '"+anno+"-"+mes+"-"+dia+"' GROUP BY Venta.codigo";
                }
            }
        }
        if(dinici.obtenerD().get(0).getBasedatos().equals("MYSQL")){
            c="WHERE CAST(Fecha AS DATE) = '"+anno+"-"+mes+"-"+dia+"' GROUP BY Venta.codigo";
        }

        return c;
    }

    public String getDia(){
        Calendar calendar= Calendar.getInstance();
        int dia=calendar.get(Calendar.DAY_OF_MONTH);
        int mes=calendar.get(Calendar.MONTH)+1;
        int anno=calendar.get(Calendar.YEAR);
        return dia+"/"+(mes)+"/"+anno;
    }
}
