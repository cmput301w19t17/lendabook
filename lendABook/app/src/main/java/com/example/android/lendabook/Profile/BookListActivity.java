package com.example.android.lendabook.Profile;

import android.content.Intent;
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

import static com.example.android.lendabook.Home.HomeFragment.acceptedBooks;
import static com.example.android.lendabook.Home.HomeFragment.availableBooks;
import static com.example.android.lendabook.Home.HomeFragment.borrowedBooks;
import static com.example.android.lendabook.Home.HomeFragment.requestedBooks;

public class BookListActivity extends AppCompatActivity {

    //temporary display for version 4
    // copied from kostinCardioBook
    private ListView oldEntryList;
    private ArrayAdapter<Book> adapter;
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

        //buttons
        btnAccepted = (Button) findViewById(R.id.btnAccepted);
        btnAccepted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter = new ArrayAdapter<Book>(getApplicationContext(), R.layout.list_item, acceptedBooks);
                oldEntryList.setAdapter(adapter);
                Log.d("999", String.valueOf(acceptedBooks.isEmpty()));
            }
        });
        btnAvailable = (Button) findViewById(R.id.btnAvailable);
        btnAvailable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter = new ArrayAdapter<Book>(getApplicationContext(), R.layout.list_item, availableBooks);
                oldEntryList.setAdapter(adapter);
                Log.d("999", String.valueOf(availableBooks.isEmpty()));
            }
        });

        btnBorrowed = (Button) findViewById(R.id.btnBorrowed);
        btnBorrowed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter = new ArrayAdapter<Book>(getApplicationContext(), R.layout.list_item, borrowedBooks);
                oldEntryList.setAdapter(adapter);
                Log.d("999", String.valueOf(borrowedBooks.isEmpty()));
            }
        });

        btnRequested = (Button) findViewById(R.id.btnRequested);
        btnRequested.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter = new ArrayAdapter<Book>(getApplicationContext(), R.layout.list_item, requestedBooks);
                oldEntryList.setAdapter(adapter);
                Log.d("999", String.valueOf(requestedBooks.isEmpty()));
            }
        });
/*
        btnAll = (Button) findViewById(R.id.btnAll);
        btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filter = "";
                displayBook();
            }
        });

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
        });*/
    }
/*
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
*/
}
