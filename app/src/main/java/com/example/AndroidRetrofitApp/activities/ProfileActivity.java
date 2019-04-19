package com.example.AndroidRetrofitApp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.AndroidRetrofitApp.R;
import com.example.AndroidRetrofitApp.models.User;
import com.example.AndroidRetrofitApp.storage.SheredPrefManager;

// (25 - B - step) is the creation of this Profile Activity
public class ProfileActivity extends AppCompatActivity {

    //(25 - E - 1)  declare the textView
    private TextView mTextViewForRender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //(25 - E - 2) initialize it
        mTextViewForRender = findViewById(R.id.welcomuserID);

        //(25 - E - 3)  get the saved user data to can get his name
        User mUser = SheredPrefManager.getmSheredPrefManagerInstance(ProfileActivity.this)
                .getUserFromSharedPref();


        //(25 - E - 4) display a welcome message

        mTextViewForRender.setText("Welcome Back " + mUser.getmNameUser());


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


}
