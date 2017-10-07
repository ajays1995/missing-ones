package com.example.hp_hp.missingones;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.missingones.R;

/**
 * Created by HP-HP on 06-07-2014.
 */
public class search_category extends ActionBarActivity {

    EditText name,age,gender,location;
    Button b;
    String name1,age1,gender1,location1;
    String flag="0",flag1="0",flag2="0",flag3="0";

    String tag="0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_category);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        name=(EditText)findViewById(R.id.name_se);
        age=(EditText)findViewById(R.id.ageg);
        gender=(EditText)findViewById(R.id.gen);
        location=(EditText)findViewById(R.id.location);
        b=(Button)findViewById(R.id.search);


        b.setEnabled(false);

       /*name1=name.getText().toString();
       gender1=gender.getText().toString();
       age1=age.getText().toString();
       location1=location.getText().toString();*/


        name.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                if(s.equals("") && s.equals(" ") )
                {
                    name1=null;
                    b.setEnabled(false);
                }
                else
                {
                    name1=name.getText().toString();
                    flag="1";
                    b.setEnabled(true);
                }

                if (flag.equals("0") && flag1.equals("0") && flag2.equals("0") && flag3.equals("0"))
                {
                    b.setEnabled(false);
                }else
                {
                    b.setEnabled(true);
                }

            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                b.setEnabled(false);
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

            }
        });


        age.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                if (s.equals("") && s.equals(" ")) {
                    age1 = null;
                    b.setEnabled(false);
                } else {
                    age1 = age.getText().toString();
                    flag1="1";
                    b.setEnabled(true);
                }

                if (flag.equals("0") && flag1.equals("0") && flag2.equals("0") && flag3.equals("0"))
                {
                    b.setEnabled(false);
                }else
                {
                    b.setEnabled(true);
                }

            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                b.setEnabled(false);
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

            }
        });



        gender.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                if(s.equals("") && s.equals(" "))
                {
                    gender1=null;
                    b.setEnabled(false);
                }
                else
                {
                    gender1=gender.getText().toString();
                    flag2="1";
                    b.setEnabled(true);
                }

                if (flag.equals("0") && flag1.equals("0") && flag2.equals("0") && flag3.equals("0"))
                {
                    b.setEnabled(false);
                }else
                {
                    b.setEnabled(true);
                }

            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                b.setEnabled(false);
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

            }
        });



        location.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                if(s.equals("") && s.equals(" "))
                {
                    location1=null;
                    b.setEnabled(false);
                }
                else
                {
                    location1=location.getText().toString();
                    flag3="1";
                    b.setEnabled(true);
                }

                if (flag.equals("0") && flag1.equals("0") && flag2.equals("0") && flag3.equals("0"))
                {
                    b.setEnabled(false);
                }else
                {
                    b.setEnabled(true);
                }

            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                b.setEnabled(false);
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

            }
        });






        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

               /*Toast.makeText(getApplicationContext(), age1, Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), gender1, Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), location1, Toast.LENGTH_SHORT).show();*/



                Intent i = new Intent(getApplicationContext(), search_list.class);
                i.putExtra("name",name1);
                i.putExtra("age",age1);
                i.putExtra("gender",gender1);
                i.putExtra("location",location1);

                Toast.makeText(getApplicationContext(), name1, Toast.LENGTH_SHORT).show();
                startActivity(i);
                finish();

            }
        });

      }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button_next, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
            {// app icon in action bar clicked; goto parent activity.
                Intent parentIntent1 = new Intent(this,MyActivity.class);
                startActivity(parentIntent1);
                this.finish();
                return true;}
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}
