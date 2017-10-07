package com.missingones;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;

import android.graphics.BitmapFactory;
import android.graphics.drawable.StateListDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by HP-HP on 22-06-2014.
 */
public class publish_fragment extends Fragment {

    EditText name, gender, age, mdate, guardian, maddress, mcity, mark, rname, relation, contact, raddress, rcity;

    Bitmap bitmap = null;



    // Progress Dialog
    private ProgressDialog pDialog;

    JSONParser jsonParser = new JSONParser();

    // url to create new product
    private static String url_create_product = /*"http://10.0.2.2/ibeto/create_product.php"*/"http://nitishnnl.16mb.com/ibeto/create_product.php";

    // url to store image
    private String upLoadServerUri =/*"http://10.0.2.2/ibeto/uploads/uploadpicture.php"*/ "http://nitishnnl.16mb.com/ibeto/uploadpicture.php";
    private int serverResponseCode = 0;

    // JSON Node names
    private static final String TAG_SUCCESS = "success";

    String path = "";

    private ImageView imageView;
    private Button button, button_next;
    private final int SELECT_PHOTO = 1;
    private Uri mImageCaptureUri;
    private static final int PICK_FROM_CAMERA = 1;
    private static final int PICK_FROM_FILE = 2;
    private AutoCompleteTextView auto_state, auto_state2;
    private Spinner spinner1, spinner2, spinner3, spinner4;
    String value1, value2, value3, value4;
    String[] state = new String[]
            {
                    "Andaman and Nicobar Islands",
                    "Andhra Pradesh",
                    "Arunachal Pradesh",
                    "Assam",
                    "Bihar",
                    "Chandigarh",
                    "Chhattisgarh",
                    "Dadra and Nagar Haveli",
                    "Daman and Diu",
                    "Delhi",
                    "Goa",
                    "Gujarat",
                    "Haryana",
                    "Himachal Pradesh",
                    "Jammu And Kashmir",
                    "Jharkhand",
                    "Karnatka",
                    "kerla",
                    "Lakshadweep",
                    "Madhya Pradesh",
                    "Maharashtra",
                    "Manipur",
                    "Meghalaya",
                    "Mizoram",
                    "Nagaland",
                    "Odisha",
                    "Puduchery",
                    "Punjab",
                    "Rajasthan",
                    "Sikkim",
                    "Tamil Nadu",
                    "Telangana",
                    "Tripura",
                    "Uttar Pradesh",
                    "Uttrakhand",
                    "West Bengal"
            };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_publish, container, false);

       

       /* Image Upload to Image View */

        imageView = (ImageView) rootView.findViewById(R.id.imageView);
        button = (Button) rootView.findViewById(R.id.browse);

        final String[] items = new String[]{"From Camera", "From SD Card"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.select_dialog_item, items);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Select Image");
        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                if (item == 0) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File file = new File(Environment.getExternalStorageDirectory(),
                            "tmp_avatar_" + String.valueOf(System.currentTimeMillis()) + ".jpg");
                    mImageCaptureUri = Uri.fromFile(file);

                    try {
                        intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
                        intent.putExtra("return-data", true);

                        startActivityForResult(intent, PICK_FROM_CAMERA);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    dialog.cancel();
                } else {
                    Intent intent = new Intent();

                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);

                    startActivityForResult(Intent.createChooser(intent, "Complete Action Using"), PICK_FROM_FILE);
                }
            }
        });

        final AlertDialog dialog = builder.create();

        imageView = (ImageView) rootView.findViewById(R.id.imageView);

        ((Button) rootView.findViewById(R.id.browse)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

       /* Button-Browse Hover */
        StateListDrawable slDraw = new StateListDrawable();
        slDraw.addState(new int[]{android.R.attr.state_focused},
                getResources().getDrawable(R.drawable.btn_default_disabled_focused_holo_light));
        slDraw.addState(new int[]{android.R.attr.state_selected},
                getResources().getDrawable(R.drawable.btn_default_focused_holo_light));

        slDraw.addState(new int[]{android.R.attr.state_pressed},
                getResources().getDrawable(R.drawable.btn_default_pressed_holo_light));

        slDraw.addState(new int[]{},
                getResources().getDrawable(R.drawable.btn_default_normal_holo_light));
        button = (Button) rootView.findViewById(R.id.browse);
        button.setBackgroundDrawable(slDraw);



       /* Button-Next Hover */
        StateListDrawable slDraw1 = new StateListDrawable();
        slDraw1.addState(new int[]{android.R.attr.state_focused},
                getResources().getDrawable(R.drawable.apptheme_btn_default_disabled_holo_light));
        slDraw1.addState(new int[]{android.R.attr.state_selected},
                getResources().getDrawable(R.drawable.apptheme_btn_default_focused_holo_light));

        slDraw1.addState(new int[]{android.R.attr.state_pressed},
                getResources().getDrawable(R.drawable.apptheme_btn_default_pressed_holo_light));

        slDraw1.addState(new int[]{},
                getResources().getDrawable(R.drawable.apptheme_btn_default_normal_holo_light));
        button_next = (Button) rootView.findViewById(R.id.next);
        button_next.setBackgroundDrawable(slDraw1);


      /* Auto-Complete State View */
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, state);
        auto_state = (AutoCompleteTextView) rootView.findViewById(R.id.mstate);
        auto_state2 = (AutoCompleteTextView) rootView.findViewById(R.id.rstate);
        auto_state.setThreshold(2);
        auto_state2.setThreshold(2);
        auto_state.setAdapter(adapter1);
        auto_state2.setAdapter(adapter1);

       /* Spinners */
        spinner1 = (Spinner) rootView.findViewById(R.id.spinner1);
        spinner2 = (Spinner) rootView.findViewById(R.id.spinner2);
        spinner3 = (Spinner) rootView.findViewById(R.id.spinner3);
        spinner4 = (Spinner) rootView.findViewById(R.id.spinner4);

        // Spinner Drop down elements


        // Spinner click listener
        spinner1.setOnItemSelectedListener((new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                value1 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        }));

        spinner2.setOnItemSelectedListener((new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                value2 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        }));

        spinner3.setOnItemSelectedListener((new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                value3 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        }));

        spinner4.setOnItemSelectedListener((new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                value4 = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        }));













       /* Sending data to php mysql */

        // Edit Text
        name = (EditText) rootView.findViewById(R.id.name);
        gender = (EditText) rootView.findViewById(R.id.gender);
        age = (EditText) rootView.findViewById(R.id.age);
        mdate = (EditText) rootView.findViewById(R.id.mdate);
        guardian = (EditText) rootView.findViewById(R.id.guardian);
        maddress = (EditText) rootView.findViewById(R.id.maddress);
        mcity = (EditText) rootView.findViewById(R.id.mcity);
        mark = (EditText) rootView.findViewById(R.id.mark);
        rname = (EditText) rootView.findViewById(R.id.rname);
        relation = (EditText) rootView.findViewById(R.id.relation);
        contact = (EditText) rootView.findViewById(R.id.contact);
        raddress = (EditText) rootView.findViewById(R.id.raddress);
        rcity = (EditText) rootView.findViewById(R.id.rcity);

        button_next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {





                    // creating new product in background thread
                    new send().execute();





            }
        });

        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) return;




        if (requestCode == PICK_FROM_FILE) {
            mImageCaptureUri = data.getData();
            path = getRealPathFromURI(mImageCaptureUri); //from Gallery

            if (path == null)
                path = mImageCaptureUri.getPath(); //from File Manager

            if (path != null)
                bitmap = BitmapFactory.decodeFile(path);
        } else {
            path = mImageCaptureUri.getPath();
            bitmap = BitmapFactory.decodeFile(path);
        }

        imageView.setImageBitmap(bitmap);
    }

    public String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getActivity().getContentResolver().query(contentUri, proj, null, null, null);

        if (cursor == null) return null;

        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

        cursor.moveToFirst();

        return cursor.getString(column_index);
    }


    /**
     * Scale the photo down and fit it to our image views.
     * <p/>
     * "Drastically increases performance" to set images using this technique.
     * Read more:http://developer.android.com/training/camera/photobasics.html
     */
    private void setFullImageFromFilePath(String imagePath) {
        // Get the dimensions of the View
        int targetW = imageView.getWidth();
        int targetH = imageView.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imagePath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(imagePath, bmOptions);
        imageView.setImageBitmap(bitmap);
    }


    /**
     * Background Async Task to Create new product
     */
    class send extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Submitting Data");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }


        String mname = name.getText().toString();
        String gen = gender.getText().toString();
        String mage = age.getText().toString();
        String date = mdate.getText().toString();
        String guard = guardian.getText().toString();
        String add = maddress.getText().toString();
        String state = auto_state.getText().toString();
        String city = mcity.getText().toString();
        String htype = value1;
        String hcolor = value2;
        String complex = value3;
        String body = value4;
        String imark = mark.getText().toString();
        String rdname = rname.getText().toString();
        String relationship = relation.getText().toString();
        String rcontact = contact.getText().toString();
        String idmark = mark.getText().toString();
        String radd = raddress.getText().toString();
        String rstate = auto_state2.getText().toString();
        String rcit = rcity.getText().toString();


        /**
         * Creating product
         */

        protected String doInBackground(String... args) {


            /*Toast.makeText(getActivity().getApplicationContext(), mname, Toast.LENGTH_SHORT).show();
            Toast.makeText(getActivity().getApplicationContext(), htype, Toast.LENGTH_SHORT).show();
            Toast.makeText(getActivity().getApplicationContext(), state, Toast.LENGTH_SHORT).show();
*/
            try {
                // Building Parameters
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("name", mname));
                params.add(new BasicNameValuePair("gender", gen));
                params.add(new BasicNameValuePair("age", mage));
                params.add(new BasicNameValuePair("mdate", date));
                params.add(new BasicNameValuePair("guardian", guard));
                //params.add(new BasicNameValuePair("", description));
                params.add(new BasicNameValuePair("maddress", add));
                params.add(new BasicNameValuePair("mstate", state));
                params.add(new BasicNameValuePair("mcity", city));
                params.add(new BasicNameValuePair("htype", htype));
                params.add(new BasicNameValuePair("hcolor", hcolor));
                params.add(new BasicNameValuePair("complexion", complex));
                params.add(new BasicNameValuePair("body", body));
                params.add(new BasicNameValuePair("mark", idmark));
                params.add(new BasicNameValuePair("rname", rdname));
                params.add(new BasicNameValuePair("relationship", relationship));
                params.add(new BasicNameValuePair("contact", rcontact));
                params.add(new BasicNameValuePair("raddress", radd));
                params.add(new BasicNameValuePair("rstate", rstate));
                params.add(new BasicNameValuePair("rcity", rcit));

                new Thread(new Runnable() {
                    public void run() {

                        uploadFile();

                    }
                }).start();


                // getting JSON Object
                // Note that create product url accepts POST method
                JSONObject json = jsonParser.makeHttpRequest(url_create_product,
                        "POST", params);

                // check log cat fro response
                Log.d("Create Response", json.toString());

                // check for success tag

                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // successfully created product
                    Intent i = new Intent(getActivity().getApplicationContext(), publish_final.class);
                    startActivity(i);

                    // closing this screen
                    //this.getfinish();
                } else {
                    // failed to create product
                    Toast.makeText(getActivity().getApplicationContext(), "All Fields were Empty", Toast.LENGTH_LONG).show();

                    Intent i = new Intent(getActivity(),MyActivity.class);
                    startActivity(i);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * *
         */
        protected void onPostExecute(String file_url) {

            // dismiss the dialog once done
            pDialog.dismiss();

        }

    }


    // upload image file function

       InputStream is;
        private void uploadFile () {

            ByteArrayOutputStream bao = new ByteArrayOutputStream();

            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, bao);

            byte [] ba = bao.toByteArray();

            String ba1=Base64.encodeBytes(ba);

            ArrayList<NameValuePair> nameValuePairs = new

                    ArrayList<NameValuePair>();

            nameValuePairs.add(new BasicNameValuePair("image",ba1));
            String mname = name.getText().toString();
            nameValuePairs.add(new BasicNameValuePair("name",mname));


            Log.v("log_tag", System.currentTimeMillis()+".jpg");
            try {

                HttpClient httpclient = new DefaultHttpClient();

                HttpPost httppost = new HttpPost(upLoadServerUri);

                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                HttpResponse response = httpclient.execute(httppost);

                HttpEntity entity = response.getEntity();

                is = entity.getContent();
                Log.v("log_tag", "In the try Loop" );
            }catch(Exception e){
                Log.v("log_tag", "Error in http connection "+e.toString());
            }


        }






}



