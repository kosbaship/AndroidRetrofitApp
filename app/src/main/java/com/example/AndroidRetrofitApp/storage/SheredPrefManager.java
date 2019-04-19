package com.example.AndroidRetrofitApp.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.AndroidRetrofitApp.models.User;

/*
*                       (24)
*  (step 25) go to LoginActivity.java
*  (this is a shared preferences local storage && We can also use SQLite)
* this class is for saving the user
*
* this class will manage saving and deleting the user whose login in
*
*   hint:
*       we create the RetrofitClient to get a singleton object from it
*       so we will do the same with shared preferences manager
* */
public class SheredPrefManager {
    // (24 - D - 2) create the shared preferences name to pass it to
    // the method getSharedPreferences()
    private static final String SHARED_PREF_NAME = "kosba_shared_pref";

    // (24 - A) we will create a singleton obj from this class to do this
    // create a static instance from this class
    private static SheredPrefManager mSheredPrefManagerInstance;

    // (24 - B)
    // create a Context Obj because it's obligatory if we wanna handel sharedPref
    private Context mContextObject;

    // (24 - C)
    // Create a private constractor and pass the context object to it
    private SheredPrefManager(Context mContextObject){
        // initialize the constructor
        this.mContextObject =  mContextObject;
    }

    // (24 - C)
    // create a synchronized method as we nly need a single instance
    // the return type will be the same as the class (SheredPrefManager) because
    //do NOT forget to pass the mContextObject
    // we will return an instance of it
    public static synchronized SheredPrefManager getmSheredPrefManagerInstance(Context mContextObject){
        // if the instance is null this mean the object is not yet created
        if(mSheredPrefManagerInstance == null){
            // in this case we will create this instance
            mSheredPrefManagerInstance = new SheredPrefManager(mContextObject);
        }

        // return the obj which is instance of this current class
        return mSheredPrefManagerInstance;
    }

    // (24 - D) create the method to store the user inside shared preferences
    // this will take a User Obj
    public void saveUser(User mUser){
        // (24 - D - 1)
        //  get the shared preferences first
        // getSharedPreferences()    :-
        //          this method take two parameters
        //          1 - the name of the shared preferences
        //          2 - the Mode (here we used MODE_PRIVATE
        //                        as we want only this app
        //                        to access this shared pref)
        //          go and declare the shared pref name above
        // .
        SharedPreferences mSharedPreferencesObj = mContextObject.
                getSharedPreferences(SHARED_PREF_NAME, mContextObject.MODE_PRIVATE);

        // (24 - D - 3)
        // create editor object and call edit() from
        // this obj allows us to write inside shared preferences
        SharedPreferences.Editor mEditor = mSharedPreferencesObj.edit();

        // (24 - D - 4)
        // put all the information we get from this mUser inside shared preferences
        // we put it inside key value pairs
        mEditor.putInt("id" , mUser.getmIDUser());
        mEditor.putString("email" , mUser.getmEmailUser());
        mEditor.putString("name" , mUser.getmNameUser());
        mEditor.putString("school" , mUser.getmScholUser());

        // (24 - D - 5) we have to apply the changes in the mEditor
        //              after this now we officially saved the user inside shared preferences
        mEditor.apply();
    }

    // (24 - E) create the method to check if the user already login or not
    // How do we Check this ??
    // if the user saved his info in shared pref we will assume  he is already login
    public boolean isLoggedIn(){
        // (24 - E - 1) again I will create a shared pref object
        SharedPreferences mSharedPreferencesObj = mContextObject.
                getSharedPreferences(SHARED_PREF_NAME, mContextObject.MODE_PRIVATE);
        // (24 - E - 2) check
        // I give the key id because I want to know about the id that we save
        // the default value is -1
        //      hint:-
        //          in MySQL -1 will not be assigned for any one
        // if this default value minus one changed or in other word not equal to minus one
        // that mean there is a value already saved
        // make this isLoggedIn() return false else return true
        return mSharedPreferencesObj.getInt("id", -1) !=-1;
    }

    // (24 - F) create the method to getBack the user
    public User getUserFromSharedPref(){
        // (24 - F -1) again I will create a shared pref object
        SharedPreferences mSharedPreferencesObj = mContextObject.
                getSharedPreferences(SHARED_PREF_NAME, mContextObject.MODE_PRIVATE);

        // (24 - F -2)
        // read the saved value from the SharedPreferences obj and
        // return this saved User
        return new User(
                mSharedPreferencesObj.getInt("id", -1),
                mSharedPreferencesObj.getString("email", null),
                mSharedPreferencesObj.getString("name", null),
                mSharedPreferencesObj.getString("school", null)
        );
    }
    // (24 - G) create the method to logout
    public void clearTheSavedUser(){
        // (24 - E - 1) again I will create a shared pref object
        SharedPreferences mSharedPreferencesObj = mContextObject.
                getSharedPreferences(SHARED_PREF_NAME, mContextObject.MODE_PRIVATE);

        // (24 - D - 3)
        // create editor object and call edit() from
        // this obj allows us to write inside shared preferences
        SharedPreferences.Editor mEditor = mSharedPreferencesObj.edit();
        //clear() this method will clear every thing that saved in the mEditor
        mEditor.clear();
        // apply the changes
        mEditor.apply();
    }

}
