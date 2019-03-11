package com.example.android.lendabook.Profile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.lendabook.LogIn.RegisterActivity;
import com.example.android.lendabook.LogIn.UserInformation;
import com.example.android.lendabook.R;
import com.example.android.lendabook.Utils.UniversalImageLoader;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by belachew, oleg on 2019-03-03.
 * belachew: initial setup along with UI
 * oleg: pulling info from database and allowing user to edit, then saving to database
 * Class for allowing user to edit the information saved on his/her profile
 */

/**
 * The EditProfile Fragment allows user to edit old information and save new information on his/her profile.
 *  Class uses Firebase database to pull old information from the current users real time firebase saved info.
 *  Then it populates the editText windows with this info and allows the user to edit it.
 *  Afterwards, it saves the new information in the real time database under the users uid.
 */
public class EditProfileFragment extends Fragment {

    private static final String TAG = "EditProfileFragment";

    //define editText
    private ImageView mProfilePhoto;
    private EditText name;
    private EditText website;
    private EditText description;
    private EditText email;
    private EditText phone_number;
    private Button save_userInfo;

    //define Strings we are going to be saving to.
    private String user_full_name;
    private String user_description;
    private String user_website;
    private String user_phone_number;
    private String user_username;
    private String user_email;

    //for firebase
    private FirebaseAuth Authentication;
    private DatabaseReference mRef;

    /**
     * Sets a layout to the root view to diplay, displays editText and profile image.
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //declares the root view
        final View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);
        mProfilePhoto = view.findViewById(R.id.profile_photo);

        //finds saveButton and editText for editing info
        save_userInfo = (Button) view.findViewById(R.id.Save);
        name = view.findViewById(R.id.name);
        phone_number = view.findViewById(R.id.phoneNumber);
        website = view.findViewById(R.id.website);
        description = view.findViewById(R.id.description);
        email = view.findViewById(R.id.email);

        //for firebase.
        mRef = FirebaseDatabase.getInstance().getReference();
        Authentication = FirebaseAuth.getInstance();

        //Set the email in contact information to the email editText
        FirebaseUser user = Authentication.getInstance().getCurrentUser();
        String user_email = user.getEmail();
        email.setText(user_email, TextView.BufferType.EDITABLE);

        //Firebase query for email,phone number, Description and website (to make it always stay under the picture)
        mRef = FirebaseDatabase.getInstance().getReference("Users");
        Query query = mRef.child(user.getUid());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Once we find the phone number, website and description associated with this account, we display it on the profile page.
                user_username = dataSnapshot.child("username").getValue().toString();
                user_full_name = dataSnapshot.child("full_name").getValue().toString();
                name.setText(user_full_name, TextView.BufferType.EDITABLE);

                user_phone_number = dataSnapshot.child("phone_number").getValue().toString();
                phone_number.setText(user_phone_number, TextView.BufferType.EDITABLE);


                user_website = dataSnapshot.child("website").getValue().toString();
                website.setText(user_website, TextView.BufferType.EDITABLE);

                user_description = dataSnapshot.child("description").getValue().toString();
                description.setText(user_description, TextView.BufferType.EDITABLE);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        // Sets a static profile image.
        setProfileImage();


        // Back arrow for navigating back to "ProfileActivity."
        ImageView backArrow = (ImageView) view.findViewById(R.id.back_arrow);
        // Navigates back to ProfileActivity upon clicking back button.
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: navigating back to ProfileActivity");
                getActivity().finish();
            }
        });

        //upon clicking save_user button, it will execute SaveNewInfo() method.
        save_userInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveNewInfo();
            }
        });

        return view;
    }

    /**
     * Sets a profile image from a default url.
     */
    private void setProfileImage() {
        Log.d(TAG, "setProfileImage: setting profile image.");
        String imgURL = "https://i.pinimg.com/236x/d5/7a/62/d57a62ed19396108635f8e5d50af9cb3--book-nerd-music-books.jpg";
        UniversalImageLoader.setImage(imgURL, mProfilePhoto, null, "");
    }

    /**
     * Obtains new information from the editText and then saves it in the database under the user uid
     */
    private void SaveNewInfo(){
        //obtains information from editText's
        user_full_name = name.getText().toString();
        user_description = description.getText().toString();
        user_phone_number = phone_number.getText().toString();
        user_website = website.getText().toString();
        user_email = email.getText().toString();

        //creates a new bundle of user information
        UserInformation userInfo = new UserInformation(user_username, user_full_name, user_website, user_description, user_phone_number);

        //authenticates user and saves information
        FirebaseUser user = Authentication.getCurrentUser();
        user.updateEmail(user_email);
        mRef.child(user.getUid()).setValue(userInfo);

        Toast.makeText(getContext(), "Information saved!",Toast.LENGTH_SHORT).show();
    }
}
