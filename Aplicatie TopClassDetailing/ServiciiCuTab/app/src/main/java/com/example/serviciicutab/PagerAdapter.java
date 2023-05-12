package com.example.serviciicutab;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.serviciicutab.Caroserie;
import com.example.serviciicutab.Ceramica;
import com.example.serviciicutab.Detailing;
import com.example.serviciicutab.Faruri;
import com.example.serviciicutab.Motor;

public class PagerAdapter extends FragmentPagerAdapter {
    //@NonNull

    private int numOfTabs;
    public PagerAdapter(FragmentManager fm, int numOfTabs){
        super(fm);
        this.numOfTabs= numOfTabs;

    }
    @Override
    public Fragment getItem(int position) {

        switch (position){

            case 0:
                return new Detailing();
            case 1:
                return new Faruri();
            case 2:
                return new Caroserie();
            case 3:
                return new Motor();
            case 4:
                return new Ceramica();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
