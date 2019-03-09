package com.example.android.lendabook.Profile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.android.lendabook.Book;
import com.example.android.lendabook.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BookListActivity extends AppCompatActivity {

    private FirebaseAuth Authentication;
    private DatabaseReference mRef;
    private FirebaseUser fbUser;
    private String books;
    // Array List of Book class filled with values gotten from firebase
    private ArrayList<Book> bookArrayList = new ArrayList<Book>(); // <<< Display this on the screen
    // ^^^^^ Display this on the screen^ ^^^^
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //initialise firebase
        Authentication = FirebaseAuth.getInstance();
        fbUser =  Authentication.getCurrentUser();
        mRef = FirebaseDatabase.getInstance().getReference().child("Users").child(fbUser.getUid());

        //get book list
        // gets the total number of books
        mRef.child("books").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("999", "in on datat chagne loop");
                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    Book book = new Book( ds.child("isbn").getValue().toString(),
                            ds.child("author").getValue().toString(),
                            ds.child("title").getValue().toString(),
                            ds.child("description").getValue().toString(),
                            ds.child("status").getValue().toString(),
                            ds.child("borrower").getValue().toString());
                    bookArrayList.add(book);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

    }

}
