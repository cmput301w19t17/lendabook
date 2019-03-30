package com.example.android.lendabook;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;

/**
 * Created by belachew on 2019-02-30.
 * Class for Editor's pick.
 */

/**
 * This is a class for the Editor's activity accessed from the bottom navigation bar.
 * This class will show the editor's pick.
 */

public class EditorsPick extends AppCompatActivity {

    private static final String TAG = "EditorsActivity";
    //set activity context
    private Context mContext = EditorsPick.this;

    /**
     * Sets up and initializes the Activity.
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editors_pick);
        Log.d(TAG, "onCreate: started");


        RelativeLayout recommendedBook = findViewById(R.id.relLayoutbook1);

        recommendedBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDialog();
            }
        });

    }



public void MyDialog() {
    final Dialog MyDialog = new Dialog(EditorsPick.this);
    MyDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
    MyDialog.setContentView(R.layout.customdialog);
    MyDialog.setTitle("Why we love this book");

    Button close = (Button)MyDialog.findViewById(R.id.close);

    close.setEnabled(true);

    close.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MyDialog.cancel();
        }
    });

    MyDialog.show();
}}

