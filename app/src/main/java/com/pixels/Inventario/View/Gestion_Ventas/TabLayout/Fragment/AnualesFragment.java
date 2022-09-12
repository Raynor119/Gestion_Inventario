package com.pixels.Inventario.View.Gestion_Ventas.TabLayout.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Gestion_Ventas.TabLayout.Calendario.AnnoPickerDialog;
import com.pixels.Inventario.View.Gestion_Ventas.TabLayout.Calendario.MesAnnoPickerDialog;

import java.util.Calendar;

public class AnualesFragment extends Fragment {
    public TextInputEditText calendarioEditText;
    public AnualesFragment(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_anuales, container, false);
        calendarioEditText=(TextInputEditText) rootView.findViewById(R.id.fecha);
        calendarioEditText.setEnabled(false);
        calendarioEditText.setText(getAnno());
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
        return rootView;
    }
    public String getAnno(){
        Calendar calendar= Calendar.getInstance();
        int anno=calendar.get(Calendar.YEAR);
        return ""+anno;
    }
}
