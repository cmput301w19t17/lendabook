package com.example.android.lendabook.LogIn;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.android.lendabook.R;
import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";
    private FirebaseAuth Authentication;

    // define all editText and button
    private EditText input_username;
    private EditText input_email;
    private EditText input_full_name;
    private EditText input_password;
    private Button register;

    private DatabaseReference mRef;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Log.d(TAG, "onCreate: started");

        Authentication = FirebaseAuth.getInstance();

        mRef = FirebaseDatabase.getInstance().getReference();

        // link all buttons/editText
        input_username = (EditText) findViewById(R.id.input_username);
        input_email = (EditText) findViewById(R.id.input_email);
        input_full_name = (EditText) findViewById(R.id.input_full_name);
        input_password = (EditText) findViewById(R.id.input_password);
        register = (Button) findViewById(R.id.btn_register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveToRegisterDB();
            }
        });
    }

    public void saveToRegisterDB(){
        String username = input_username.getText().toString();
        String email = input_email.getText().toString();
        String full_name = input_full_name.getText().toString();
        String password = input_password.getText().toString();

        // Checks if field for username, email or password is empty; if yes, alert user and stop register process
        if(TextUtils.isEmpty(username)){
            Toast.makeText(this, "Please enter username.",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please enter email.",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please enter password.",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(full_name)){
            Toast.makeText(this, "Please enter your full name.",Toast.LENGTH_SHORT).show();
            return;
        }


        //Checks if the username entered is unique
        // found at: https://stackoverflow.com/questions/38948905/how-can-i-check-if-a-value-exists-already-in-a-firebase-data-class-android/41646772
        FirebaseUser user = Authentication.getCurrentUser();
        mRef.child("Users").orderByChild("username").equalTo(username)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            Toast.makeText(RegisterActivity.this, "Username Taken.",Toast.LENGTH_SHORT).show();
                        }else{
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

        Authentication.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            String username = input_username.getText().toString();
                            String full_name = input_full_name.getText().toString();

                            UserInformation userInfo = new UserInformation(username, full_name, "none", "none", "none");

                            FirebaseUser user = Authentication.getCurrentUser();
                            mRef.child("Users").child(user.getUid()).setValue(userInfo);

                            Toast.makeText(RegisterActivity.this, "Registration Successful.",Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(getApplicationContext(), LogInActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(RegisterActivity.this, "Registration Failed, please try again!",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}