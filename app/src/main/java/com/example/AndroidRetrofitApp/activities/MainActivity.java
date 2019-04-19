package com.example.AndroidRetrofitApp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.AndroidRetrofitApp.R;
import com.example.AndroidRetrofitApp.api.RetrofitClient;
import com.example.AndroidRetrofitApp.models.DefultResponse;
import com.example.AndroidRetrofitApp.storage.SheredPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//     THis is our SIGN UP activity

/*
 *                   (4)
 *         (Implement & Override)
 * Implement the on click listener to use it
 * then it will force you to override specific method
 *
 * */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /*
    *                   (1)
    * first thing after creating the XML
    * Declare and Create all the edit text on the screen
    * */
    private EditText editTextEmail, editTextPassword, editTextName, editTextSchool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
         *                   (2)
         * Second is to initialize and get reference to the views on the screen
         * */
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextName = findViewById(R.id.editTextName);
        editTextSchool = findViewById(R.id.editTextSchool);
        /*
         *                   (3)
         * Attach Click Listeners to the buttons on the screen
         * */
        findViewById(R.id.buttonSignUp).setOnClickListener(this);
        findViewById(R.id.textViewLogin).setOnClickListener(this);
    }
    /*
     * (25 - c)
     // (step  25 - D) Go to design the activity_profile.xml
     * // Case Two: ----
     * Duplicate this code in the main activity beside the login Activity
     * */
    @Override
    protected void onStart() {
        super.onStart();
        // Always when you start the app get an instance of the SPM and check if the user
        // logged in first to open his profile to him
        if(SheredPrefManager.getmSheredPrefManagerInstance(this).isLoggedIn()){
            // we need to close all the existing activities before opining any fresh activity
            // why I need to close them?
            // because when the user press back we do not need to show him
            // the login activity again
            // to do this I need an Intent
            Intent mIntentObj = new Intent(this, ProfileActivity.class);
            // to close all the existing activities we need to do some flags in this Intent
            mIntentObj.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            // execute the opening
            startActivity(mIntentObj);
        }
    }
    /*
     *                   (6)
     * (Step (7) will be in the build.gradle file)
     * Create the sign up method which will
     *      A- receive the data from the Edit Texts
     *      B- implement my constrains on it like password length (Check Validation)
     *      C- Doing the Api Registration Call
     * */
    private void userSignUp() {

        //      (6 - A)- receive the data from the Edit Texts
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String name = editTextName.getText().toString().trim();
        String school = editTextSchool.getText().toString().trim();


        // (6 - B)- implement my constrains on it like password length (Check Validation)
        //email.isEmpty()  check if it's empty
        if (email.isEmpty()) {
            editTextEmail.setError("Email is required");
            // direct the focus to the field in the run time
            editTextEmail.requestFocus();
            return;
        }
        // !Patterns.EMAIL_ADDRESS.matcher(email).matches()
        // check for the email form mane@example.com
        // this line (Patterns.EMAIL_ADDRESS.matcher(email).matches()) means
        // if the email matshes that pattren so we have to write not (!)
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Enter a valid email");
            editTextEmail.requestFocus();
            // this return to stop the execution
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Password required");
            editTextPassword.requestFocus();
            // this return to stop the execution
            return;
        }
        // password.length() < 6
        // check if the password less than 6 digits
        // if you want to check confirm password u can use
        // password.equals()
        if (password.length() < 6) {
            editTextPassword.setError("Password should be at least 6 character long");
            editTextPassword.requestFocus();
            // this return to stop the execution
            return;
        }

        if (name.isEmpty()) {
            editTextName.setError("Name required");
            editTextName.requestFocus();
            // this return to stop the execution
            return;
        }

        if (school.isEmpty()) {
            editTextSchool.setError("School required");
            editTextSchool.requestFocus();
            // this return to stop the execution
            return;
        }
        /*
        *                       (10)
        *           (Create the call)
        * (Step (11) Go to androidmanifist file ) for the internet permission
        * Actual API Call For The User Registration
        *   1- Send the POST HTTP request
        *   2- Register User
        *
        * getmRetrofitClientInstance : this is an oject from the retrofit class
        *                              and we use it to make the call
        * getAPI :                     this interface has the basic definition the the endpoint
        *                              and the required parameters the api allow or need
        * createuser :                 this is the endpoint attached to the POST Request
        * createuser(email, password, name, school) I pass to this method the data I received from the
        *                                           editText in step (6)
        * */
        Call<DefultResponse> mCall = RetrofitClient
                .getmRetrofitClientInstance()
                .getAPI()
                .createuser(email, password, name, school);

        //                             (15)
        // (Step 16 ) go and create the LoginActivity then DESIGN THE LAYOUT.XML FILE
        // Modify the Call Type<DefultResponse> Above then use enqueue()
        // enqueue : we need this method to execute the http call
        // write inside new then space then  ctrl space to override the methods
        mCall.enqueue(new Callback<DefultResponse>() {
            @Override
            public void onResponse(Call<DefultResponse> call, Response<DefultResponse> response) {
                if (response.code() == 201){
                    // receiving the JSON response object coming from the API Call
                    // Auto Parsed and store it inside an Instance of the  Model class
                    // that I created to know the specific data I need from the parsed object
                    DefultResponse mDefultResponseInstance = response.body();
                    String mStringResponseMessage = mDefultResponseInstance.getmMesssageResponse();
                    Toast.makeText(MainActivity.this, mStringResponseMessage, Toast.LENGTH_LONG).show();
                } else if (response.code() == 422){
                    // I Have to Know what this error code means and write the message manually
                    Toast.makeText(MainActivity.this, "User Already Exist", Toast.LENGTH_LONG).show();
                }


            }

            @Override
            public void onFailure(Call<DefultResponse> call, Throwable t) {

            }
        });

    }

    /*
     *
     * The Override method from the on click listener
     * */
    @Override
    public void onClick(View v) {
        /*
         *                   (5)
         * Create switch case the receive every button ID and perform the right action
         * */
        switch (v.getId()) {
            case R.id.buttonSignUp:
                // make sign up for the user
                userSignUp();
                break;
            case R.id.textViewLogin:
                //                      (22)
                // (23) go to login activity and modify this same code to swap between the two activities
                // this button for opening up the login activity
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
    }
}
