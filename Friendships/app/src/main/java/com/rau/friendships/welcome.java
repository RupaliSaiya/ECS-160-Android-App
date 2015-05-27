package com.rau.friendships;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class welcome extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_welcome, menu);
        return true;
    }

    // Menu actions
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_friends_list) {
            goToFriendsList();
            return true;
        }else if (id == R.id.menu_profile) {
            goToProfile();
            return true;
        }else if (id == R.id.menu_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /** Define all of the menu actions here **/

    // friendslist
    private void goToFriendsList()
    {
        Intent intent = new Intent(this, FriendsList.class);
        startActivity(intent);
    }

    // profile
    private void goToProfile()
    {
        Intent intent = new Intent(this,Profile.class);
        startActivity(intent);
    }

    // logout
    private void logout()
    {

        finish();
    }



    /** Button actions go here **/

    //When user clicks on [ Create Delivery ]
    public void createDelivery(View view){
        Intent intent = new Intent(this, CreateDelivery.class);
        startActivity(intent);
    }

    //When user clicks on [ View Deliveries ]
    public void viewDeliveries(View view){
        Intent intent = new Intent(this, ViewDeliveries.class);
        startActivity(intent);
    }


    //When user clicks on [ Add Friend ]
    public void addFriend(View view){
        Intent intent = new Intent(this, AddFriend.class);
        startActivity(intent);
    }

}
