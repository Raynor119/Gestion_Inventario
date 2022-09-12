package com.pixels.Inventario.View.Gestion_Ventas.TabLayout.Fragment;

import android.app.DatePickerDialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Gestion_Ventas.TabLayout.Calendario.MesAnnoPickerDialog;

import java.util.Calendar;

public class MensualesFragment extends Fragment {
    public int mes,anno;
    public MesAnnoPickerDialog pd;
    public TextInputEditText calendarioEditText;
    public MensualesFragment(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mensuales, container, false);
        calendarioEditText=(TextInputEditText) rootView.findViewById(R.id.fecha);
        calendarioEditText.setEnabled(false);
        calendarioEditText.setText(getMes());
        CardView Bcalendario=(CardView) rootView.findViewById(R.id.calendario);
        Bcalendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pd = new MesAnnoPickerDialog(MensualesFragment.this);
                pd.Context=MensualesFragment.this;
                pd.SelectFecha();
            }
        });
        return rootView;
    }
    public String getMes(){
        Calendar calendar= Calendar.getInstance();
        mes=(calendar.get(Calendar.MONTH)+1);
        anno=calendar.get(Calendar.YEAR);
        return mes+"/"+anno;
    }

}
