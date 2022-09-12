package com.pixels.Inventario.View.Gestion_Ventas.TabLayout.Calendario;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Gestion_Ventas.TabLayout.Fragment.AnualesFragment;

public class AnnoPickerDialog {
    private DatePickerDialog.OnDateSetListener listener;
    public static AnualesFragment Context;
    private NumberPicker annoPicker;
    private int anno;
    public AnnoPickerDialog(AnualesFragment context, int Anno){
        this.Context=context;
        this.anno=Anno;
        this.listener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int anno, int mes, int i2) {
                Context.calendarioEditText.setText(anno+"");
            }
        };
    }

    public void  SelectFecha() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Context.getActivity());
        LayoutInflater inflater = Context.getActivity().getLayoutInflater();
        View dialog = inflater.inflate(R.layout.anno_picker_dialog, null);

        annoPicker = (NumberPicker) dialog.findViewById(R.id.picker_year);

        TextView annonTextView=(TextView) dialog.findViewById(R.id.annon);


        annoPicker.setMinValue(1900);
        annoPicker.setMaxValue(2099);
        annoPicker.setValue(anno);



        annonTextView.setText(""+annoPicker.getValue());

        annoPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                annonTextView.setText(""+numberPicker.getValue());
            }
        });

        builder.setView(dialog)
                .setPositiveButton("aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        listener.onDateSet(null, annoPicker.getValue(), 0, 0);
                    }
                })
                .setNegativeButton("cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        AlertDialog alert =builder.create();
        alert.show();
    }
}
