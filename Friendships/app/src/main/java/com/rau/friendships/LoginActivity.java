package com.rau.friendships;


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

// networking stuff
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.VolleyError;



public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /** All button calls/activities go here **/

    //Called when the user clicks the [ Sign Up ] button
    public void signUp(View view) {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

    //Called when the user clicks the [ Login ] button
    public void login(View view){
        /**
         * This is really basic code for the login activity
         * All this does is direct the user to the welcome
         * page when hitting the button.
         *
         * We'll need to:
         * 1) capture user name and password and compare it
         *    to the database
         * 2) throw errors/messages if user not found, etc.
         */
        Intent intent = new Intent(this, welcome.class);
        startActivity(intent);
    }

    //Called when the user clicks the [ Forgot Password ] button
    public void forgotPassword(View view){
        // code goes here
    }

    //Called when the user checks the [ Remember Me ] box
    /** [ Remember Me ] check box function goes here **/



}
