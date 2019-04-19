package com.example.AndroidRetrofitApp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
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
            editTextPassword.setError("Password should be atleast 6 character long");
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
        * (Step (11) Go to androidmanifst file ) for the internet permission
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
        Call<ResponseBody> mCall = RetrofitClient
                .getmRetrofitClientInstance()
                .getAPI()
                .createuser(email, password, name, school);

        // enqueue : we need this method to execute the http call
        mCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //(11 - B )
                String mStringResponse = null;

                // (11 - A ) resiving the response in a string object and to do that
                // we required to put it inside a try catch block
                try {
                    // check if the response code refer to creation or the user already exist
                    if (response.code() == 201) {
                        // receive the response to a string'
                        // and if you have to put it inside try block because u recevied it as a string
                            mStringResponse = response.body().string();
                    } else{
                        mStringResponse = response.errorBody().string();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                //                              (12)
                // (12 - A)  parsing the JSON response
                // we will convert this string MString to a JSON object because what we getting
                // as a response from the API is a json object starting and ending with curly braces
                if (mStringResponse != null){
                    // Convert the string to a json object and pass the string to the
                    // json object constructor
                    try {
                        JSONObject mJSONObject = new JSONObject(mStringResponse);
                        // we have Two keys in our response meesage
                        // 1 - error
                        // 2 - message
                        // u will define the DAYATYPE for the key based on the value coming ins the
                        // retrofit response  and pass the key as aparameter
                        String mStringMessage = mJSONObject.getString("message");
                        Toast.makeText(MainActivity.this, mStringMessage, Toast.LENGTH_LONG).show();


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }


            }
            // this pre defiend method will be call in case any Failure of the execution
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                // create a TOAST Message that display the error message catched by the Throwable obj
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
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
                // this button for opening up the login activity
                break;
        }
    }
}
