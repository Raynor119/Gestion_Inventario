package com.pixels.Inventario.View.Gestion_Ventas.TabLayout.Calendario;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;


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

        mesPicker.setMinValue(1);
        mesPicker.setMaxValue(12);
        mesPicker.setValue(mes);

        annoPicker.setMinValue(1900);
        annoPicker.setMaxValue(2099);
        annoPicker.setValue(anno);

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
}