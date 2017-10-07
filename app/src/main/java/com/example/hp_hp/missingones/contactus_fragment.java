package com.example.hp_hp.missingones;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.missingones.R;

/**
 * Created by HP-HP on 31-07-2014.
 */
public class contactus_fragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_contact, container, false);

        return rootView;
    }
}
