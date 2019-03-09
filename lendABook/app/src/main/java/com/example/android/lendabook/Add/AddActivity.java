package com.example.android.lendabook.Add;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.lendabook.Book;
import com.example.android.lendabook.Profile.BookListActivity;
import com.example.android.lendabook.R;
import com.example.android.lendabook.Utils.BottomNavigationViewHelper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class AddActivity extends AppCompatActivity {
    private static final String TAG = "AddActivity";
    private Context mContext = AddActivity.this;
    private static final int ACTIVITY_NUM = 2;
    private EditText tilteBox;
    private EditText isbnBox;
    private  EditText descBox;
    private EditText authorBox;

    private int numBooks;

    private FirebaseAuth Authentication;
    private DatabaseReference mRef;
    private FirebaseUser fbUser;

    Button btnAdd;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Log.d(TAG, "onCreate: started");

        setupToolbar();
        setUpBottomNavigationView();

        // text fields
        tilteBox = (EditText) findViewById(R.id.input_book_title);
        isbnBox = (EditText) findViewById(R.id.input_isbn);
        descBox = (EditText) findViewById(R.id.input_book_description);
        authorBox = (EditText) findViewById(R.id.input_author);

        //fire base
        Authentication = FirebaseAuth.getInstance();
        fbUser =  Authentication.getCurrentUser();
        mRef = FirebaseDatabase.getInstance().getReference().child("Users").child(fbUser.getUid());

        // gets the total number of books
        mRef.child("num_books").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                numBooks = dataSnapshot.getValue(int.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        // add button
        btnAdd = (Button) findViewById(R.id.btn_register);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String isbnText = isbnBox.getText().toString();
                String titleText = tilteBox.getText().toString();
                String descText = descBox.getText().toString();
                String authorText = authorBox.getText().toString();
                addEntry(isbnText, titleText, descText, authorText, "requested", "Mr. Book Borrower");
            }
        });

        // fills text boxes with data from search
        //fillTextView();
    }

    private void addEntry(String isbnText, String titleText, String descText, String authorText, String status, String borrower) {
        // creates new book object
        Book book = new Book(isbnText, authorText, titleText, descText, status, borrower);
        // adds the book to book list on firebase and increase the number of book firebase
        mRef.child("books").child(String.valueOf(numBooks)).setValue(book);
        mRef.child("num_books").setValue(numBooks+1);
        Intent intent = new Intent(AddActivity.this, BookListActivity.class);
        startActivity(intent);

    }


    // fills text boxes with data from book api
    // I NEED TO CHANGE THIS, I THINK IT WORKS IF YOU UNCOMMENT IT BUT IT'S MESSY -Peter
   /*private void fillTextView(){
        Log.d("999: @filltextview", parsedTitle);
        isbnBox.setText(isbn);
        tilteBox.setText(parsedTitle);
        descBox.setText(parsedDescription);
        authorBox.setText(parsedAuthor);
    }*/

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
