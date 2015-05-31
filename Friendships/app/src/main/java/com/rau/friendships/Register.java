package com.rau.friendships;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

// import helper classes
import com.rau.friendships.app.AppConfig;
import com.rau.friendships.app.AppController;
import com.rau.friendships.helper.SessionManager;
import com.rau.friendships.helper.SQLiteHandler;


import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;


// networking stuff
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.VolleyError;




public class Register extends Activity {

    private static final String TAG = Register.class.getSimpleName();
    private Button button_regs;
    private EditText input_name;
    private EditText input_email;
    private EditText input_password;
    private ProgressDialog progress_dialog;
    private SessionManager session;
    private SQLiteHandler db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        session = new SessionManager(getApplicationContext());
        db = new SQLiteHandler(getApplicationContext());

        progress_dialog = new ProgressDialog(this);
        progress_dialog.setCancelable(false);

        input_name = (EditText) findViewById(R.id.edittext_username);
        input_email = (EditText) findViewById(R.id.edittext_email);
        input_password = (EditText) findViewById(R.id.edittext_password);
        button_regs = (Button) findViewById(R.id.button_sign_up);

        // check if user is logged in
        if (session.isLoggedIn()){
            Intent intent = new Intent(Register.this, welcome.class);
            startActivity(intent);
            finish();
        }

        // register button action
        button_regs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String name = input_name.getText().toString();
                String email = input_email.getText().toString();
                String password = input_password.getText().toString();

                if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
                    registerUser(name, email, password);
                }

                else {
                    Toast.makeText(getApplicationContext(),
                            "Please enter your details!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    } // end onCreate


    private void registerUser(final String name, final String email,
                              final String password) {
        // Tag used to cancel the request
        String tag_string_req = "req_register";

        progress_dialog.setMessage("Registering ...");
        showDialog();

        StringRequest strReq = new StringRequest(Method.POST,
                AppConfig.URL_REGISTER, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Register Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    if (!error) {
                        // User successfully stored in MySQL
                        // Now store the user in sqlite
                        String uid = jObj.getString("uid");

                        JSONObject user = jObj.getJSONObject("user");
                        String name = user.getString("name");
                        String email = user.getString("email");
                        String created_at = user.getString("created_at");

                        // Inserting row in users table
                        db.addUser(name, email, uid, created_at);

                        // Launch login activity
                        Intent intent = new Intent(Register.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    } else {

                        // Error occurred in registration. Get the error
                        // message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Registration Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();
                params.put("tag", "register");
                params.put("name", name);
                params.put("email", email);
                params.put("password", password);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void showDialog() {
        if (!progress_dialog.isShowing())
            progress_dialog.show();
    }

    private void hideDialog() {
        if (progress_dialog.isShowing())
            progress_dialog.dismiss();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_register, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//    /** Button actions go here **/
//
//    //Called when the user clicks [ Submit ]
//    public void submitRegistration(View view){
//        // code goes here
//    }
}
