package com.example.panagiota.myapplication;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends ActionBarActivity implements View.OnClickListener {

    Button bLogin;
    EditText etUsername, etPassword;
    TextView tvRegisterLink;

    UserLocalStore userLocalStore; //we need to have access to the UserLocalStore

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bLogin = (Button) findViewById(R.id.blogin);
        tvRegisterLink = (TextView) findViewById(R.id.tvRegisterLink);

//so this button is clicked
        bLogin.setOnClickListener(this);
        tvRegisterLink.setOnClickListener(this);

        userLocalStore = new UserLocalStore(this); //we give the local store a context
    }

    //when this button is clicked this method will be notified but if we have multiple on ClickListener
    //this method will be notified balloon
    @Override
    //in this part of code we can see what happens when the Login button is clicked
    public void onClick(View v) {
        //1.we won't notified balloon
        switch (v.getId()) { //2.switch: it gets the id of the view which notified this on click method
            case R.id.blogin://3.and then if the login button was the one who notified this click method
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                //we say that when a user logs in we need to tell to the local database that
                //we used a logIn and it needs to store the details about the user
                User user = new User(username, password);

                authenticate(user);
                break;

            //now we need to create another case and this will be called when the register link is clicked
            case R.id.tvRegisterLink:
                startActivity(new Intent(this, Register.class));
                break;
        }
    }

    private void authenticate(User user) {
        ServerRequests serverRequest = new ServerRequests(this);
        serverRequest.fetchUserDataInBackground(user, new GetUserCallback() {
            @Override
            public void done(User returnedUser) { //this done method we called
                if (returnedUser == null) {
                    showErrorMessage();
                }else{
                  logUserIn(returnedUser);
                }
                }
        });
    }

    private void showErrorMessage() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Login.this);
        dialogBuilder.setMessage("Incorrect user details");
        dialogBuilder.setPositiveButton("OK", null);
        dialogBuilder.show();
    }
    private void logUserIn(User returnedUser) {
        userLocalStore.storeUserData(returnedUser);
        userLocalStore.setUserLoggedIn(true);//if the login is successful
        startActivity(new Intent(this, MainActivity.class));
    }
}

