package com.example.panagiota.myapplication;

/**
 * Created by Panagiota on 01-Mar-16.
 */
public class User {
    String name, username, password;
    int age;

    //there are two ways of creating a user
    public User(String name,int age ,String username, String password){
        this.name=name;
        this.age=age;
        this.username=username;
        this.age=age;
    }
    //another way of creating a user is when we know its username and its password
    public  User(String username,String  password){
        this("",-1,username,password);
    }
}
