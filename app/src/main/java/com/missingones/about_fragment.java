package com.missingones;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by HP-HP on 22-06-2014.
 */
public class about_fragment extends Fragment {

    TextView textview,t2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_about, container, false);

        textview=(TextView)rootView.findViewById(R.id.data1);
        t2=(TextView)rootView.findViewById(R.id.data2);

        textview.setText("You've probably heard it said that \"necessity is the mother of invention.\" Well, that was certainly the case with ‘MISSING ONES’, this application. We were on an outing in another town, when we came across a kid crying, lonely, and standing helplessly at the roadside. Soon we realised that the kid was a mislaid one.\n" +
                "Being stranded, we handed over that kid to an immediate police station and returned. But, we had no clue about what occurred to the child next and what would have happened to her ill-fated parents. Analysing the success rate of previously registered similar cases, we were inexact indeed about the girl reaching back to her guardians. This incidence certainly raised a necessity of this APP of ours ‘THE MISSING ONES’.\n");
        t2.setText("This app of \"MISSING ONES\" seeks to address the gaps between police, NGOs and other concerned agencies so as to guarantee that all the missing women, children and men are logged & restored and also ensure that offenders who exploited them are exposed and penalised. ");

        return rootView;
    }
}
