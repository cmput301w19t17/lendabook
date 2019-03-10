package com.example.android.lendabook.Home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.android.lendabook.Profile.BookListActivity;
import com.example.android.lendabook.R;

/**
 * First page shown when logged in. Not finished for this version. Only contains a temporary button to take you to the list of books you have.
 */

public class HomeFragment extends Fragment {
    Button btnBookList;
    private static final String TAG = "HomeFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        btnBookList = (Button) view.findViewById(R.id.btn_bklist);
        btnBookList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), BookListActivity.class);
                startActivity(intent);
            }
        });
        return view;


    }
}
