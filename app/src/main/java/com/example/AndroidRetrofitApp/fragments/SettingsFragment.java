package com.example.AndroidRetrofitApp.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.AndroidRetrofitApp.R;
import com.example.AndroidRetrofitApp.activities.LoginActivity;
import com.example.AndroidRetrofitApp.activities.ProfileActivity;
import com.example.AndroidRetrofitApp.api.RetrofitClient;
import com.example.AndroidRetrofitApp.models.DefultResponse;
import com.example.AndroidRetrofitApp.models.LoginResponse;
import com.example.AndroidRetrofitApp.models.User;
import com.example.AndroidRetrofitApp.storage.SheredPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// (39 - C) implement the click listener and override the needed methods

//              (28)
// (Step 29) go and create the 3 xml fragment files New>layout resource file
// we created this whole package
//  (28 - settings - A)
// to make this class a fragment we need to extends Fragment
// make sure to extends this android.support.v4.app
public class SettingsFragment extends Fragment implements View.OnClickListener{
    //                      (39)
    // (Step 40) Go to API.java
    // (39 - A) define the view object
    private EditText mEditTextChangeEmail, mEditTextChangeName, mEditTextChangeSchool;
    private EditText mEditTextChangeCurrentPassword, mEditTextChangeNewPassword;


    //  (28 - settings - A)
    // override the method onCreateView()
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // inflate our xml file that we want to display on the screen
        // we will use layout inflater object a our brush for drawing
        //we use inflater.inflate()
        // first parameter is file we want draw from
        // the root that we will draw into
        //false for attach to root
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }
    // (39 - B) Initialize the EditText
    // to do this we need to override onViewCreated
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //initialize all the EditText
        mEditTextChangeEmail = view.findViewById(R.id.editTextEmail);
        mEditTextChangeName = view.findViewById(R.id.editTextName);
        mEditTextChangeSchool = view.findViewById(R.id.editTextSchool);
        mEditTextChangeCurrentPassword = view.findViewById(R.id.editTextCurrentPassword);
        mEditTextChangeNewPassword = view.findViewById(R.id.editTextNewPassword);

        //add click listeners to the buttons
        view.findViewById(R.id.buttonSave).setOnClickListener(this);
        view.findViewById(R.id.buttonChangePassword).setOnClickListener(this);
        view.findViewById(R.id.buttonLogout).setOnClickListener(this);
        view.findViewById(R.id.buttonDelete).setOnClickListener(this);
    }
    // (39 - D -2)
    //create this method to update the user
    private void updateProfile(){
        // (39 - D - 1)
        // Add validation
        // receive the data from the Edit Texts in the fragment that build for  update
        String email = mEditTextChangeEmail.getText().toString().trim();
        String name = mEditTextChangeName.getText().toString().trim();
        String school = mEditTextChangeSchool.getText().toString().trim();

        //implement my constrains on it like password length (Check Validation)
        //email.isEmpty()  check if it's empty
        if (email.isEmpty()) {
            mEditTextChangeEmail.setError("Email is required");
            // direct the focus to the field in the run time
            mEditTextChangeEmail.requestFocus();
            return;
        }
        // !Patterns.EMAIL_ADDRESS.matcher(email).matches()
        // check for the email form mane@example.com
        // this line (Patterns.EMAIL_ADDRESS.matcher(email).matches()) means
        // if the email matshes that pattren so we have to write not (!)
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEditTextChangeEmail.setError("Enter a valid email");
            mEditTextChangeEmail.requestFocus();
            // this return to stop the execution
            return;
        }

        if (name.isEmpty()) {
            mEditTextChangeName.setError("Name required");
            mEditTextChangeName.requestFocus();
            // this return to stop the execution
            return;
        }

        if (school.isEmpty()) {
            mEditTextChangeSchool.setError("School required");
            mEditTextChangeSchool.requestFocus();
            // this return to stop the execution
            return;
        }

        //                              (41)
        // (41 - A)
        // get the existing user from the SharedPreferences Manager
        // to get the Current User ID From it
        User mExistingUser = SheredPrefManager
                .getmSheredPrefManagerInstance(getActivity())
                .getUserFromSharedPref();

        Call<LoginResponse> mCall = RetrofitClient
                .getmRetrofitClientInstance()
                .getAPI()
                .updateUser(mExistingUser.getmIDUser(),
                        email,
                        name,
                        school);
        // (41 - B)
        mCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                // Display the message that comes in our response
                Toast.makeText(getActivity(), response.body().getmMessageResponse(), Toast.LENGTH_LONG).show();
                //    UPDATE THE USER THAT IN OUR SHAERD PREF
                // if there is no error so we will updated the user that saved in shared prefs
                if (!response.body().ismErrorResponse()){
                    //get the user that coming in the response
                    // with getmUserResponse()
                    // and store it inside the shared prefs
                    SheredPrefManager
                            .getmSheredPrefManagerInstance(getActivity())
                            .saveUser(response.body().getmUserResponse());
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });

    }
    // (42 - A - 2)
    // (step 42 - B) Go to API.java
    private void updatePassword(){
        //(42 - C)
        //        receive the data from the Edit Texts
        //        and do the validation
        String currentpassword = mEditTextChangeCurrentPassword.getText().toString().trim();
        String newpassword = mEditTextChangeNewPassword.getText().toString().trim();

        if (currentpassword.isEmpty()) {
            mEditTextChangeCurrentPassword.setError("Password required");
            mEditTextChangeCurrentPassword.requestFocus();
            // this return to stop the execution
            return;
        }
        if (newpassword.isEmpty()) {
            mEditTextChangeNewPassword.setError("Enter New Password");
            mEditTextChangeNewPassword.requestFocus();
            // this return to stop the execution
            return;
        }


        // when we update the password we need to pass
        //      1 - Current Password
        //      2 - New Password
        //      3 - User Email
        // to our api
        // get the current user that are stored in the Shared Prefs
        User mCurrentUser = SheredPrefManager
                .getmSheredPrefManagerInstance(getActivity())
                .getUserFromSharedPref();

        //(42 - D)
        // (Step 43) is here which is the log out
        // make the Call
        // get the
        Call<DefultResponse> mCall = RetrofitClient
                .getmRetrofitClientInstance()
                .getAPI()
                .updatePassword(
                        currentpassword,
                        newpassword,
                        mCurrentUser.getmEmailUser()
                );
        mCall.enqueue(new Callback<DefultResponse>() {
            @Override
            public void onResponse(Call<DefultResponse> call, Response<DefultResponse> response) {
                // display a toast with the coming message
                Toast.makeText(getActivity(), response.body().getmMesssageResponse(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<DefultResponse> call, Throwable t) {

            }
        });
    }

    // (43 - B)
    private void logout(){
        // to do this we need to clear the saved user in our shared prefs
        SheredPrefManager
                .getmSheredPrefManagerInstance(getActivity())
                .clearTheSavedUser();
        // then open the login activity
        Intent mIntentObj = new Intent(getActivity(), LoginActivity.class);
        // to close all the existing activities we need to do some flags in this Intent
        mIntentObj.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        // execute the opening
        startActivity(mIntentObj);
    }
    // (44 - B)
    // (Step 44 -B - 2 ) Go to API.java
    // create the method Delete user
    private void deleteUser(){
        // (44 - B - 1)
        //create an alert dialog to take a confirmation from the user to be sure of
        // that he want to delete his acc not by mistake
        AlertDialog.Builder mBuildAlert = new AlertDialog.Builder(getActivity());
        mBuildAlert.setTitle("Are You Sure ?!");
        mBuildAlert.setMessage("This Action is irreversible...");
        mBuildAlert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // (44 - B - 3)
                //if the user selects yes delete the user
                // to do this we need to clear the saved user in our shared prefs

                // first get the user ID from SHared Prefs
                int usertIDFromSharedPref = SheredPrefManager
                        .getmSheredPrefManagerInstance(getActivity())
                        .getUserFromSharedPref()
                        .getmIDUser();

                // Perform the delete user call
                Call<DefultResponse> mCall = RetrofitClient
                        .getmRetrofitClientInstance()
                        .getAPI()
                        .deleteUser(usertIDFromSharedPref);

                // delete the user from the shared prefs
                mCall.enqueue(new Callback<DefultResponse>() {
                    @Override
                    public void onResponse(Call<DefultResponse> call, Response<DefultResponse> response) {
                        // if there is NO error in the process of deleting user (user deleted successfully)
                        // logout
                        if (!response.body().ismErrorResponse()){
                            logout();
                        }
                    }

                    @Override
                    public void onFailure(Call<DefultResponse> call, Throwable t) {
//                        Toast.makeText(getActivity(), response.body().getmMesssageResponse(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        mBuildAlert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        // create the alert dialog
        AlertDialog mAD = mBuildAlert.create();
        // show the alert dialog
        mAD.show();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonSave:
                // (39 - D -1)
                updateProfile();
                break;
            case R.id.buttonChangePassword:
                //                                (42)
                // (42 - A - 1)
                updatePassword();
                break;
            case R.id.buttonLogout:
                //                              (43)
                // (43 - A)
                // logout the the user
                // and open the login activity again
                logout();
                break;
            case R.id.buttonDelete:
                //                              (44)
                // (44 - A)
                // Delete the current user
                deleteUser();
                break;

        }
    }
}
