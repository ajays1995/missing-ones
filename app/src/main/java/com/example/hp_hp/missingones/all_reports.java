package com.example.hp_hp.missingones;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.missingones.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class all_reports extends ActionBarActivity {

    ListView list1;
    String jsonStr;
    LazyAdapter adapter;

  //  private ActionBarActivity barActivity;
    private ProgressDialog pDialog;



    // URL to get contacts JSON
  //  private static String url= "http://nitishnnl.16mb.com/ibeto/get_all_products.php";
    private static String url= "http://10.0.2.2/ibeto/get_all_products.php";



    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();

    // JSON Node names
    private static final String TAG_SUCCESS = "success";

    // JSON Node names
    private static final String TAG_IBETO = "ibeto";
    private static final String TAG_ID = "id";
    private static final String TAG_NAME = "name";
    private static final String TAG_GENDER = "gender";
    private static final String TAG_AGE = "age";
    private static final String TAG_MDATE = "mdate";
    private static final String TAG_GUARDIAN = "guardian";
    private static final String TAG_IMAGE_PATH = "image_path";
    private static final String TAG_MADDRESS = "maddress";
    private static final String TAG_MSTATE = "mstate";
    private static final String TAG_MCITY = "mcity";
    private static final String TAG_HTYPE = "htype";
    private static final String TAG_HCOLOR = "hcolor";
    private static final String TAG_COMPLEXION = "complexion";
    private static final String TAG_BODY = "body";
    private static final String TAG_MARK = "mark";
    private static final String TAG_RNAME = "rname";
    private static final String TAG_RELATIONSHIP = "relationship";
    private static final String TAG_CONTACT = "contact";
    private static final String TAG_RADDRESS = "raddress";
    private static final String TAG_RSTATE = "rstate";
    private static final String TAG_RCITY = "rcity";

    String id=null, name=null, gender=null, age=null, mdate=null, guardian=null, image_path=null, maddress=null, mstate=null,
            mcity=null, htype=null, hcolor=null, complexion=null, body=null,mark=null, rname=null, relationship=null,
            contact=null, raddress=null, rstate=null, rcity=null;


    /*String pre_name=null,pre_age=null,pre_gender=null,pre_location=null,pre_tag="0";*/




    // contacts JSONArray
    JSONArray ibeto = null;

    // Hashmap for ListView
    ArrayList<HashMap<String, String>> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_reports);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*barActivity.getSupportActionBar().show();
        barActivity.getSupportActionBar().setTitle("Reports");*/


        /*Intent in = getIntent();
        pre_name = in.getStringExtra("name");
        Toast.makeText(getApplicationContext(), pre_name, Toast.LENGTH_SHORT).show();
        if (in.getExtras() != null)
        {
            // Get JSON values from previous intent
            pre_name = in.getStringExtra("name");
            pre_gender = in.getStringExtra("gender");
            pre_age = in.getStringExtra("age");
            pre_location = in.getStringExtra("location");
            pre_tag = in.getStringExtra("tag");
            Toast.makeText(getApplicationContext(), pre_name, Toast.LENGTH_SHORT).show();
            *//*Toast.makeText(getApplicationContext(), pre_age, Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(), pre_gender, Toast.LENGTH_SHORT).show();*//*
        }else {
                pre_name=null;
                pre_age=null;
                pre_gender=null;
                pre_location=null;
                pre_tag="0";
              }*/

        contactList = new ArrayList<HashMap<String, String>>();

        list1=(ListView)findViewById(R.id.list);

        // Calling async task to get json
        new GetContacts().execute();
    }

    /**
     * Async task class to get json by making HTTP call
     * */
    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(all_reports.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {

                 // Creating service handler class instance
                 ServiceHandler sh = new ServiceHandler();

                 // Making a request to url and getting response
                 jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);

                 Log.d("Response: ", "> " + jsonStr);

                 if (jsonStr != null) {
                     try {
                         JSONObject jsonObj = new JSONObject(jsonStr);

                         // Getting JSON Array node
                         ibeto = jsonObj.getJSONArray(TAG_IBETO);

                         // looping through All Contacts
                         for (int i = 0; i < ibeto.length(); i++) {
                             JSONObject c = ibeto.getJSONObject(i);

                             id = c.getString(TAG_ID);
                             name = c.getString(TAG_NAME);
                             gender = c.getString(TAG_GENDER);
                             age = c.getString(TAG_AGE);
                             mdate = c.getString(TAG_MDATE);
                             guardian = c.getString(TAG_GUARDIAN);
                             image_path = c.getString(TAG_IMAGE_PATH);
                             maddress = c.getString(TAG_MADDRESS);
                             mstate = c.getString(TAG_MSTATE);
                             mcity = c.getString(TAG_MCITY);
                             htype = c.getString(TAG_HTYPE);
                             hcolor = c.getString(TAG_HCOLOR);
                             complexion = c.getString(TAG_COMPLEXION);
                             body = c.getString(TAG_BODY);
                             mark = c.getString(TAG_MARK);
                             rname = c.getString(TAG_RNAME);
                             relationship = c.getString(TAG_RELATIONSHIP);
                             contact = c.getString(TAG_CONTACT);
                             raddress = c.getString(TAG_RADDRESS);
                             rstate = c.getString(TAG_RSTATE);
                             rcity = c.getString(TAG_RCITY);


                        /*// Phone node is JSON Object
                        JSONObject phone = c.getJSONObject(TAG_PHONE);
                        String mobile = phone.getString(TAG_PHONE_MOBILE);
                        String home = phone.getString(TAG_PHONE_HOME);
                        String office = phone.getString(TAG_PHONE_OFFICE);*/

                             // tmp hashmap for single contact
                             HashMap<String, String> list = new HashMap<String, String>();

                             // adding each child node to HashMap key => value
                             //list.put(TAG_ID, id);
                             list.put(TAG_ID, id);
                             list.put(TAG_NAME, name);
                             list.put(TAG_GENDER, gender);
                             list.put(TAG_AGE, age);
                             list.put(TAG_MDATE, mdate);
                             list.put(TAG_GUARDIAN, guardian);
                             list.put(TAG_IMAGE_PATH, image_path);
                             list.put(TAG_MADDRESS, maddress);
                             list.put(TAG_MSTATE, mstate);
                             list.put(TAG_MCITY, mcity);
                             list.put(TAG_HTYPE, htype);
                             list.put(TAG_HCOLOR, hcolor);
                             list.put(TAG_COMPLEXION, complexion);
                             list.put(TAG_BODY, body);
                             list.put(TAG_RNAME, rname);
                             list.put(TAG_RELATIONSHIP, relationship);
                             list.put(TAG_CONTACT, contact);
                             list.put(TAG_RADDRESS, raddress);
                             list.put(TAG_RSTATE, rstate);
                             list.put(TAG_RCITY, rcity);

                             // adding contact to contact list
                             contactList.add(list);
                         }
                     } catch (JSONException e) {
                         e.printStackTrace();
                     }
                 } else {
                     Log.e("ServiceHandler", "Couldn't get any data from the url");
                 }



            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            // TODO Auto-generated method stub
            show();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button_next, so long
        // as you specify a parent activity in AndroidManifest.xml.
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button_next, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home: {// app icon in action bar clicked; goto parent activity.
                Intent parentIntent1 = new Intent(this, MyActivity.class);
                startActivity(parentIntent1);
                this.finish();
                return true;
            }
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void show()
    {
        if(jsonStr==null){
            Toast.makeText(getApplicationContext(), "Unable to Load data check connection", Toast.LENGTH_SHORT).show();

        }



        // Getting adapter by passing xml data ArrayList
        adapter=new LazyAdapter(all_reports.this, contactList);
        list1.setAdapter(adapter);


        // Listview on item click listener
        list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> a, View v, int position, long id) {

                HashMap<String, String> song = new HashMap<String, String>();
                song = contactList.get(position);
                //Toast.makeText(getApplicationContext(), song.get("name"), Toast.LENGTH_SHORT).show();



                // Starting single contact activity
                Intent in = new Intent(getApplicationContext(),reports_detail.class);
                in.putExtra(TAG_NAME, song.get("name"));
                in.putExtra(TAG_GENDER, song.get("gender"));
                in.putExtra(TAG_AGE, song.get("age"));
                in.putExtra(TAG_MDATE, song.get("mdate"));
                in.putExtra(TAG_GUARDIAN, song.get("guardian"));
                in.putExtra(TAG_IMAGE_PATH, song.get("image_path"));
                in.putExtra(TAG_MADDRESS, song.get("maddress"));
                in.putExtra(TAG_MSTATE, song.get("mstate"));
                in.putExtra(TAG_MCITY, song.get("mcity"));
                in.putExtra(TAG_HTYPE, song.get("htype"));
                in.putExtra(TAG_HCOLOR, song.get("hcolor"));
                in.putExtra(TAG_COMPLEXION, song.get("complexion"));
                in.putExtra(TAG_BODY, song.get("body"));
                in.putExtra(TAG_RNAME, song.get("rname"));
                in.putExtra(TAG_RELATIONSHIP, song.get("relationship"));
                in.putExtra(TAG_CONTACT, song.get("contact"));
                in.putExtra(TAG_RADDRESS, song.get("raddress"));
                in.putExtra(TAG_RSTATE, song.get("rstate"));
                in.putExtra(TAG_RCITY, song.get("rcity"));


                startActivity(in);
               // finish();*/
            }
        });
    }



}
