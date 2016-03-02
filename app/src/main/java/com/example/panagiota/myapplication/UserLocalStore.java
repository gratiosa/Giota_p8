package com.example.panagiota.myapplication;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Panagiota on 01-Mar-16.
 */

//this class allows us to install user data in a file and share its preferences and to get details about the
    //user loggedIn and clean the users data.

//when we create a UserLocalStore we need to give the context from the activity
    //which is used in there and this will be used  to get the shared preference
public class UserLocalStore {
    public static final String SP_NAME="userDetails";
    SharedPreferences userLocalDatabase; //allows us to store data on the phone it a local class so it cannot instantiate a shared preference

    public UserLocalStore (Context context){ //constructor
        userLocalDatabase=context.getSharedPreferences(SP_NAME,0);
    }
    //now we need to genarate methods that we can use to get data from the local database
    //and also to set the user data
    public void storeUserData(User user){

        //to store the userData we need to give the method
        //this allows to edit what is contained

        SharedPreferences.Editor spEditor=userLocalDatabase.edit();
        //What this does is updated everything is currently stored and shared preferences with the attributes of the user

        //now we need to store the attributes of the use which have been post on this method so:
        spEditor.putString("name",user.name);
        //and we need to store its age
        spEditor.putInt("age",user.age);
        //and we need to store its username
        spEditor.putString("username",user.username);
        //and we need to store its password
        spEditor.putString("password",user.password);
        spEditor.commit();
    }
    //another method we need is (user->login:true //user->logout:false
    public void setUserLoggedIn (boolean loggedIn){
        //so we need to store the data
        SharedPreferences.Editor spEditor=userLocalDatabase.edit();
        spEditor.putBoolean("loggedIn", loggedIn);
        spEditor.commit();
    }
    public void clearUserData() {
        SharedPreferences.Editor spEditor=userLocalDatabase.edit();
        spEditor.clear();
        spEditor.commit();
    }
    //we create another method that allows us to get the user
    //this method need to return a user so
    //in this way we can receive all the attributes of the user stored in the local database
    public User getLoggedInUser(){
        if (userLocalDatabase.getBoolean("loggedIn", false) == false) {
            return null;
        }
        String name=userLocalDatabase.getString("name","");
        int age=userLocalDatabase.getInt("age", -1);
        String username=userLocalDatabase.getString("username", "");
        String password=userLocalDatabase.getString("password","");
//we need to create the user and to give him its attributes
        User user = new User(name, age, username, password);
        return user;
    }
}
//in order to use all these methods we need to go to the register class and create a new user....(*1)