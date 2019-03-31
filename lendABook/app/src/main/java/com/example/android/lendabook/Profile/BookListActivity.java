package com.example.android.lendabook.Profile;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.lendabook.Book;
import com.example.android.lendabook.R;
import com.example.android.lendabook.Utils.BarcodeScanner;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.ArrayList;

import static com.example.android.lendabook.Home.HomeFragment.acceptedBooks;
import static com.example.android.lendabook.Home.HomeFragment.availableBooks;
import static com.example.android.lendabook.Home.HomeFragment.borrowedBooks;
import static com.example.android.lendabook.Home.HomeFragment.lentBooks;
import static com.example.android.lendabook.Home.HomeFragment.myAvailableBooks;
import static com.example.android.lendabook.Home.HomeFragment.requestedBooks;
import static com.example.android.lendabook.Home.HomeFragment.userName;

/**
 * Displays all books based on button pressed
 */

public class BookListActivity extends AppCompatActivity {

    //temporary display for version 4
    // copied from kostinCardioBook
    private ListView oldEntryList;
    private ArrayAdapter<Book> adapter;
    private ArrayAdapter<String> adapterString;
    private Button btnAvailable;
    private Button btnRequested;
    private Button btnAccepted;
    private Button btnBorrowed;
    private Button btnMyAvailable;
    private Button btnLent;
    private int numBooks;
    private int adapterID;
    private String displaying = "";
    private DatabaseReference bookRef = FirebaseDatabase.getInstance().getReference().child("Books");
    private Book selectedBook;
    public static Book globalBook; //couldnt figure out how to pass this using put extra

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
                displaying = "accepted";
                Log.d("999", String.valueOf(acceptedBooks.isEmpty()));
            }
        });
        btnAvailable = (Button) findViewById(R.id.btnAvailable);
        btnAvailable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter = new ArrayAdapter<Book>(getApplicationContext(), R.layout.list_item, availableBooks);
                oldEntryList.setAdapter(adapter);
                displaying = "available";
                Log.d("999", String.valueOf(availableBooks.isEmpty()));
            }
        });

        btnBorrowed = (Button) findViewById(R.id.btnBorrowed);
        btnBorrowed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter = new ArrayAdapter<Book>(getApplicationContext(), R.layout.list_item, borrowedBooks);
                oldEntryList.setAdapter(adapter);
                displaying = "borrowed";
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

        btnMyAvailable= (Button) findViewById(R.id.btnMyAvailable);
        btnMyAvailable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("999", String.valueOf(myAvailableBooks.isEmpty()));
                adapter = new ArrayAdapter<Book>(getApplicationContext(), R.layout.list_item, myAvailableBooks);
                oldEntryList.setAdapter(adapter);
                displaying = "MAB";

            }
        });

        btnLent = (Button) findViewById(R.id.btnLent);
        btnLent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displaying = "lent";
                adapter = new ArrayAdapter<Book>(getApplicationContext(), R.layout.list_item, lentBooks);
                oldEntryList.setAdapter(adapter);
            }
        });


        //clicking on book
        oldEntryList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (displaying.equals("MAB"))
                Toast.makeText(BookListActivity.this, "Book Deleted",Toast.LENGTH_SHORT).show();
                bookRef.child(myAvailableBooks.get((int) id).getIsbn()).removeValue();
                myAvailableBooks.remove(myAvailableBooks.get((int) id));
                return true;
            }
        });

        oldEntryList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book clickedBook;
                if (displaying.equals("available")){
                    clickedBook = availableBooks.get((int) id);
                    clickedBook.addRequest(userName);
                    bookRef.child(clickedBook.getIsbn()).setValue(clickedBook);
                    //if you want to add notifications you would send one here I guess
                }   else if (displaying.equals("MAB")){
                    selectedBook = myAvailableBooks.get((int) id);
                    ArrayList<String> requestsArray = selectedBook.getRequests();
                    adapterString = new ArrayAdapter<String>(getApplicationContext(), R.layout.list_item, requestsArray);
                    oldEntryList.setAdapter(adapterString);
                    displaying = "requests";
                }   else if (displaying.equals("requests")){
                    selectedBook.setBorrower(selectedBook.getRequests().get((int) id));
                    selectedBook.clearRequests();
                    selectedBook.addRequest("   ");
                    selectedBook.setStatus("accepted");
                    bookRef.child(selectedBook.getIsbn()).setValue(selectedBook);
                    acceptedBooks.add(selectedBook);
                    adapter = new ArrayAdapter<Book>(getApplicationContext(), R.layout.list_item, acceptedBooks);
                    oldEntryList.setAdapter(adapter);
                    displaying = "accepted";
                } else if (displaying.equals("accepted")){
                    Intent intent = new Intent(BookListActivity.this, BarcodeScanner.class);
                    clickedBook = acceptedBooks.get((int) id);
                    globalBook = acceptedBooks.get((int) id);
                    intent.putExtra("desiredISBN", clickedBook.getIsbn());
                    intent.putExtra("status", "lending");
                    startActivity(intent);
                }else if (displaying.equals("borrowed")){
                    Intent intent = new Intent(BookListActivity.this, BarcodeScanner.class);
                    clickedBook = acceptedBooks.get((int) id);
                    globalBook = acceptedBooks.get((int) id);
                    intent.putExtra("desiredISBN", clickedBook.getIsbn());
                    intent.putExtra("status", "returning");
                    startActivity(intent);
                }
            }
        });
    }
}
