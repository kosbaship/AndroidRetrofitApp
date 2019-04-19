package com.example.AndroidRetrofitApp.models;

/*
*                               (19)
*   (Step 20)   go to API.java
*   this class for the JSON response object itself that contains the user obj
*   in the login response we have error, message and user
*   to detremine the the error message because you need it to decide how you will display the data for the user in
    the screen inside the enqueue methhod

* * {
    "error": false,
    "message": "Login Successful",
    "user ": {
        "id": 14,
        "email": "mohamedwael@gamil.com",
        "name": "Wael",
        "school": "modrenschool"
    }
*
*
* */
public class LoginResponse {

    // (19 - A)
    // define the variable for the coming user data
    // if u choose to declare the variable name different from
    // the json object key name you have to put Json Object key name
    // inside SerializedName("") notation and this will matches them for u
    private boolean error;
    private String message;
    private User user;

    // (19 - B) define the constructor
    public LoginResponse(boolean error, String message, User user) {
        this.error = error;
        this.message = message;
        this.user = user;
    }


    // (19 - C) define the Getter Methods
    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }
}
