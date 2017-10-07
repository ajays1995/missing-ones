package com.example.hp_hp.missingones;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.missingones.R;

/**
 * Created by HP-HP on 29-07-2014.
 */
public class help_fragment  extends Fragment {

    TextView t1,t2,t3,t4,t5,t6,t7,t8,t9,t10;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_help, container, false);

        t1=(TextView)rootView.findViewById(R.id.data1);
        t2=(TextView)rootView.findViewById(R.id.data2);
        /*t3=(TextView)rootView.findViewById(R.id.data3);
        t4=(TextView)rootView.findViewById(R.id.data4);
        t5=(TextView)rootView.findViewById(R.id.data5);
        t6=(TextView)rootView.findViewById(R.id.data6);
        t7=(TextView)rootView.findViewById(R.id.data7);
        t8=(TextView)rootView.findViewById(R.id.data8);
        t9=(TextView)rootView.findViewById(R.id.data9);
        t10=(TextView)rootView.findViewById(R.id.data10);*/




        return rootView;
    }
}


