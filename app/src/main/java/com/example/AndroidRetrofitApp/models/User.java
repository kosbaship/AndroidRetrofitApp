package com.example.AndroidRetrofitApp.models;

import com.google.gson.annotations.SerializedName;

/*
*                           (18)
*   (step 19) go and create LoginResponse.java  model class
* create this model class to store the user object the com inside the login response.
* because when we send the login request via post man
* we get this response JSON object start with { and end with }
* {
    "error": false,
    "message": "Login Successful",
    "user ": {
        "id": 14,
        "email": "mohamedwael@gamil.com",
        "name": "Wael",
        "school": "modrenschool"
    }
}
and inside this response object we have another user object with the key "user"
so :-
we need TWO things
    1 - user object to store the user (this file)
    2 - response object to store the response
* */
public class User {

    // (18 - A)
    // define the variable for the coming user data
    // if u choose to declare the variable name different from
    // the json object key name you have to put Json Object key name
    // inside SerializedName("") notation and this will matches them for u
    @SerializedName("id")
    private int mIDUser;
    @SerializedName("email")
    private String mEmailUser;
    @SerializedName("name")
    private String mNameUser;
    @SerializedName("school")
    private String mScholUser;


    // (18 - B) define the constructor
    public User(int mIDUser, String mEmailUser, String mNameUser, String mScholUser) {
        this.mIDUser = mIDUser;
        this.mEmailUser = mEmailUser;
        this.mNameUser = mNameUser;
        this.mScholUser = mScholUser;
    }


    // (18 - C) define the Getter Methods
    public int getmIDUser() {
        return mIDUser;
    }

    public String getmEmailUser() {
        return mEmailUser;
    }

    public String getmNameUser() {
        return mNameUser;
    }

    public String getmScholUser() {
        return mScholUser;
    }
}
