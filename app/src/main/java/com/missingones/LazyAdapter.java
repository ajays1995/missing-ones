package com.missingones;



import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;


public class LazyAdapter extends BaseAdapter {
    
	
	
    private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater=null;
    //public ImageLoader imageLoader;
    
    public LazyAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //imageLoader=new ImageLoader(activity.getApplicationContext());
    }

   // Typeface face = Typeface.createFromAsset(activity.getAssets(),"DroidSansRegionalAAD.ttf");
	public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }
    
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
        	
            vi = inflater.inflate(R.layout.list_item, parent,false);

        TextView name1 = (TextView)vi.findViewById(R.id.name); // title
        TextView gender1 = (TextView)vi.findViewById(R.id.gender); // artist name
        TextView age1 = (TextView)vi.findViewById(R.id.age); // duration
        TextView location1 = (TextView)vi.findViewById(R.id.location);
        ImageView thumb_image=(ImageView)vi.findViewById(R.id.list_image);
        HashMap<String, String> song = new HashMap<String, String>();
        song = data.get(position);
        
        // Setting all values in listview
        name1.setText(song.get("name"));
        gender1.setText(song.get("gender"));
        String age;
        if(song.get("age").equals("")) {
            age="Age";
            }
        else {
              age = song.get("age") + " Years";
            }
        age1.setText(age);
        location1.setText(song.get("mstate"));
        try {

            URL url = new URL(song.get("image_path"));
            HttpGet httpRequest = null;

            httpRequest = new HttpGet(url.toURI());

            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response = (HttpResponse) httpclient
                    .execute(httpRequest);

            HttpEntity entity = response.getEntity();
            BufferedHttpEntity b_entity = new BufferedHttpEntity(entity);
            InputStream input = b_entity.getContent();

            Bitmap bitmap = BitmapFactory.decodeStream(input);


            thumb_image.setImageBitmap(bitmap);

        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        } catch (MalformedURLException e) {
            Log.e("log", "bad url");
        } catch (IOException e) {
            Log.e("log", "io error");
        }


        return vi;
    }
}