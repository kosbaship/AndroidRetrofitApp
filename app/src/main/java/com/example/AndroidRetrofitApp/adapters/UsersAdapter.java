package com.example.AndroidRetrofitApp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.AndroidRetrofitApp.R;
import com.example.AndroidRetrofitApp.models.User;

import java.util.List;

//                  (34)
// (35) Go to API.java
//(34 - A)
// we created this recycler view adapter
//    1 - extends the RecyclerView.Adapter make sure .widget.RecyclerView
//    2 - Adapter<Here> for this adapter we need to define a view holder
//    3 - UsersAdapter.UserViewHolder
//          we need to create this calss (UserViewHolder)
//          inside this class (UsersAdapter)
public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder>{
    //(34 - C - 1)
    // define some object and then create a constructor
    private Context mContextObj;
    // the list that we want to display
    private List<User> mUserList;
    //(34 - C - 2)
    // generate a constructor to initialize the above two values
    public UsersAdapter(Context mContextObj, List<User> mUserList) {
        this.mContextObj = mContextObj;
        this.mUserList = mUserList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //(34 - C - 4)
        // we should return our users view holder
        // and for this we need our view and draw the view on it using inflater
        View mViewOnTheScreen = LayoutInflater.from(mContextObj)
                .inflate(R.layout.users_listitem, viewGroup, false);
        // return an instanse of the class new user view holder
        return new UserViewHolder(mViewOnTheScreen);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder userViewHolder, int i) {
        //(34 - C - 5)
        // first we will get the current user using the i
        User mCurrentUser = mUserList.get(i);
        //(34 - C - 7)
        //use this userViewHolder to get the views values from the UserViewHolder
        // and also we will read the user that matches what we need from the
        // user obj.
        userViewHolder.mTextViewEmailToDisplay.setText(mCurrentUser.getmEmailUser());
        userViewHolder.mTextViewNameToDisplay.setText(mCurrentUser.getmNameUser());
        userViewHolder.mTextViewSchoolToDisplay.setText(mCurrentUser.getmScholUser());
    }

    @Override
    public int getItemCount() {
        //(34 - C - 3) change the return
        return mUserList.size();
    }

    //(34 - B)
    // we need to extends RecyclerView.ViewHolder
    // alt + enter to create a constructor
    // then alt + enterthe above class to implement methods
    class UserViewHolder extends RecyclerView.ViewHolder{
        //(34 - C - 6)
        //define our views
        TextView mTextViewEmailToDisplay,
                mTextViewNameToDisplay,
                mTextViewSchoolToDisplay;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            // because we initialize and sending the view here
            //        return new UserViewHolder(mViewOnTheScreen);
            // here in this return as an argument we can use the context
            // to get reference to the views on the screen
            mTextViewEmailToDisplay = itemView.findViewById(R.id.displayedEmailID);
            mTextViewNameToDisplay = itemView.findViewById(R.id.displayedNameID);
            mTextViewSchoolToDisplay = itemView.findViewById(R.id.displayedSchoolID);
            // send there values too on bind view holder

        }
    }
}
