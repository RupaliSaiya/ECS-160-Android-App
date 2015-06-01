package com.rau.friendships;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ActionMenuView;
import android.widget.EditText;
import android.widget.Toast;


public class Register extends Activity {

    // database helper
    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void onRegisterClick(View view){
        if (view.getId() == R.id.registerBT){

            EditText username = (EditText)findViewById(R.id.usernameET);
            EditText email = (EditText)findViewById(R.id.emailET);
            EditText password1 = (EditText)findViewById(R.id.passwordET);
            EditText password2 = (EditText)findViewById(R.id.confirmpasswordET);

            String str_username = username.getText().toString();
            String str_email = email.getText().toString();
            String str_password1 = password1.getText().toString();
            String str_password2 = password2.getText().toString();

            // check that passwords match
            if (!str_password1.equals(str_password2)){
                String message = "Passwords do NOT match!";
                Toast pass = Toast.makeText(Register.this, message, Toast.LENGTH_SHORT);
                pass.show();
            }

            // passwords match, march on
            else{
                User user = new User();
                user.setUsername(str_username);
                user.setEmail(str_email);
                user.setPassword(str_password1);

                helper.insertUser(user);
            }
        }
    } // end onRegisterClick

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

}
