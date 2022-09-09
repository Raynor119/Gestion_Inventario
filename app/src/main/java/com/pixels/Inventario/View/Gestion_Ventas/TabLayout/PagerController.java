package com.pixels.Inventario.View.Gestion_Ventas.TabLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.pixels.Inventario.R;
import com.pixels.Inventario.View.Gestion_Ventas.TabLayout.Fragment.AnualesFragment;
import com.pixels.Inventario.View.Gestion_Ventas.TabLayout.Fragment.DiariasFragment;
import com.pixels.Inventario.View.Gestion_Ventas.TabLayout.Fragment.MensualesFragment;

public class PagerController extends FragmentPagerAdapter {
    private int numeroTab;

    public PagerController(FragmentManager fm, int behavior) {
        super(fm);
        this.numeroTab=behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new DiariasFragment();
            case 1:
                return new MensualesFragment();
            case 2:
                return new AnualesFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numeroTab;
    }
}
