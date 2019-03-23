package com.example.android.lendabook.Profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.android.lendabook.Home.HomeActivity;
import com.example.android.lendabook.LogIn.LogInActivity;
import com.example.android.lendabook.R;
import com.example.android.lendabook.Utils.BottomNavigationViewHelper;
import com.example.android.lendabook.Utils.SectionsStatePagerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;
/**
 * Created by belachew, oleg on 2019-03-03.
 * belachew: initial setup along with UI
 * oleg: database interaction and allowing user to sign out.
 * Class for displaying the user profile.
 */

/**
 * The AccountSettingActivity allows you to SignOut of your account and go to the Edit profile info page.
 * Displays a list with choices Edit Profile and Sign Out.
 * Edit profile: moves to edit profile page.
 * Sign out: signs the user out of his account.
 */

public class AccountSettingActivity extends AppCompatActivity {

    private static final String TAG = "AccountSettingActivity";
    private Context mContext;
    private static final int ACTIVITY_NUM = 4;

    //sets up page adapters
    private SectionsStatePagerAdapter pagerAdapter;
    private ViewPager mViewPager;
    private RelativeLayout mRelativeLayout;

    //for firebase
    private FirebaseAuth Authorization;

    /**
     * Sets a layout, define the database information, sets up what will be displayed on the page.
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //for firebase and checks if user is logged in.
        Authorization = FirebaseAuth.getInstance();
        if(Authorization.getCurrentUser() == null){
            finish();
            Intent intent = new Intent(getApplicationContext(), LogInActivity.class);
            startActivity(intent);
        }

        //gets the current user
        FirebaseUser user = Authorization.getCurrentUser();

        //sets up current layout
        setContentView(R.layout.activity_accountsettings);
        mContext = AccountSettingActivity.this;
        Log.d(TAG, "onCreate: started");
        mViewPager = findViewById(R.id.container);
        mRelativeLayout = findViewById(R.id.relLayout1);

        setUpSettingList();
        setUpBottomNavigationView();
        setUpFragments();

        // setup the backarrow for navigating back to "ProfileActivity"
        ImageView backArrow = findViewById(R.id.back_arrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: navigating back to 'ProfileActivity'");
                finish();
            }
        });
    }

    //sets up the fragments in list
    private void setUpFragments() {
        pagerAdapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        // Fragment 0
        pagerAdapter.addFragment(new EditProfileFragment(), getString(R.string.edit_profile_fragment));
        // Fragment 1
        pagerAdapter.addFragment(new SignOutFragment(), getString(R.string.sign_out_fragment));
    }

    /**
     * Sets up the ViewPager
     *
     * @param fragmentNumber
     */
    private void setViewPager(int fragmentNumber) {
        mRelativeLayout.setVisibility(View.GONE);
        Log.d(TAG, "setViewPager: navigating to fragment #: " + fragmentNumber);
        mViewPager.setAdapter(pagerAdapter);
        mViewPager.setCurrentItem(fragmentNumber);
    }

    /**
     * Sets a layout of the list with Edit Profile and Sign Out.
     */
    private void setUpSettingList() {
        Log.d(TAG, "setupSettingsList: initializing 'Account Settings' list");
        ListView listView = findViewById(R.id.lvAccountSettings);

        //defines list with options added.
        ArrayList<String> options = new ArrayList<>();
        options.add(getString(R.string.edit_profile_fragment));
        options.add(getString(R.string.sign_out_fragment));

        //list adapter
        ArrayAdapter adapter = new ArrayAdapter(mContext, android.R.layout.simple_list_item_1, options);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Log.d(TAG, "onItemClick: navigating to fragment# : " + position);
                //Clicking signout signs you out
                if(position ==1){
                    Authorization.signOut();
                    Toast.makeText(getApplicationContext(), "Logging out.",Toast.LENGTH_LONG).show();
                    finish();
                    Intent intent = new Intent(getApplicationContext(), LogInActivity.class);
                    startActivity(intent);
                }else{
                    setViewPager(0);
                }
            }
        });
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
