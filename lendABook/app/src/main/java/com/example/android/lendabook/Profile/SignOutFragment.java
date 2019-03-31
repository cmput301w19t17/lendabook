package com.example.android.lendabook.Profile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.lendabook.R;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by belachew on 2019-02-27.
 * Class for the user to sign out of t
 */

/**
 * Clicking sign out signs you out of your account on firebase.
 */

public class SignOutFragment extends Fragment {

    private static final String TAG = "SignOutFragment";
    
    /**
     * Creates view once signout click on previous page.
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     *
     * @return view
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signout, container, false);

       return view;
    }
}
