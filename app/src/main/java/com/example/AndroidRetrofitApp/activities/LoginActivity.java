package com.example.AndroidRetrofitApp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.AndroidRetrofitApp.R;
import com.example.AndroidRetrofitApp.api.RetrofitClient;
import com.example.AndroidRetrofitApp.models.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/*
 *                   (17 - D)
 *         (Implement & Override)
 * Implement the on click listener to use it
 * then it will force you to override specific method
 *
 * */


public class LoginActivity extends AppCompatActivity implements View.OnClickListener  {

    //                          (17)
    //   (step 18) go and create User.java  model class
    //  (17 - A) Declare and Create all the edit text on the screen
    private EditText editTextEmail;
    private EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Log.v("LoginActivity : ----", "This is OnCreate oooooooooooooooooooooooooooooooooooooooh");
        /*                      (17 - B)
         *  is to initialize and get reference to the views on the screen
         * */
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);


        /*                      (17 - C)
         * Attach Click Listeners to the buttons on the screen
         * */
        findViewById(R.id.textViewRegister).setOnClickListener(this);
        findViewById(R.id.buttonLogin).setOnClickListener(this);

    }

    /*
     *                   (17 - F)
     * (Step 18) will be create the model class User.java file)
     * Create the login method which will
     *      1- receive the data from the Edit Texts
     *      2- implement my constrains on it like password length (Check Validation)
     *      3- Doing the Api Registration Call
     * */
    private void userLogin() {

        Log.v("LoginActivity : ----", "This is UserLogin oooooooooooooooooooooooooooooooooooooooh");
        //      (17 - G - 1)- receive the data from the Edit Texts
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        // (17 - G - 2)- implement my constrains on it like password length (Check Validation)
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
//                           (21)
        //  (step 22) go to MainActivity.java
//         (21 - A)
//         (Create the call)
//         1- Send the POST HTTP request
//         2 - Register User
//
//        * getmRetrofitClientInstance : this is an object from the retrofit class
//        *                              and we use it to make the call
//        * getAPI :                     this interface has the basic definition the the endpoint
//        *                              and the required parameters the api allow or need
//          userlogin :                 this is the endpoint attached to the retrofit2.http.POST Request
//        * userlogin(email, password) I pass to this method the data I received from the
//
        Call<LoginResponse> mCall = RetrofitClient.getmRetrofitClientInstance().getAPI().userlogin(email, password);
        // (21 - B)
        // enqueue : we need this method to execute the http call
        // write inside new then space then  ctrl space to override the methods

        mCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                // this time we won't get any error code so we will parse the response
                // user an instance from the LoginResponse
                // body() will return a LoginResponse
                LoginResponse mLoginResponseinstanse = response.body();


                // check the variable mErrorResponse inside the login response
                // has an error or not if is the value false so there is no error
                // so we will add ! because !false == true or in other word
                // !mLoginResponse.isError() means if there is no error
                // mErrorResponse stores the boolean value that coming from the server
                // and this why we say above we will not have any error because we only handle
                // the error false
                if (!mLoginResponseinstanse.isError()) {
                    // save user (Shared preferences && Sqlite DB)
                    // open profile

                    Toast.makeText(LoginActivity.this, mLoginResponseinstanse.getMessage(), Toast.LENGTH_LONG).show();

                } else{

                    Toast.makeText(LoginActivity.this, mLoginResponseinstanse.getMessage(), Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

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
         *                   (17 - E)
         * Create switch case the receive every button ID and perform the right action
         * */
        switch (v.getId()) {
            case R.id.buttonLogin:
                // make sign up for the user
                userLogin();
                break;
            case R.id.textViewRegister:
                //                      (23)
                // this button for opening up the Main activity
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }
}
