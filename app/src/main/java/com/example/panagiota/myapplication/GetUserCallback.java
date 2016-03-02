package com.example.panagiota.myapplication;

/**
 * Created by Panagiota on 01-Mar-16.
 */
//that class allows us to informed activity which performs the server request when the server request is completed
//so we named this as an interface because it tells the activity which methods to use
    //which methods will be called when the server request

interface GetUserCallback {
    public abstract void done(User returnedUser); //abstract because it's interface and interfaces have only abstract methods
}
