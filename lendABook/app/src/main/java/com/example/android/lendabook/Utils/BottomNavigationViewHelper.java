package com.example.android.lendabook.Utils;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.MenuItem;

import com.example.android.lendabook.Add.AddActivity;
import com.example.android.lendabook.Home.HomeActivity;
import com.example.android.lendabook.Notification.NotificationActivity;
import com.example.android.lendabook.Profile.ProfileActivity;
import com.example.android.lendabook.R;
import com.example.android.lendabook.Search.SearchActivity;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

/**
 * Created by belachew on 2019-02-27.
 * Class for the bottom navigation bar.
 */

/**
 * The class is for creation of the bottom navigation bar and allow user to use it.
 */

public class BottomNavigationViewHelper {

    private static final String TAG = "BottomNavigationViewHel";
    
    /**
     * allows commands/viewing of the bottom navigation bar.
     *
     * @param bottomNavigationViewEx
     */
    public static void setupBottomNavigationView(BottomNavigationViewEx bottomNavigationViewEx) {
        Log.d(TAG, "setupBottomNavigationView: Setting up NavigationView");
        bottomNavigationViewEx.enableAnimation(false);
        bottomNavigationViewEx.setTextVisibility(false);
        bottomNavigationViewEx.enableShiftingMode(1, false);

    }
    
    /**
     * Enables navigation in bottom navigation bar.
     *
     * @param context
     * @param view
     */
    public static void enableNavigation(final Context context, BottomNavigationViewEx view) {
        view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()) {
                    //switch case based on which icon you click
                    case R.id.ic_house:
                        Intent intent1 = new Intent(context, HomeActivity.class); // ACTIVITY_NUM = 0
                        context.startActivity(intent1);
                        break;

                    case R.id.ic_search:
                        Intent intent2 = new Intent(context, SearchActivity.class); // ACTIVITY_NUM = 1
                        context.startActivity(intent2);
                        break;

                    case R.id.ic_add:
                        Intent intent3 = new Intent(context, AddActivity.class); // ACTIVITY_NUM = 2
                        intent3.putExtra("cameFrom", 0);
                        context.startActivity(intent3);
                        break;

                    case R.id.ic_notification:
                        Intent intent4 = new Intent(context, NotificationActivity.class); // ACTIVITY_NUM = 3
                        context.startActivity(intent4);
                        break;

                    case R.id.ic_user:
                        Intent intent5 = new Intent(context, ProfileActivity.class); // ACTIVITY_NUM = 4
                        context.startActivity(intent5);
                        break;
                }
                return true;
            }
        });
    }
}
