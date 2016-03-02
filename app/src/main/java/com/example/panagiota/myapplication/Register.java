package com.example.panagiota.myapplication;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register extends ActionBarActivity implements View.OnClickListener{
    Button bRegister;
    EditText etName,etAge, etUsername,etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName=(EditText)findViewById(R.id.etName);
        etAge=(EditText)findViewById(R.id.etAge);
        etUsername=(EditText)findViewById(R.id.etUsername);
        etPassword=(EditText)findViewById(R.id.etPassword);

        bRegister.setOnClickListener(this);
    }
//when the user is registered we have its name....
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bRegister:
                String name = etName.getText().toString();
                int age = Integer.parseInt(etAge.getText().toString());
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                //(*1) //and then we need the name Λ
                User user = new User(name, age, username, password);
                registerUser(user);
                break;
        }
    }
    //and when the user is registered we start a server request
    private void registerUser(User user){
        ServerRequests serverRequests=new ServerRequests(this);
        serverRequests.storeUserDataInBackground(user, new GetUserCallback() {
            @Override
            public void done(User returnedUser) {
                Intent loginIntent = new Intent(Register.this, Login.class);
                startActivity(loginIntent);
            }
        });
    }

        }

