package com.example.android.lendabook.LogIn;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.lendabook.Home.HomeActivity;
import com.example.android.lendabook.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by oleg on 2019-03-03.
 * Class for logging in as an existing user.
 */

/**
 * The log in activity when logging in as an existing user.
 *  Class uses Firebase database to authenticate an existing user and then sets the user to the current user.
 *  You need an email and password to log in.
 *  Successful login moves the user to the HomeActivity of the application.
 */

public class LogInActivity extends AppCompatActivity {

    private static final String TAG = "LogInActivity";

    private TextView link;
    private EditText input_email;
    private EditText input_password;
    private Button login_btn;

    private FirebaseAuth Authorization;

    /**
     * Sets up and initializes the Activity.
     * If current user is specified, we are already logged in and transfers to home page.
     * Else, wait for button click to move to method that an account exists with this info.
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.d(TAG, "onCreate: started");

        link = (TextView)findViewById(R.id.link_signup);
        input_email = (EditText) findViewById(R.id.input_email);
        input_password = (EditText) findViewById(R.id.input_password);
        login_btn = (Button) findViewById(R.id.btn_login);

        Authorization = FirebaseAuth.getInstance();

        if(Authorization.getCurrentUser() != null){
            finish();
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        }

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogIn();
            }
        });
    }


    /**
     * Sets up the clickable view (blue link text) in order to move user to the RegisterActivity where
     * they can create an account.
     *
     * @param view
     */
    public void click(View view){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    /**
     * LogIn method logs the user in.
     * It obtains email and password from the EditText.
     * Method makes sure neither of the entries are empty and then verifies that the account exists with Firebase authentication.
     */
    public void LogIn(){
        String email = input_email.getText().toString();
        String password = input_password.getText().toString();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Email not entered.",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Password not entered.",Toast.LENGTH_SHORT).show();
            return;
        }

        Authorization.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            finish();
                            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                            startActivity(intent);
                            Toast.makeText(LogInActivity.this, "LogIn Successful!",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(LogInActivity.this, "LogIn Unsuccessful.",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
