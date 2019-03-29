package com.example.android.lendabook.Home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.lendabook.Book;
import com.example.android.lendabook.Profile.BookListActivity;
import com.example.android.lendabook.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by belachew on 2019-02-27.
 * Class for the home fragment that you see when you first log in.
 */

/**
 * First page shown when logged in. Not finished for this version. Only contains a temporary button to take you to the list of books you have.
 * When activity is loaded it downloads books from firebase and sorts them into arrays on the device for further processing. Probably not the
 * best way to do this.
 */

public class HomeFragment extends Fragment {
    Button btnBookList;
    private static final String TAG = "HomeFragment";

    //List of all books on FireBase
    public static ArrayList<Book> availableBooks; //books that I can borrow
    public static ArrayList<Book> borrowedBooks; //books that I have borrowed
    public static ArrayList<Book> requestedBooks; //books that I have requested
    public static ArrayList<Book> acceptedBooks; //my book that has been requested and I accepted the request of
    public static ArrayList<Book> lentBooks; //my book that I have given to someone
    public static ArrayList<Book>  myAvailableBooks; //books that i have made available
    public static String userName; //username of currecnt user

    /**
     * Initializes the home fragment.
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        btnBookList = (Button) view.findViewById(R.id.btn_bklist);
        btnBookList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("999", "updating books, username = "+ userName);
                Intent intent = new Intent(getContext(), BookListActivity.class);
                startActivity(intent);
            }
        });
        getUserName();
        getAllBooks();
        return view;
    }

    //gets all books from FireBase
    private void getAllBooks() {
        DatabaseReference bookRef = FirebaseDatabase.getInstance().getReference().child("Books");
        availableBooks = new ArrayList<Book>();
        borrowedBooks = new ArrayList<Book>();
        requestedBooks = new ArrayList<Book>();
        acceptedBooks = new ArrayList<Book>();
        lentBooks = new ArrayList<Book>();
        myAvailableBooks = new ArrayList<Book>();

        bookRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //goes through each book on firebase
                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    Book book = new Book( ds.child("title").getValue().toString(),
                            ds.child("isbn").getValue().toString(),
                            ds.child("author").getValue().toString(),
                            ds.child("description").getValue().toString(),
                            ds.child("owner").getValue().toString(),
                            ds.child("borrower").getValue().toString(),
                            ds.child("status").getValue().toString(),
                            (ArrayList<String>) ds.child("requests").getValue());
                    //sorts book into local arrays based on their variables
                    if (book.getStatus().equals("available") & !book.getOwner().equals(userName)){
                        availableBooks.add(book);
                    }
                    else if (book.getStatus().equals("available") & book.getOwner().equals(userName)){
                        myAvailableBooks.add(book);
                    }
                    else if (book.getStatus().equals("borrowed") & book.getBorrower().equals(userName)){
                        borrowedBooks.add(book);
                    }
                    else if (book.getStatus().equals("lent") & book.getOwner().equals(userName)){
                        lentBooks.add(book);
                    }
                    else if (book.getStatus().equals("accepted") & book.getOwner().equals(userName)){
                        acceptedBooks.add(book);
                    }
                    else if (book.getStatus().equals("requested")){
                        requestedBooks.add(book);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public void getUserName(){
        // gets username
        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference("Users");
        FirebaseAuth Authentication = FirebaseAuth.getInstance();
        FirebaseUser user = Authentication.getCurrentUser();
        Query query = mRef.child(user.getUid());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userName = (String) dataSnapshot.child("username").getValue().toString();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
