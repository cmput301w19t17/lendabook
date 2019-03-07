package com.example.android.lendabook.Add;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.android.lendabook.R;
import com.example.android.lendabook.Utils.BottomNavigationViewHelper;
import com.example.android.lendabook.Utils.fetchData;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import static com.example.android.lendabook.Utils.fetchData.isbn;
import static com.example.android.lendabook.Utils.fetchData.parsedAuthor;
import static com.example.android.lendabook.Utils.fetchData.parsedDescription;
import static com.example.android.lendabook.Utils.fetchData.parsedTitle;

public class AddActivity extends AppCompatActivity {
    private static final String TAG = "AddActivity";
    private Context mContext = AddActivity.this;
    private static final int ACTIVITY_NUM = 2;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Log.d(TAG, "onCreate: started");

        setupToolbar();
        setUpBottomNavigationView();
        fillTextView();
    }

    // fills text boxes with data from book api
    private void fillTextView(){
        EditText isbnTitle = (EditText) findViewById(R.id.input_book_title);
        EditText isbnBox = (EditText) findViewById(R.id.input_isbn);
        EditText descBox = (EditText) findViewById(R.id.input_book_description);
        EditText authorBox = (EditText) findViewById(R.id.input_author);
        Log.d("999: @filltextview", parsedTitle);
        isbnBox.setText(isbn);
        isbnTitle.setText(parsedTitle);
        descBox.setText(parsedDescription);
        authorBox.setText(parsedAuthor);
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

    /**
     * Responsible for setting up the profile toolbar
     */
    private void setupToolbar() {
        Toolbar toolbar =  findViewById(R.id.profileToolBar);
        setSupportActionBar(toolbar);

    }
}
