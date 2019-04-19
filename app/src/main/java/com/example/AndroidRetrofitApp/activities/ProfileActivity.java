package com.example.AndroidRetrofitApp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;


import com.example.AndroidRetrofitApp.R;

import com.example.AndroidRetrofitApp.fragments.HomeFragment;
import com.example.AndroidRetrofitApp.fragments.SettingsFragment;
import com.example.AndroidRetrofitApp.fragments.UsersFragment;
import com.example.AndroidRetrofitApp.storage.SheredPrefManager;
//                      (27 - C)
// now we will implements BottomNavigationView.OnNavigationItemSelectedListener
// then override the needed methods

// (25 - B - step) is the creation of this Profile Activity
public class ProfileActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    //                                      (27)
    // (Step 28 ) for all Items in the bottom navigation view we need to create
    // a fragment
    //  create them as a Java classes not a whole fragment
    //                        GO CREATE PACKAGE fragments
    // (27 - A ) declare the bottom navigation view
    BottomNavigationView mBottomNavigationView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // (27 - B ) initialize the bottom navigation view
        mBottomNavigationView = findViewById(R.id.bottom_nav);

        // (27 - D ) attach our listener  to this bottom navigation view
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);

        //(30 - B)
        //  call the method that displays the fragment
        // and assign the default value to be home fragment
        displayFagment(new HomeFragment());

    }
    //                       (30)
    // (step  31) go to the Fragment_Home and design the display of the user Profile details
    //(30 - A)
    // when the profile activity loaded the home fragment
    // this method ill take the fragment and displays it depend on your choius
    private void displayFagment(Fragment mFragment){
        // to display fragment
        // replace()
        //       this method that as argument the
        //       1 -id for the container
        //       that host the fragment
        //       2 - the fragment I want to put on the screen
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.relativelayoutID, mFragment)
                .commit();
    }




    /*
     * (25 - c)
     // (step  25 - D) Go to design the activity_profile.xml
     * // Case Two: ----
     * Duplicate this code in the MainActivity.java beside the login Activity
     * and GO TO PROFILEACTIVITY.JAVA check if the user is not Logged in
     * Direct him to the MainActivity.java
     * */
    @Override
    protected void onStart() {
        super.onStart();
        // Always when you start the app get an instance of the SPM and check if the user
        // logged in first to open his profile to him
        //             add  !
        if(!SheredPrefManager.getmSheredPrefManagerInstance(this).isLoggedIn()){
            // we need to close all the existing activities before opining any fresh activity
            // why I need to close them?
            // because when the user press back we do not need to show him
            // the login activity again
            // to do this I need an Intent
            Intent mIntentObj = new Intent(this, MainActivity.class);
            // to close all the existing activities we need to do some flags in this Intent
            mIntentObj.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            // execute the opening
            startActivity(mIntentObj);
        }
    }
    /*
     *
     * The Override method from the OnNavigationItemSelectedListener
     * */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        //(30 - C)
        //  when we select a fragment from the bottom navigation
        // it will matches it with the proper ID
        Fragment mFragment = null;
        switch (menuItem.getItemId()) {
            case R.id.menu_homeID:
                //put the home fragment into the variable if the selected bottom is the home
                mFragment = new HomeFragment();
                break;
            case R.id.menu_usersID:
                mFragment = new UsersFragment();
                break;
            case R.id.menu_settingsID:
                mFragment = new SettingsFragment();
                break;
        }
        // if there is a value inside the fragment display that value
        if (mFragment != null){
            displayFagment(mFragment);
        }
        return false;
    }
}
