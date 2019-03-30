package com.example.android.lendabook;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

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


    }

}

