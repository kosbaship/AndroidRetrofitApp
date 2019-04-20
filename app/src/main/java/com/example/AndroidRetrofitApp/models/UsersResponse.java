package com.example.AndroidRetrofitApp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

// (35 - B) create this user response
//in our response we have a boolean variable and a list
//{
//        "error": false,
//        "users": [
//        {
//        "id": 1,
//        "email": "bito@gamil.com",
//        "name": "Peter",
//        "school": "elsa3deya"
//        },
//        {
//        "id": 2,
//        "email": "forteast@gamil.com",
//        "name": "Islam",
//        "school": "Ali Mubarak"
//        },
//        {
//        "id": 3,
//        "email": "forteast@gamil.com",
//        "name": "Islam",
//        "school": "Omar Ebn El Khatab"
//        }
//}
public class UsersResponse {

    @SerializedName("error")
    private boolean mErrorResponse;
    // because we getting back a list with type user
    // we knw this from the API response in Postman
    @SerializedName("users")
    private List<User> mUsersResponse;

    //generate the constructor
    public UsersResponse(boolean mErrorResponse, List<User> mUsersResponse) {
        this.mErrorResponse = mErrorResponse;
        this.mUsersResponse = mUsersResponse;
    }

    // generate the getters

    public boolean ismErrorResponse() {
        return mErrorResponse;
    }

    public List<User> getmUsersResponse() {
        return mUsersResponse;
    }
}
