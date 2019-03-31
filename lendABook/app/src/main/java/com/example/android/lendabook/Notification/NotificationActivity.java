package com.example.android.lendabook.Notification;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.android.lendabook.R;
import com.example.android.lendabook.Utils.BottomNavigationViewHelper;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

/**
 * Created by belachew on 2019-02-27.
 * Class for Notification fragment.
 */

/**
 * This is a class for the Notification page accessed from the bottom navigation bar.
 * This class will show notifications but it isn't developed yet.
 */
public class NotificationActivity extends AppCompatActivity {
    private static final String TAG = "NotificationActivity";
    //set activity context
    private Context mContext = NotificationActivity.this;
    private static final int ACTIVITY_NUM = 3;

     /**
     * Sets up and initializes the Activity.
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        Log.d(TAG, "onCreate: started");

        setUpBottomNavigationView();
    }

    /**
     * BottomNavigationView setup
     * */
    private void setUpBottomNavigationView() {
        Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView");
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(mContext, bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }
}
