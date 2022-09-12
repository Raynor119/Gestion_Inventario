package com.pixels.Inventario.View.Gestion_Ventas.TabLayout.Calendario;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;


import androidx.fragment.app.DialogFragment;

import com.pixels.Inventario.R;

import java.util.Calendar;

public class MesAnnoPickerDialog extends DialogFragment {

    private DatePickerDialog.OnDateSetListener listener;
    private int anno,mes;

    public void setListener(DatePickerDialog.OnDateSetListener listener,int Anno,int Mes) {
        this.listener = listener;
        this.anno=Anno;
        this.mes=Mes;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialog = inflater.inflate(R.layout.date_picker_dialog, null);
        final NumberPicker mesPicker = (NumberPicker) dialog.findViewById(R.id.picker_month);
        final NumberPicker annoPicker = (NumberPicker) dialog.findViewById(R.id.picker_year);

        TextView mesTextView=(TextView) dialog.findViewById(R.id.mes);
        TextView annonTextView=(TextView) dialog.findViewById(R.id.annon);

        mesPicker.setMinValue(1);
        mesPicker.setMaxValue(12);
        mesPicker.setValue(mes);

        annoPicker.setMinValue(1900);
        annoPicker.setMaxValue(2099);
        annoPicker.setValue(anno);

        VerificarMes(mesTextView,mesPicker);
        annonTextView.setText(""+annoPicker.getValue());

        annoPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                annonTextView.setText(""+numberPicker.getValue());
            }
        });

        mesPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                VerificarMes(mesTextView,numberPicker);
            }
        });

        builder.setView(dialog)
                .setPositiveButton("aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        listener.onDateSet(null, annoPicker.getValue(), mesPicker.getValue(), 0);
                    }
                })
                .setNegativeButton("cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MesAnnoPickerDialog.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }
     public void VerificarMes(TextView m,NumberPicker n){
         if(n.getValue()==1){
             m.setText("Enero");
         }
         if(n.getValue()==2){
             m.setText("Febrero");
         }
         if(n.getValue()==3){
             m.setText("Marzo");
         }
         if(n.getValue()==4){
             m.setText("Abril");
         }
         if(n.getValue()==5){
             m.setText("Mayo");
         }
         if(n.getValue()==6){
             m.setText("Junio");
         }
         if(n.getValue()==7){
             m.setText("Julio");
         }
         if(n.getValue()==8){
             m.setText("Agosto");
         }
         if(n.getValue()==9){
             m.setText("Septiembre");
         }
         if(n.getValue()==10){
             m.setText("Octubre");
         }
         if(n.getValue()==11){
             m.setText("Noviembre");
         }
         if(n.getValue()==12){
             m.setText("Diciembre");
         }
     }
}