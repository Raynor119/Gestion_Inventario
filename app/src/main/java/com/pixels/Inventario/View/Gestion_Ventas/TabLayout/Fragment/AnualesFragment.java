package com.pixels.Inventario.View.Gestion_Ventas.TabLayout.Fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import com.pixels.Inventario.View.Gestion_Ventas.TabLayout.Calendario.AnnoPickerDialog;
import com.pixels.Inventario.View.Gestion_Ventas.TabLayout.Fragment.GraficasFragment.GColumnas.GraficaColumnaA;
import com.pixels.Inventario.View.Gestion_Ventas.TabLayout.Fragment.GraficasFragment.GLineas.GraficaLinearA;
import com.pixels.Inventario.View.Gestion_Ventas.TabLayout.Fragment.GraficasFragment.GLineas.GraficaLinearM;
import com.pixels.Inventario.View.Gestion_Ventas.TabLayout.Fragment.RecyclerViewAdaptador.VentasRecyclerViewAdapter;
import com.pixels.Inventario.ViewModel.Gestion_Ventas.VentasAnualesRecyclerViewModel;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.List;

public class AnualesFragment extends Fragment {

    public TextInputEditText calendarioEditText;
    public RecyclerView reciclerView;
    private TextView costototal,totalP,totalVn,totalDevo,impuesto,totalGananNeta,TotalVendido;
    private CardView BGraficaC;
    private LinearLayout LGrafica;
    private CardView BGraficaL;
    private LinearLayout LGraficaL;

    public AnualesFragment(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_anuales, container, false);
        reciclerView= rootView.findViewById(R.id.venta_list);
        calendarioEditText=(TextInputEditText) rootView.findViewById(R.id.fecha);
        calendarioEditText.setEnabled(false);
        calendarioEditText.setText(getAnno());
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
                int anno;
                anno=Integer.parseInt(calendarioEditText.getText().toString());
                AnnoPickerDialog pd = new AnnoPickerDialog(AnualesFragment.this,anno);
                pd.SelectFecha();
            }
        });

        iniciarRecyclerView(calendarioEditText.getText().toString());
        iniciarGraficaColumnas(calendarioEditText.getText().toString());

        FloatingActionButton fab = rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarRecyclerView(calendarioEditText.getText().toString());
                iniciarGraficaColumnas(calendarioEditText.getText().toString());
            }
        });

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
           GraficaColumnaA graficaColumna=new GraficaColumnaA(Ffecha);
           graficaColumna.Fecha=Ffecha;
           getChildFragmentManager().beginTransaction().replace(R.id.container,graficaColumna).commit();
       }catch (Exception e){

       }
    }
    public void iniciarRecyclerView(String Ffecha){
        reciclerView.setAdapter(null);
        resertTextView();
        VentasAnualesRecyclerViewModel ventas= ViewModelProviders.of(getActivity()).get(VentasAnualesRecyclerViewModel.class);
        ventas.reset();
        ventas.buscarVentas(getActivity(),getConsulta(Ffecha));
        final Observer<List<TotalVentas>> observer= new Observer<List<TotalVentas>>() {
            @Override
            public void onChanged(List<TotalVentas> ventasD) {
                reciclerView.setAdapter(new VentasRecyclerViewAdapter(ventasD,AnualesFragment.this.getActivity()));
                int costototalIndex=0;
                int totalPerdida=0;
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
                //totalP.setText("$ "+formato.format(totalPerdida));
                totalDevo.setText("$ "+formato.format(totalDevolu));
                impuesto.setText("$ "+formato.format(bdIva.doubleValue()));
                totalGananNeta.setText("$ "+formato.format(bdGN.doubleValue()));
                totalVn.setText("$ "+formato.format(totalVN));
                TotalVendido.setText("$ "+formato.format(totalV));
                LGraficaL.setVisibility(ConstraintLayout.VISIBLE);
                try{
                    GraficaLinearA graficaColumna=new GraficaLinearA(ventasD,getActivity());
                    graficaColumna.VentasD=ventasD;
                    graficaColumna.Context=getActivity();
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
        int anno;
        anno=Integer.parseInt(fechaE);
        String c="";
        consultasDatos dinici=new consultasDatos(getActivity());
        if(dinici.obtenerD().get(0).getBasedatos().equals("SQLITE")){
            c="WHERE strftime('%Y', Venta.Fecha) = '"+anno+"' GROUP BY Venta.codigo";
        }
        if(dinici.obtenerD().get(0).getBasedatos().equals("MYSQL")){
            c="WHERE YEAR(Fecha) = '"+anno+"' GROUP BY Venta.codigo";
        }
        return c;
    }


    public String getAnno(){
        Calendar calendar= Calendar.getInstance();
        int anno=calendar.get(Calendar.YEAR);
        return ""+anno;
    }
}
