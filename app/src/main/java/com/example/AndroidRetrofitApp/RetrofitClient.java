package com.example.AndroidRetrofitApp;
//                  (9)
//(Sterp (10) go to main activity)
// this is not nesessary but it is a very GOOD idea if
// your application make to much networking request via retrofit
// what this class will do? or why I need this class
//          this class (RetrofitClient.java) will initialize an instance of
//          the retrofit class
//          every time u call it
// we use Retrofit library in general to send POST REQUEST or other requests


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitClient {
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
    private static final String BASE_URL = "http://10.42.0.1/KosbaAPI/public/";
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
        // INITIALIZE the retrofit object
        mRetrofitInstance = new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    // (D - after step 9)
    // create a synchronized method to get a singleton instance of
    // our RETROFITCLIENT class
    //synchronized: because we want a single instance only
    public static synchronized RetrofitClient getmRetrofitClientInstance(){
        // if the object is null this mean the object is not yet created
        if(mRetrofitClientInstance == null){
            // in this case we will create this instance
            mRetrofitClientInstance = new RetrofitClient();
        }
        // return the instance
        return mRetrofitClientInstance;
    }

    // (E - after step 9)
    // this method is to get the API
    public API getAPI(){
        return mRetrofitInstance.create(API.class);
    }

}
