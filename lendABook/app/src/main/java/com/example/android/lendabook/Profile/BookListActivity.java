package com.example.android.lendabook.Profile;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.lendabook.Add.AddActivity;
import com.example.android.lendabook.Book;
import com.example.android.lendabook.LogIn.LogInActivity;
import com.example.android.lendabook.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Displays books associated with your account.
 * Long pressing a book deletes it.
 * Taping a book lets you edit it.
 * 4 Buttons to filter books by status.
 * Temporary UI for this version
 * Based on KostinCardioBook
 * Made by Kostin
 */

public class BookListActivity extends AppCompatActivity {

    private FirebaseAuth Authentication;
    private DatabaseReference mRef;
    private FirebaseUser fbUser;
    private String books;
    private String filter = "";


    // Array List of Book class filled with values gotten from firebase
    private ArrayList<Book> bookArrayList = new ArrayList<Book>(); // <<< Display this on the screen
    // ^^^^^ Display this on the screen^ ^^^^

    //temporary display for version 4
    // copied from kostinCardioBook
    private ListView oldEntryList;
    private ArrayAdapter<Book> adapter;
    private ArrayList<Book> filteredBookArrayList = new ArrayList<>();
    private Button btnAvailable;
    private Button btnRequested;
    private Button btnAccepted;
    private Button btnBorrowed;
    private Button btnAll;
    private int numBooks;
    private int adapterID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
        Toast.makeText(BookListActivity.this, "Long Press on book to delete it",Toast.LENGTH_LONG).show();
        oldEntryList = (ListView) findViewById(R.id.oldEntryList);
        //initialise firebase
        Authentication = FirebaseAuth.getInstance();
        fbUser =  Authentication.getCurrentUser();
        mRef = FirebaseDatabase.getInstance().getReference().child("Users").child(fbUser.getUid());


        // spinner / dropdown
        Spinner spinner = findViewById(R.id.spinner);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(BookListActivity.this,
                R.layout.spinner_item, getResources().getStringArray(R.array.book_status));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(myAdapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                if (adapterView.getSelectedItem().toString() == "Available") {
                        filter = "available";
                        displayBook();
                    }

                    else if (adapterView.getSelectedItem().toString() == "Requested") {
                        filter = "requested";
                        displayBook();
                    }

                    else if (adapterView.getSelectedItem().toString() == "Accepted") {
                        filter = "accepted";
                        displayBook();
                    }

                    else if (adapterView.getSelectedItem().toString() == "Borrowed") {
                        filter = "borrowed";
                        displayBook();
                    }

                    else if (adapterView.getSelectedItem().toString() == "All") {
                        filter = "";
                        displayBook();
                    }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

/*
        //buttons
        btnAccepted = (Button) findViewById(R.id.btnAccepted);
        btnAccepted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filter = "accepted";
                displayBook();
            }
        });
        btnAvailable = (Button) findViewById(R.id.btnAvailable);
        btnAvailable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filter = "available";
                displayBook();
            }
        });

        btnBorrowed = (Button) findViewById(R.id.btnBorrowed);
        btnBorrowed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filter = "borrowed";
                displayBook();
            }
        });

        btnRequested = (Button) findViewById(R.id.btnRequested);
        btnRequested.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filter = "requested";
                displayBook();
            }
        });

        btnAll = (Button) findViewById(R.id.btnAll);
        btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filter = "";
                displayBook();
            }
        });

        */

        Log.d("999", String.valueOf(numBooks));
        mRef.child("num_books").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    numBooks = dataSnapshot.getValue(int.class);
                }
                catch(Exception e){
                    mRef.child("num_books").setValue("0");
                    numBooks = 0;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        //clicking on book
        oldEntryList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(BookListActivity.this, "Book Deleted",Toast.LENGTH_SHORT).show();
                if (adapterID == 0){
                    mRef.child("books").child(filteredBookArrayList.get((int) id).getFirebaseID()).removeValue();
                }
                else {
                    mRef.child("books").child(bookArrayList.get((int) id).getFirebaseID()).removeValue();
                }
                numBooks -= 1;
                mRef.child("num_books").setValue(numBooks);
                return true;
            }
        });

        oldEntryList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(BookListActivity.this, AddActivity.class);
                if (adapterID == 0){
                    intent.putExtra("fireBaseID", filteredBookArrayList.get((int) id).getFirebaseID());
                } else{
                    intent.putExtra("fireBaseID", bookArrayList.get((int) id).getFirebaseID());
                }
                intent.putExtra("cameFrom", 2);
                startActivity(intent);
            }
        });

        //get book list
        // gets the total number of books
        mRef.child("books").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                bookArrayList.clear();
                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    Book book = new Book( ds.child("isbn").getValue().toString(),
                            ds.child("author").getValue().toString(),
                            ds.child("title").getValue().toString(),
                            ds.child("description").getValue().toString(),
                            ds.child("status").getValue().toString(),
                            ds.child("borrower").getValue().toString(),
                            ds.child("firebaseID").getValue().toString());
                    bookArrayList.add(book);
                }
                displayBook();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

    }

    private void displayBook(){
        // if filter is set
        if (filter != ""){
            filteredBookArrayList.clear();
            // go through each book in the fill array list
            for (int i = 0; i < bookArrayList.size(); i++){
                // if the books status is the same as the filter
                if (bookArrayList.get(i).getStatus().equals(filter)){
                    // add that book to the new arraylist that only contains the filtered books
                    filteredBookArrayList.add(bookArrayList.get(i));
                }
            }
            adapterID = 0;
            adapter = new ArrayAdapter<Book>(this, R.layout.list_item, filteredBookArrayList);
        }
        else{
            adapter = new ArrayAdapter<Book>(this, R.layout.list_item, bookArrayList);
            adapterID = 1;
        }
        oldEntryList.setAdapter(adapter);
    }


}
