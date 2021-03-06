package com.example.AndroidRetrofitApp.api;

//                      (8)
// (Step 9 go to RetrofitClient.java)
// Create this API INTERFACE to Define All the API Calls
// just for defining

import com.example.AndroidRetrofitApp.models.DefultResponse;
import com.example.AndroidRetrofitApp.models.LoginResponse;
import com.example.AndroidRetrofitApp.models.UsersResponse;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

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


    //                      (35)
    // (36) got users_fragment.xml and define the recyclerView
    //(35 - B) GO TO models and create user response
    //(35 - A)
    // define a get request to get all the user rom the API
    //  endpoint : allusers
    //(35 - C)
    // define response type which is UsersResponse
    // this time we do not need to pass any parameters
    @GET("allusers")
    Call<UsersResponse> getAllUsers();


    //                  (40)
    // (Step 41) go to SettingsFragment.java
    // Create a PUT request.
    //  endpoint : updateuser/{id}
    // response type : it's like login Response {error, message, users}
    @FormUrlEncoded
    @PUT("updateuser/{id}")
    Call<LoginResponse> updateUser(
            // we uses @Path because this is a parameter of the url
            @Path("id") int id,
            // the rest is the date will be saved in the database for a particular user
            //and the id is for where clause
            @Field("email") String email,
            @Field("name") String name,
            @Field("school") String school
    );


    //              (42 - B)
    // (42 - C) Go to SettingsFragment.java
    // define the Update password Api call
    // Create a PUT request.
    //  endpoint : updatepassword
    @FormUrlEncoded
    @PUT("updatepassword")
    Call<DefultResponse> updatePassword(
            @Field("currentpassword") String currentpassword,
            @Field("newpassword") String newpassword,
            @Field("email") String email
            );


    //              (44 - B - 2)
    // (44 - B - 3) Go to SettingsFragment.java
    // define the delete user Api call
    // Create a DELETE request.
    //  endpoint : deleteuser
    @DELETE("deleteuser/{id}")
    Call<DefultResponse> deleteUser(
            // we uses @Path because this is a parameter of the url
            @Path("id") int id
    );

}
