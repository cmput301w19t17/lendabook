package com.example.android.lendabook.Home;
/*
 * Icon made by Freepik from www.flaticon.com
 * */
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.lendabook.R;

public class AdsFragment extends Fragment {

    private static final String TAG = "AdsFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ads, container, false);
        return view;
    }
}
