package com.missingones;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by HP-HP on 22-06-2014.
 */
public class search_fragment extends Fragment {

    Button button1,button2;

    // flag for Internet connection status
    Boolean isInternetPresent = false;

    // Connection detector class
    ConnectionDetector cd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        Log.e("yogi","fragment created");
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        /*button1 = (Button) rootView.findViewById(R.id.button);*/
        button2 = (Button) rootView.findViewById(R.id.button2);

        // creating connection detector class instance
        cd = new ConnectionDetector(getActivity().getApplicationContext());

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
                Intent i = new Intent(getActivity(),all_reports.class);
                Log.e("yogi","Intent Created");
                startActivity(i);

/*                // get Internet status
                isInternetPresent = cd.isConnectingToInternet();

                // check for Internet status
                if (isInternetPresent) {
                    // Internet Connection is Present
                    // make HTTP requests
                    showAlertDialog(search_fragment.this, "Internet Connection",
                            "You have internet connection", true);

                    // here you set what you want to do when user clicks your button_next,
                    // e.g. launch a new activity
                    Intent i = new Intent(getActivity(),all_reports.class);
                    startActivity(i);


                } else {
                    // Internet connection is not present
                    // Ask user to connect to Internet
                    showAlertDialog(search_fragment.this, "No Internet Connection",
                            "You don't have internet connection.", false);
                }

*/


            }
        });
        return rootView;
    }

    /**
     * Function to display simple Alert Dialog
     * @param context - application context
     * @param title - alert dialog title
     * @param message - alert message
     * @param status - success/failure (used to set icon)   */
    public void showAlertDialog(search_fragment context, String title, String message, Boolean status) {
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();

        // Setting Dialog Title
        alertDialog.setTitle(title);

        // Setting Dialog Message
        alertDialog.setMessage(message);

        // Setting alert dialog icon
        alertDialog.setIcon((status) ? R.drawable.success : R.drawable.fail);

        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }

}