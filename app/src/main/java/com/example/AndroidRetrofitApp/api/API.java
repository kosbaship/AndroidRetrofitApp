package com.example.AndroidRetrofitApp.api;

//                      (8)
// (Step 9 go to RetrofitClient.java)
// Create this API INTERFACE to Define All the API Calls
// just for defining

import com.example.AndroidRetrofitApp.models.DefultResponse;
import com.example.AndroidRetrofitApp.models.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface API {
    // (A - After Step 8)
    // create the createuser API
    //  endpoint : createuser
    //     Call< HERE u should write the response >
    // if u have no idea about the response use ResponseBody
    // notation : FormUrlEncoded this required because we sending
    //              a fourm url encoded request
    //                       (14)
    // set the call type to DefultResponse
    // (Step 15)  go to MainActivity.java
    @FormUrlEncoded
    @POST("createuser")
    Call<DefultResponse> createuser(
            // here we will define the fields (Requierd Parameters)
            // that we will send when creating a user
            // to know them send a post request via postman to this endpoint
            // and see the parameters are required
            @Field("email") String email,
            @Field("password") String password,
            @Field("name") String name,
            @Field("school") String school
    );
    //                      (20)
    // (Step 21) go to LoginActivity.java
    // create the userLogin API
    //  endpoint : userlogin
    //     Call< HERE u should write the response >
    // if u have no idea about the response use ResponseBody
    // notation : FormUrlEncoded this required because we sending
    //              a fourm url encoded request
    @FormUrlEncoded
    @POST("userlogin")
    Call<LoginResponse> userlogin(
            // here we will define the fields (Requierd Parameters)
            // that we will send when creating a user
            // to know them send a post request via postman to this endpoint
            // and see the parameters are required
            @Field("email") String email,
            @Field("password") String password
    );

}
