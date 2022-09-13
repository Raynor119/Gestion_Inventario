package com.pixels.Inventario.View.Gestion_Ventas.TabLayout.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.pixels.Inventario.Model.DatosE.Producto;
import com.pixels.Inventario.Model.DatosE.TotalVentas;
import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Gestion_Productos.Fragment.VerInventarioFragment;
import com.pixels.Inventario.View.Gestion_Productos.RecyclerViewAdaptador.ProductosRecyclerViewAdapter;
import com.pixels.Inventario.View.Gestion_Ventas.TabLayout.Fragment.RecyclerViewAdaptador.VentasDiariasRecyclerViewAdapter;
import com.pixels.Inventario.ViewModel.Gestion_Productos.ProductosRecyclerViewModel;
import com.pixels.Inventario.ViewModel.Gestion_Ventas.VentasRecyclerViewModel;

import java.util.Calendar;
import java.util.List;

public class DiariasFragment extends Fragment {

    private TextInputEditText calendarioEditText;
    public RecyclerView reciclerView;

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
            }
        });


        return rootView;
    }
    public void iniciarRecyclerView(String Ffecha){
        reciclerView.setAdapter(null);
        VentasRecyclerViewModel ventas= ViewModelProviders.of(getActivity()).get(VentasRecyclerViewModel.class);
        ventas.reset();
        ventas.buscarVentas(getActivity(),getConsulta(Ffecha));
        final Observer<List<TotalVentas>> observer= new Observer<List<TotalVentas>>() {
            @Override
            public void onChanged(List<TotalVentas> ventasD) {
                reciclerView.setAdapter(new VentasDiariasRecyclerViewAdapter(ventasD,DiariasFragment.this));
            }
        };
        ventas.getResultado().observe(getActivity(),observer);
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
        return "SELECT venta.codigo,COUNT(ventasproductos.codigoV) as CProductosV,sum(ventasproductos.CantidadV*ventasproductos.PrecioPV) as TotalV," +
                "(sum(((ventasproductos.CantidadV-ventasproductos.CantidadD)*ventasproductos.PrecioPV)/(1.0+(ventasproductos.IvaPV*0.01)))-sum((ventasproductos.CantidadV-ventasproductos.CantidadD)*ventasproductos.CostePV)) as GananciaNeta," +
                "(sum((((ventasproductos.CantidadV-ventasproductos.CantidadD)*ventasproductos.PrecioPV)/(1.0+(ventasproductos.IvaPV*0.01))*ventasproductos.IvaPV*0.01))) as TotalIvaP," +
                "(sum((ventasproductos.CantidadV)*ventasproductos.CostePV)) as CostoV," +
                "(SUM(ventasproductos.CantidadD*ventasproductos.CostePV)) as PerdidaD," +
                "(SUM(ventasproductos.CantidadD*ventasproductos.PrecioPV)) as TotalD" +
                ",venta.Fecha FROM ventasproductos INNER JOIN venta ON ventasproductos.codigov=venta.codigo WHERE CAST(Fecha AS DATE) = '"+anno+"-"+mes+"-"+dia+"' GROUP BY venta.codigo";
    }

    public String getDia(){
        Calendar calendar= Calendar.getInstance();
        int dia=calendar.get(Calendar.DAY_OF_MONTH);
        int mes=calendar.get(Calendar.MONTH)+1;
        int anno=calendar.get(Calendar.YEAR);
        return dia+"/"+(mes)+"/"+anno;
    }
}
