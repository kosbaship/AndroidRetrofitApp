package com.example.AndroidRetrofitApp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.AndroidRetrofitApp.R;

//              (28)
// (Step 29) go and create the 3 xml fragment files New>layout resource file
// we created this whole package
//  (28 - settings - A)
// to make this class a fragment we need to extends Fragment
// make sure to extends this android.support.v4.app
public class SettingsFragment extends Fragment {

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
}
