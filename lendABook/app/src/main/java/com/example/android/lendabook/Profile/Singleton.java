package com.example.android.lendabook.Profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by oleg on 2019-03-26.
 * Singleton class for an extra step of verification of the current user which we use in ProfileActivity.java
 */

/**
 * Implements the Singleton design pattern to check who the current user that is logged in.
*/

public class Singleton{

    private static Singleton instance = null;

    //define firebase authentication and reference.
    private FirebaseAuth Authentication;
    private DatabaseReference mRef;

    FirebaseUser user = Authentication.getInstance().getCurrentUser();

    private Singleton(){}

    public static Singleton getInstance() {

        if (instance != null) {
            instance = new Singleton();
        }
        return instance;
    }
}

