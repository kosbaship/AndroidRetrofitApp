package com.example.AndroidRetrofitApp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.AndroidRetrofitApp.R;
import com.example.AndroidRetrofitApp.storage.SheredPrefManager;

//              (28)
// (Step 29) go and create the 3 xml fragment files New>layout resource file
// we created this whole package
//  (28 - Home - A)
// to make this class a fragment we need to extends Fragment
// make sure to extends this android.support.v4.app
public class HomeFragment extends Fragment {

    //  (28 - Home - A)
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
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    //                                      (32)
    //(step 33) go fragment_users.xml
    //(32 - A)
    // we need to override onViewCreated()
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //(32 - B) declare the views that on the screen variables
        // we will get the text views using this view object
        TextView textViewEmail = view.findViewById(R.id.textViewEmail);
        TextView textViewName = view.findViewById(R.id.textViewName);
        TextView textViewSchool = view.findViewById(R.id.textViewSchool);

        //(32 - C) time to get the data of the user from his shared preferences
        //   getActivity()
        //          we use this method to get that context because we need a context for the
        //          the method will return back to us an instance of the shared pref
        //          getmSheredPrefManagerInstance()
        //          why?
        //          we are inside a fragment not an activity so will pass this to get the context
        textViewEmail.setText(
                SheredPrefManager.getmSheredPrefManagerInstance(getActivity()) // here we get an instance of the shared pref manger
                        .getUserFromSharedPref() //this is the whole user inside shared pref
                        .getmEmailUser()); // this is the current user email
        textViewName.setText(
                SheredPrefManager.getmSheredPrefManagerInstance(getActivity()) // here we get an instance of the shared pref manger
                        .getUserFromSharedPref() //this is the whole user inside shared pref
                        .getmNameUser()); // this is the current user email
        textViewSchool.setText(
                SheredPrefManager.getmSheredPrefManagerInstance(getActivity()) // here we get an instance of the shared pref manger
                        .getUserFromSharedPref() //this is the whole user inside shared pref
                        .getmScholUser()); // this is the current user email
    }
}
