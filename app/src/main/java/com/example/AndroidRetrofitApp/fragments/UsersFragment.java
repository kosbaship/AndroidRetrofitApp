package com.example.AndroidRetrofitApp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.AndroidRetrofitApp.R;
import com.example.AndroidRetrofitApp.adapters.UsersAdapter;
import com.example.AndroidRetrofitApp.api.RetrofitClient;
import com.example.AndroidRetrofitApp.models.User;
import com.example.AndroidRetrofitApp.models.UsersResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//              (28)
// (Step 29) go and create the 3 xml fragment files New>layout resource file
// we created this whole package
//  (28 - users - A)
// to make this class a fragment we need to extends Fragment
// make sure to extends this android.support.v4.app
public class UsersFragment extends Fragment {


    //                      (37)
    //(37 - A)
    // Define our RecyclerView
    private RecyclerView mRecyclerView;
    // define the adapter of the recycler view
    private UsersAdapter mUsersAdapter;
    // and also define the list of users we want to display
    private List<User> mUsersList;

    //  (28 - users - A)
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
        return inflater.inflate(R.layout.fragment_users, container, false);
    }


    //(37 - B)
    // Override the method on View Created
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // initialize the recycler view
        mRecyclerView = view.findViewById(R.id.recyclerviewID);
        // we pass getActivity() and we are in fragment
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // get the list of user frm the api call
        Call<UsersResponse> mCall = RetrofitClient
                .getmRetrofitClientInstance()
                .getAPI()
                .getAllUsers();
        mCall.enqueue(new Callback<UsersResponse>() {
            @Override
            public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
                // from response we can get the list of users
                //  response.body().getmUsersResponse()
                //          this will get the response as a list
                // mUsersList this will store it for us
                mUsersList = response.body().getmUsersResponse();

                // create the adapter to manage the list of users
                // first parameter is Context
                // second is a list of user and we will pass what we just fetched
                mUsersAdapter = new UsersAdapter(getActivity(), mUsersList);
                // we will set this adapter to recycler view
                mRecyclerView.setAdapter(mUsersAdapter);


// test ur app and if it crashes check the logcat and clear ir first
            }

            @Override
            public void onFailure(Call<UsersResponse> call, Throwable t) {

            }
        });
    }
}

