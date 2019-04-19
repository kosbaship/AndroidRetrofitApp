package com.example.AndroidRetrofitApp.models;

import com.google.gson.annotations.SerializedName;

//                          (13)
// (Step 14)  Gor To API.java interface
// this will contains the keys of the JSON response that comes from the API
// for example we get this message from the API call as a response
//{
//    "error": false,
//    "message": "User Created Successfully"
//}
//this is a json object
public class DefultResponse {

    // (13 - A)
    // if u choose to declare the variable name different from
    // the json object key name you have to put Json Object key name
    // inside SerializedName("") notation and this will matches them for u
    @SerializedName("error")
    private boolean mErrorResponse;
    @SerializedName("message")
    private String mMesssageResponse;

    // (13 - B) define the constructor
    public DefultResponse(boolean mErrorResponse, String mMesssageResponse) {
        this.mErrorResponse = mErrorResponse;
        this.mMesssageResponse = mMesssageResponse;
    }


    // (13 - C) define the Getter Methods
    public boolean ismErrorResponse() {
        return mErrorResponse;
    }
    public String getmMesssageResponse() {
        return mMesssageResponse;
    }
}
