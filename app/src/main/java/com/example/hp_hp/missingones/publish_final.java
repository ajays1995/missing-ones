package com.example.hp_hp.missingones;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.missingones.R;

/**
 * Created by HP-HP on 03-07-2014.
 */
public class publish_final extends ActionBarActivity{

    TextView textView;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.publish);

        textView=(TextView)findViewById(R.id.textView3);
        b= (Button)findViewById(R.id.home1);
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                Intent i = new Intent(getApplicationContext(), MyActivity.class);
                startActivity(i);


            }
        });



    }
}
