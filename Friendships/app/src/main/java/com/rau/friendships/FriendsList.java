package com.rau.friendships;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class FriendsList extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_list);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_friends_list, menu);
        return true;
    }

    // Menu actions
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_profile) {
            goToProfile();
            return true;
        }else if (id == R.id.menu_logout) {
            logout();
            return true;
        }else if(id == R.id.menu_dashboard){
            goToDashboard();
            return true;
        }else if(id == R.id.menu_notifications){
            goToNotifications();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /** Define all of the menu actions here **/

    // profile
    private void goToProfile() {
        Intent intent = new Intent(this,Profile.class);
        startActivity(intent);
    }

    // dashboard
    private void goToDashboard(){
        Intent intent = new Intent(this, welcome.class);
        startActivity(intent);
    }

    // notifications
    private void goToNotifications(){
        Intent intent = new Intent(this, Notifications.class);
        startActivity(intent);
    }

    // logout
    private void logout() {
        finish();
    }
}
