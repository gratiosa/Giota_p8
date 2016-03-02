package com.example.panagiota.myapplication;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    Button bLogout;
    EditText etName, etAge, etUsername;
    UserLocalStore userLocalStore;

    //so we need the main activity to have access to the local store
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName=(EditText)findViewById(R.id.etName);
        etAge=(EditText)findViewById(R.id.etAge);
        etUsername=(EditText)findViewById(R.id.etUsername);
        bLogout=(Button)findViewById(R.id.bLogout);

        bLogout.setOnClickListener(this);

        userLocalStore=new UserLocalStore(this); //we give the local store a context
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bLogout:
                userLocalStore.clearUserData(); //when the used is loggedout we can clear its data
                userLocalStore.setUserLoggedIn(false);
                Intent loginIntent = new Intent(this, Login.class);
                startActivity(loginIntent);
                break;
        }
    }
    @Override
    public void onStart() {
        super.onStart();
        if (authenticate() == true) { //if authenticate is equal to true we can do anything we want to do in the main activity
//we want to display the user details
            displayUserDetails();
        }
    }
    private boolean authenticate() {
        if (userLocalStore.getLoggedInUser() == null) {
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
            return false;
        }
        return true;
    }
    private void displayUserDetails(){
        //firstly we dsplay the user who is logged on..
        User user=userLocalStore.getLoggedInUser();
//in order to display the user details:
        etUsername.setText(user.username);
        etUsername.setText(user.name);
        etUsername.setText(user.age + "");
    }

}
