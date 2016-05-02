package com.wingnity.jsonparsingtutorial;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;



import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



//import com.google.gson.Gson;

/**
 * Created by lenovo on 4/6/2015.
 */
public class Login extends Activity implements View.OnClickListener,Serializable {

    private EditText user, pass;
    private Button mSubmit, mRegister;
    int success;
    static InputStream is = null;
    TextView error;
    LinearLayout layout;
    private ProgressDialog pDialog;
    // JSON parser class
    //  JSONParser jsonParser = new JSONParser();
    JSONObject objjson = new JSONObject();
    String jsonstring = null;
    // php login script location:
    private ClipboardManager myClipboard;
    private ClipData myClip;
    ProgressBar mProgressBar;

    // testing on Emulator:
    private static final String LOGIN_URL = "http://192.168.1.101:8081/bloodapp/user/login";


    // JSON element ids from repsonse of php script:
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dummy1);


        // setup input fields
        user = (EditText) findViewById(R.id.username);
        pass = (EditText) findViewById(R.id.password);
        mProgressBar = (ProgressBar) findViewById(R.id.loadingProgressBar);

        // setup buttons
        mSubmit = (Button) findViewById(R.id.login);

        mProgressBar.setVisibility(View.GONE);
        // register listeners
        mSubmit.setOnClickListener(this);
       /* findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                final String email = user.getText().toString();
                if (!isValidEmail(email)) {
                    user.setError("Invalid Email");
                }

                final String passw = pass.getText().toString();
                if (!isValidPassword(passw)) {
                    pass.setError("Invalid Password");
                }

            }
        });*/
    }
    //validating email id
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // validating password with retype password
    private boolean isValidPassword(String passw) {
        if (passw != null && passw.length() > 6) {
            return true;
        }
        return false;
    }



    public void onClick(View v) {
        ProgressDialog dialog_network;

        switch (v.getId()) {

            case R.id.login:
                Map<String, String> networkDetails = getConnectionDetails();
                if (networkDetails.isEmpty()) {

                    dialog_network = new ProgressDialog(Login.this);
                    dialog_network.setMessage(" Sorry,Please Connect internet ");
                    dialog_network.setTitle("No Internet Connection/wifi");
                    dialog_network.show();
                    dialog_network.setCancelable(true);
                    // dialog_network.
                } else {

                    // mProgressBar.setVisibility(View.VISIBLE);

                    new AttemptLogin().execute(LOGIN_URL);
                    break;


                }
           /* case R.id.container:
                Intent i = new Intent(this,  MainActivity.class);
                startActivity(i);
                break;*/

            default:
                break;
        }
    }

    class AttemptLogin extends AsyncTask<String, String, String> {

        boolean failure = false;
        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            // layout.setVisibility(View.VISIBLE);
            super.onPreExecute();

            dialog = new ProgressDialog(Login.this);
            dialog.setMessage(" please wait");
            dialog.setTitle("Login progress");
            dialog.show();

            dialog.setCancelable(false);



        }

        @Override
        protected String doInBackground(String... urls) {
            // Check for success tag
            // int success;
            String responseText = null;
            String email = user.getText().toString();

            String password = pass.getText().toString();

            try {

                JSONObject jsonObj = new JSONObject();
                jsonObj.put("email", email);
                jsonObj.put("password", password);

                // Log.d("request!", "starting");
                // getting product details by making HTTP request
                Log.d("request!", "starting");
                // getting product details by making HTTP request
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPostreq = new HttpPost(urls[0]);

                // check your log for json response
                // Log.d("Login attempt", json.toString());
                System.out.println("near jsonObj.toString()" + jsonObj.toString());

                StringEntity se = new StringEntity(jsonObj.toString());
                se.setContentType("application/json");
                // se.setContentEncoding(new
                // BasicHeader(HTTP.CONTENT_TYPE,"application/json;charset=UTF-8"));

                httpPostreq.setEntity(se);

                @SuppressWarnings("deprecation")
                HttpResponse httpResponse = httpClient.execute(httpPostreq);
                int code = httpResponse.getStatusLine().getStatusCode();
                System.out.println(code);
                System.out.println("near httprespone" + httpResponse);
                try {
                    responseText = EntityUtils.toString(httpResponse
                            .getEntity());
                    System.out.println("value of responetext" + responseText);
                /*   *//* String res=responseText.toString();
                    res= res.replaceAll("\\s+","");
                    if(res.equals("1"))
                        error.setText("Correct Username or Password");
                    else
                        error.setText("Sorry!! Incorrect Username or Password");*//*
                } catch (Exception e) {*/
                    try {
                        Thread.sleep(3000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                    Log.i("Parse Exception", e + "response got");
                } catch (IOException e) {
                    e.printStackTrace();
                }

				/*
				 * JSONObject json = jsonParser.makeHttpRequest ( LOGIN_URL,
				 * "POST", params);
				 */
                // check your log for json response
               /* JSONObject json = new JSONObject(responseText);
                System.out.println(json
                        + "response from json object...............");
*/
               /* Gson gson = new Gson();
                LoginBean login = gson.fromJson(responseText, LoginBean.class);
                Log.d("out put =========", login.user.userId);

                String user = json.getString("user");
                System.out.println(user);


//String user=json.getString("user");*/
              //  Log.d("register atempt", json.toString());
              /*  String acc1 = login.oauth.access_token.toString();
                String uid = login.user.userId.toString();
                String fname = login.user.firstName.toString();
                String lname = login.user.lastName.toString();
                String uname = login.user.userName.toString();*/
                SharedPreferences settings;
                SharedPreferences.Editor editor;
                Context context;
                settings = getSharedPreferences("adam",
                        Context.MODE_PRIVATE);
                editor = settings.edit();


                editor.commit();

                if (code == 200) {
                    System.out.println("inside if loop");
                   // Log.d("Login Successful!", json.toString());

                    Intent i = new Intent(Login.this, MainActivity.class);
                    // Toast.makeText(getApplicationContext(), "Login Successful!.", Toast.LENGTH_SHORT).show();
              //      String acc = login.oauth.access_token.toString();
                  //  System.out.println("new access token:=" + acc);
                  //  String name = login.user.firstName.toString();

                    /*i.putExtra("username", name);
                    i.putExtra("acc_token", acc);*/
                    finish();
                    startActivity(i);

                  //  return json.getString(TAG_MESSAGE);
                } else if (code == 404) {

                       /* AlertDialog alert =  new  AlertDialog.Builder(Login.this).setPositiveButton("Ok",null).create();
                        alert.setTitle("Login faliure");
                        alert.setMessage("Invalid username and password");
                        alert.show();
                        alert.onBackPressed();*/

                    ProgressDialog dialog_loginerror;
                    dialog_loginerror = new ProgressDialog(Login.this);
                    dialog_loginerror.setMessage(" Invalid username and password ");
                    dialog_loginerror.setTitle("LOgin Faliure");
                    dialog_loginerror.show();
                    dialog_loginerror.setCancelable(true);
                   // Log.d("Login Falure!", json.getString(TAG_MESSAGE));
                    Toast.makeText(getApplicationContext(), "Login failure!.", Toast.LENGTH_SHORT).show();

                   // return json.getString(TAG_MESSAGE);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            return null;
        }


        // After completing background task Dismiss the progress dialog

        protected void onPostExecute(String result) {
            // dismiss the dialog once product deleted
            // pDialog.dismiss();

            System.out.println("inside dopost method");
            super.onPostExecute(result);
            dialog.cancel();
            //adapter.notifyDataSetChanged();
            //  layout.setVisibility(View.GONE);
     /* mProgressBar.setVisibility(View.VISIBLE);
            mProgressBar.setVisibility(View.GONE);
*/
            if (result != null) {
                Toast.makeText(Login.this, result, Toast.LENGTH_LONG).show();
            }
        }
    }


    private Map<String, String> getConnectionDetails() {
        Map<String, String> networkDetails = new HashMap<String, String>();
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo wifiNetwork = connectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (wifiNetwork != null && wifiNetwork.isConnected()) {

                networkDetails.put("Type", wifiNetwork.getTypeName());
                networkDetails.put("Sub type", wifiNetwork.getSubtypeName());
                networkDetails.put("State", wifiNetwork.getState().name());
            }

            NetworkInfo mobileNetwork = connectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (mobileNetwork != null && mobileNetwork.isConnected()) {
                networkDetails.put("Type", mobileNetwork.getTypeName());
                networkDetails.put("Sub type", mobileNetwork.getSubtypeName());
                networkDetails.put("State", mobileNetwork.getState().name());
                if (mobileNetwork.isRoaming()) {
                    networkDetails.put("Roming", "YES");
                } else {
                    networkDetails.put("Roming", "NO");
                }
            }
        } catch (Exception e) {
            networkDetails.put("Status", e.getMessage());
        }
        return networkDetails;
    }



}