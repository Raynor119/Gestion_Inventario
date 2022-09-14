package com.pixels.Inventario.View.Gestion_Ventas.TabLayout.Fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
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
import com.pixels.Inventario.View.Gestion_Ventas.TabLayout.Calendario.MesAnnoPickerDialog;
import com.pixels.Inventario.View.Gestion_Ventas.TabLayout.Fragment.RecyclerViewAdaptador.VentasDiariasRecyclerViewAdapter;
import com.pixels.Inventario.ViewModel.Gestion_Ventas.VentasAnualesRecyclerViewModel;
import com.pixels.Inventario.ViewModel.Gestion_Ventas.VentasMensualesRecyclerViewModel;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.List;

public class AnualesFragment extends Fragment {

    public TextInputEditText calendarioEditText;
    public RecyclerView reciclerView;
    private TextView costototal,totalP,totalDevo,impuesto,totalGananNeta,TotalVendido;

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
        totalP=(TextView) rootView.findViewById(R.id.Perdida);
        totalDevo=(TextView) rootView.findViewById(R.id.TotalDevolu);
        impuesto=(TextView) rootView.findViewById(R.id.IvaP);
        totalGananNeta=(TextView) rootView.findViewById(R.id.GananN);
        TotalVendido=(TextView) rootView.findViewById(R.id.TotalVendido);
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

        FloatingActionButton fab = rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarRecyclerView(calendarioEditText.getText().toString());
            }
        });
        return rootView;
    }
    public void iniciarRecyclerView(String Ffecha){
        reciclerView.setAdapter(null);
        VentasAnualesRecyclerViewModel ventas= ViewModelProviders.of(getActivity()).get(VentasAnualesRecyclerViewModel.class);
        ventas.reset();
        ventas.buscarVentas(getActivity(),getConsulta(Ffecha));
        final Observer<List<TotalVentas>> observer= new Observer<List<TotalVentas>>() {
            @Override
            public void onChanged(List<TotalVentas> ventasD) {
                reciclerView.setAdapter(new VentasDiariasRecyclerViewAdapter(ventasD,AnualesFragment.this.getActivity()));
                int costototalIndex=0;
                int totalPerdida=0;
                int totalDevolu=0;
                double impuestoiva=0;
                double GananN=0;
                int totalV=0;
                for(int i=0;i<ventasD.size();i++){
                    costototalIndex=costototalIndex+ventasD.get(i).getCostoV();
                    totalPerdida=totalPerdida+ventasD.get(i).getPerdidaD();
                    totalDevolu=totalDevolu+ventasD.get(i).getTotalD();
                    impuestoiva=impuestoiva+ventasD.get(i).getTotalIvaP();
                    GananN=GananN+ventasD.get(i).getGananciaNeta();
                    totalV=totalV+ventasD.get(i).getTotalV();
                }
                NumberFormat formato= NumberFormat.getNumberInstance();
                BigDecimal bdIva = new BigDecimal(impuestoiva);
                bdIva = bdIva.setScale(0, RoundingMode.HALF_UP);
                BigDecimal bdGN = new BigDecimal(GananN);
                bdGN = bdGN.setScale(0, RoundingMode.HALF_UP);
                costototal.setText("$ "+formato.format(costototalIndex));
                totalP.setText("$ "+formato.format(totalPerdida));
                totalDevo.setText("$ "+formato.format(totalDevolu));
                impuesto.setText("$ "+formato.format(bdIva.doubleValue()));
                totalGananNeta.setText("$ "+formato.format(bdGN.doubleValue()));
                TotalVendido.setText("$ "+formato.format(totalV));
            }
        };
        ventas.getResultado().observe(getActivity(),observer);
    }

    public String getConsulta(String fechaE){
        int anno;
        anno=Integer.parseInt(fechaE);
        String c="";
        consultasDatos dinici=new consultasDatos(getActivity());
        if(dinici.obtenerD().get(0).getBasedatos().equals("SQLITE")){
            c="WHERE strftime('%Y', venta.Fecha) = '"+anno+"' GROUP BY venta.codigo";
        }
        if(dinici.obtenerD().get(0).getBasedatos().equals("MYSQL")){
            c="WHERE YEAR(Fecha) = '"+anno+"' GROUP BY venta.codigo";
        }
        return c;
    }


    public String getAnno(){
        Calendar calendar= Calendar.getInstance();
        int anno=calendar.get(Calendar.YEAR);
        return ""+anno;
    }
}
