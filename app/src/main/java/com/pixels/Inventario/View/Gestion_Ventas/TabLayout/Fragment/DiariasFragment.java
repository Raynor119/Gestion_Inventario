package com.pixels.Inventario.View.Gestion_Ventas.TabLayout.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.pixels.Inventario.R;

import java.util.Calendar;

public class DiariasFragment extends Fragment {
    private int dia,mes,anno;
    public DiariasFragment(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_diarias, container, false);
        TextInputEditText calendarioEditText=(TextInputEditText) rootView.findViewById(R.id.fecha);
        calendarioEditText.setEnabled(false);
        calendarioEditText.setText(getDia());
        CardView Bcalendario=(CardView) rootView.findViewById(R.id.calendario);
        Bcalendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog= new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int Mes, int Dia) {
                        dia=Dia;
                        mes=Mes;
                        anno=year;
                        calendarioEditText.setText(Dia+"/"+(mes+1)+"/"+year);
                    }
                },anno,mes,dia);
                datePickerDialog.show();
            }
        });
        return rootView;
    }

    public String getDia(){
        Calendar calendar= Calendar.getInstance();
        dia=calendar.get(Calendar.DAY_OF_MONTH);
        mes=calendar.get(Calendar.MONTH);
        anno=calendar.get(Calendar.YEAR);
        return dia+"/"+(mes+1)+"/"+anno;
    }
}
