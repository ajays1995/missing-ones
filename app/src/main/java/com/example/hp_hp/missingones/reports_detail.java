package com.example.hp_hp.missingones;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.missingones.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;


public class reports_detail extends ActionBarActivity  {
	
	// JSON node keys
    String name, gender, age, mdate, guardian, image_path, maddress, mstate, mcity, htype, hcolor, complexion, body,
            mark, rname, relationship, contact, raddress, rstate, rcity;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        
        // getting intent data
        Intent in = getIntent();

        // Get JSON values from previous intent
        name = in.getStringExtra("name");
        gender = in.getStringExtra("gender");
        age = in.getStringExtra("age");
        mdate = in.getStringExtra("mdate");
        guardian = in.getStringExtra("guardian");
        image_path = in.getStringExtra("image_path");
        maddress = in.getStringExtra("maddress");
        mstate = in.getStringExtra("mstate");
        mcity = in.getStringExtra("mcity");
        htype = in.getStringExtra("htype");
        hcolor = in.getStringExtra("hcolor");
        complexion = in.getStringExtra("complexion");
        body = in.getStringExtra("body");
        mark = in.getStringExtra("mark");
        rname = in.getStringExtra("rname");
        relationship = in.getStringExtra("relationship");
        contact = in.getStringExtra("contact");
        raddress = in.getStringExtra("raddress");
        rstate = in.getStringExtra("rstate");
        rcity = in.getStringExtra("rcity");

        getSupportActionBar().setTitle(name);

        TextView name1, gender1, age1, mdate1, guardian1, maddress1,htype1, hcolor1, complexion1, body1,
                mark1, rname1, relationship1, contact1, raddress1;

        ImageView image_path1;

        name1 = (TextView) findViewById(R.id.name);
        gender1 = (TextView) findViewById(R.id.gender);
        age1 = (TextView) findViewById(R.id.age);
        mdate1 = (TextView) findViewById(R.id.mdate1);
        guardian1 = (TextView) findViewById(R.id.guardian1);
        maddress1 = (TextView) findViewById(R.id.maddress1);
        htype1 = (TextView) findViewById(R.id.htype1);
        hcolor1 = (TextView) findViewById(R.id.hcolor1);
        complexion1 = (TextView) findViewById(R.id.complexion1);
        body1 = (TextView) findViewById(R.id.body1);
        mark1 = (TextView) findViewById(R.id.ident_mark1);
        rname1 = (TextView) findViewById(R.id.rname);
        relationship1 = (TextView) findViewById(R.id.relationship1);
        contact1 = (TextView) findViewById(R.id.contact1);
        raddress1 = (TextView) findViewById(R.id.raddress1);


        image_path1 =(ImageView) findViewById(R.id.list_image);



        name1.setText(name);
        gender1.setText(gender);
        age1.setText(age);
        mdate1.setText(mdate);
        guardian1.setText(guardian);
        maddress1.setText(maddress);
        htype1.setText(htype);
        hcolor1.setText(hcolor);
        complexion1.setText(complexion);
        body1.setText(body);
        mark1.setText(mark);
        relationship1.setText(relationship);
        contact1.setText(contact);
        raddress1.setText(raddress);

        try {

            URL url = new URL(image_path);
            HttpGet httpRequest = null;

            httpRequest = new HttpGet(url.toURI());

            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response = (HttpResponse) httpclient
                    .execute(httpRequest);

            HttpEntity entity = response.getEntity();
            BufferedHttpEntity b_entity = new BufferedHttpEntity(entity);
            InputStream input = b_entity.getContent();

            Bitmap bitmap = BitmapFactory.decodeStream(input);


            image_path1.setImageBitmap(bitmap);

        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        } catch (MalformedURLException e) {
            Log.e("log", "bad url");
        } catch (IOException e) {
            Log.e("log", "io error");
        }


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
                Intent parentIntent1 = new Intent(this,all_reports.class);
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

