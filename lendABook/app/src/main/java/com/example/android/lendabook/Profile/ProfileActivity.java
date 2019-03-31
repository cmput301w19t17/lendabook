package com.example.android.lendabook.Profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.lendabook.LogIn.RegisterActivity;
import com.example.android.lendabook.R;
import com.example.android.lendabook.Utils.BottomNavigationViewHelper;
import com.example.android.lendabook.Utils.UniversalImageLoader;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
/**
 * Created by belachew, oleg on 2019-03-03.
 * belachew: initial setup along with UI
 * oleg: pulling info from database and setting info in the User Profile.
 * Class for displaying the user profile.
 */

/**
 * The ProfileActivity allows user to see his own profile.
 *  Class uses Firebase database to pull information about the user from real time database and display it.
 *  The information populates the TextView's in the layout.
 */
public class ProfileActivity extends AppCompatActivity {
    private static final String TAG = "ProfileActivity";
    //Sets context
    private Context mContext = ProfileActivity.this;
    private static final int ACTIVITY_NUM = 4;
    private static final int REQUEST_CODE = 1;


    //for firebase
    private FirebaseAuth Authentication;
    private DatabaseReference mRef;

    //define progressBar and default profile photo (we only use default for now)
    private ProgressBar mProgressBar;
    private ImageView profilePhoto;

    /**
     * Sets a layout, define the database information, sets up what will be displayed on the page.
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Log.d(TAG, "onCreate: started");

        //for firebase.
        mRef = FirebaseDatabase.getInstance().getReference();
        Authentication = FirebaseAuth.getInstance();

        //set up what will be displayed on the profile page.
        setUpBottomNavigationView();
        setupToolbar();
        setupActivityWidget();
        setProfileImageAndFullName();
        setupUserInformation();

        Button getLocation = (Button) findViewById(R.id.geolocation);
        getLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                com.google.android.gms.location.places.ui.PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();


                try {
                    startActivityForResult(builder.build(ProfileActivity.this), REQUEST_CODE);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, this);
                String address = String.format("Place: %s", place.getAddress());

                Button getLocation = (Button) findViewById(R.id.geolocation);
                getLocation.setText(address);
            }
        }
    }

    /**
     * Sets a default picture as well as the users full name under the picture
     */
    private void setProfileImageAndFullName() {
        Log.d(TAG, "setProfileImage: setting profile photo.");
        String imgURL = "https://i.pinimg.com/236x/d5/7a/62/d57a62ed19396108635f8e5d50af9cb3--book-nerd-music-books.jpg";
        UniversalImageLoader.setImage(imgURL, profilePhoto, mProgressBar, "");

        //Set the name under the picture to the users full name
        FirebaseUser user = Authentication.getCurrentUser();

        //user verifyUser.user to do another step of verification for the current user.
        Singleton verifyUser = Singleton.getInstance();

        //Firebase query for the fullName (to make it always stay under the picture)
        mRef = FirebaseDatabase.getInstance().getReference("Users");
        Query query = mRef.child(user.getUid());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String FullName = (String) dataSnapshot.child("full_name").getValue().toString();
                //Once we find the full associated with this account, we display it on the profile page.
                TextView name = findViewById(R.id.FullName);
                name.setText(FullName);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

    }

    /**
     * Sets up a widget for progresBar and profilePhoto
     */
    private void setupActivityWidget() {
        mProgressBar = (ProgressBar) findViewById(R.id.profileProgressBar);
        mProgressBar.setVisibility(View.GONE);
        profilePhoto = (ImageView) findViewById(R.id.profile_photo);
    }

    /**
     * Responsible for setting up the profile toolbar and username on the toolbar
     */
    private void setupToolbar() {
        Toolbar toolbar =  findViewById(R.id.profileToolBar);
        setSupportActionBar(toolbar);

        ImageView profileMenu = findViewById(R.id.profile_menu);
        FirebaseUser user = Authentication.getCurrentUser();

        //user verifyUser.user to do another step of verification for the current user.
        Singleton verifyUser = Singleton.getInstance();

        //Firebase query for the username (to make it always stay on the top toolbar)
        mRef = FirebaseDatabase.getInstance().getReference("Users");
        Query query = mRef.child(user.getUid());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String username = (String) dataSnapshot.child("username").getValue().toString();
                //Once we find the username associated with this account, we display it on the profile page.
                TextView profileName = findViewById(R.id.profileName);
                profileName.setText(username);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //navigates to account setting upon clicking profileMenu
        profileMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: navigating to account settings.");
                Intent intent = new Intent(mContext, AccountSettingActivity.class);
                startActivity(intent);
            }
        });

    }

    /**
     * Method to set up user information of the profile page
     */
    private void setupUserInformation(){
        TextView email = findViewById(R.id.email);
        //defines current user
        FirebaseUser user = Authentication.getInstance().getCurrentUser();

        //user verifyUser.user to do another step of verification for the current user.
        Singleton verifyUser = Singleton.getInstance();

        //Set the email in contact information to the email of the account
        String user_email = user.getEmail();
        email.setText("Email: "+user_email);

        //Firebase query for email,phone number, description and website (to make it always stay under the picture)
        mRef = FirebaseDatabase.getInstance().getReference("Users");
        Query query = mRef.child(user.getUid());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String PhoneNo = (String) dataSnapshot.child("phone_number").getValue().toString();
                String Website = (String) dataSnapshot.child("website").getValue().toString();
                String aboutMeDescription = (String) dataSnapshot.child("description").getValue().toString();
                //Once we find the phone number, website and description associated with this account, we display it on the profile page.
                TextView phone_number = findViewById(R.id.phone_number);
                //format the phone number
                String formatPhoneNo = PhoneNumberUtils.formatNumber(PhoneNo);
                phone_number.setText("Phone number: "+formatPhoneNo);
                TextView website = findViewById(R.id.website);
                website.setText("Website: "+Website);
                TextView Description = findViewById(R.id.description);
                Description.setText(aboutMeDescription);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
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
