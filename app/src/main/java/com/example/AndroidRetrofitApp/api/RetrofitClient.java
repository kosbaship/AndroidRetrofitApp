package com.example.AndroidRetrofitApp.api;
//                  (9)
//(Step (10) go to main activity)
// this is not necessary but it is a very GOOD idea if
// your application make to much networking request via retrofit
// what this class will do? or why I need this class
//          this class (RetrofitClient.java) will initialize an instance of
//          the retrofit class
//          every time u call it
// we use Retrofit library in general to send POST REQUEST or other requests


import android.util.Base64;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitClient {
/*    // (45 - B)
    // get it from Postman
    // this how we wend the authorization to API because if someone know our url he can spam our data
    // and this authorization will send an increpted username and password to our database
    // you wll find this header in postman increpted
    //Base64 :this is encoded formate for username and password
    // Basic : this is cas sensitve
    private static final String AUTH = "Basic " + Base64.encodeToString(("kosbaship:123456").getBytes(), Base64.NO_WRAP);

*/
    // (A - after step 9)
    // define the BASE URL of our API
    // what is this? will be this "http://localhost/KosbaAPI/public/"
    // using localhost will NOT work
    // u have to find ur IP
    // if you want this app work in real device make sure to CONNECT ur DEVICE
    // to ur COMPUTER via HOTSPOT
    //   swap localhost with ur ip
    //       ifconfig via terminal addr:192.168.1.8
    //       ipconfig via cmd ap4v address: 192.168.1.8
    private static final String BASE_URL = "http://192.168.1.4/KosbaAPI/public/";
    // (B - after step 9)
    // define the variables I will use
    // define the singleton instance
    private static RetrofitClient mRetrofitClientInstance;
    //DECLARE a retrofit object
    private Retrofit mRetrofitInstance;


    // (C - after step 9)
    // Create a private Constructor and do not forget that the instructor is
    // the first thing to be executed after creating an object of this class
    private RetrofitClient(){
  /*      //                                      (45)
        // (45 - A)             create a basic authentication
        // we will create this to incrept our username and password and send thim with every request to our data
        // so this mean only our app that is have there username and the password (encrepted) who can only menipulat
        // the data
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(
                        new Interceptor() {
                            @Override
                            public Response intercept(Chain chain) throws IOException {
                                // the original request without increption
                                Request original = chain.request();

                                // our authrization on the request
                                Request.Builder requestBuilder = original.newBuilder()
                                        .addHeader("Authorization", AUTH) // AUTH is defined above
                                        .method(original.method(), original.body());
                                // the above header will add to the request calls
                                Request request = requestBuilder.build();
                                return chain.proceed(request);
                            }
                        }
                ).build();
*/
        // (45 - A)
        // add our okHttpClient to the retrofit object
        //                          (12)
        // INITIALIZE the retrofit object
        // be sure to understand that we add here addConverterFactory
        // with GsonConverterFactory and this will do the parsing Automaticlly for us
        // so : go and CREATE Model Class (Step 13) Defultresponse.java
        mRetrofitInstance = new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
               /* .client(okHttpClient) // add ahtrization to every call */
                .build();
    }

    // (D - after step 9)
    // the return type will be the same as the class (RetrofitClient) because
    // we will return an instance of it
    // create a synchronized method to get a singleton instance of
    // our RETROFITCLIENT class
    //synchronized: because we want a single instance only
    public static synchronized RetrofitClient getmRetrofitClientInstance(){
        // if the object is null this mean the object is not yet created
        if(mRetrofitClientInstance == null){
            // in this case we will create this instance
            mRetrofitClientInstance = new RetrofitClient();
        }
        // return the obj which is instance of this current class
        return mRetrofitClientInstance;
    }

    // (E - after step 9)
    // this method is to get the API
    public API getAPI(){
        return mRetrofitInstance.create(API.class);
    }

}
