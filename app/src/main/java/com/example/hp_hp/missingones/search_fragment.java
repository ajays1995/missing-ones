package com.example.hp_hp.missingones;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.missingones.R;

/**
 * Created by HP-HP on 22-06-2014.
 */
public class search_fragment extends Fragment {

    Button button1,button2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        //button1 = (Button) rootView.findViewById(R.id.button);
        button2 = (Button) rootView.findViewById(R.id.button2);

        /*button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // here you set what you want to do when user clicks your button_next,
                // e.g. launch a new activity
                Intent i = new Intent(getActivity(),search_category.class);
                startActivity(i);

            }
        });*/

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // here you set what you want to do when user clicks your button_next,
                // e.g. launch a new activity
                Intent i = new Intent(getActivity(),all_reports.class);
                startActivity(i);
            }
        });
        return rootView;
    }

}