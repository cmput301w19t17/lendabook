package com.example.android.lendabook.Add;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.lendabook.Book;
import com.example.android.lendabook.Profile.BookListActivity;
import com.example.android.lendabook.R;
import com.example.android.lendabook.User;
import com.example.android.lendabook.Utils.BottomNavigationViewHelper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.io.IOException;
/**
 * Created by kostin on 2019-03-01.
 * Class for interaction between scanning and filling out info about book in add book activity.
 */

/**
    Scan book, get information from google API and then populate the editText in AddBook activity.
 */

public class AddActivity extends AppCompatActivity {
    private static final String TAG = "AddActivity";
    private Context mContext = AddActivity.this;
    private static final int ACTIVITY_NUM = 2;
    private static final int RESULT_LOAD_IMAGE = 1;

    private EditText tilteBox;
    private EditText isbnBox;
    private  EditText descBox;
    private EditText authorBox;
    private EditText statusBox;

    private String userName;

    private int cameFrom; //0 = add buttor, 1 = scan isnb, 2 = edit book

    private FirebaseAuth Authentication;
    private DatabaseReference bookListRef;
    private FirebaseUser fbUser;

    private String fireBaseID;
    
    /**
     * Does initializaton of the activity, scanning, adding to slots in addBook activity
     *
     * @param savedInstanceState
     */
    Button btnAdd;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Log.d(TAG, "onCreate: started");
        TextView include_picture = findViewById(R.id.include_picture_text_view);
        final ImageView remove_image = findViewById(R.id.remove_image);
        include_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPhoto();
            }
        });

        remove_image.setVisibility(View.GONE);

        setupToolbar();
        setUpBottomNavigationView();



        // text fields
        statusBox = (EditText) findViewById(R.id.input_status);
        tilteBox = (EditText) findViewById(R.id.input_book_title);
        isbnBox = (EditText) findViewById(R.id.input_isbn);
        descBox = (EditText) findViewById(R.id.input_book_description);
        authorBox = (EditText) findViewById(R.id.input_author);

        //fire base
        Authentication = FirebaseAuth.getInstance();
        fbUser =  Authentication.getCurrentUser();
        bookListRef = FirebaseDatabase.getInstance().getReference().child("Books");


        // figure out where user came from
        Intent intent = getIntent();
        cameFrom = intent.getIntExtra("cameFrom", 999);
        Log.d("999", "Came From: " + String.valueOf(cameFrom));
        if (cameFrom == 2){
            fireBaseID = intent.getStringExtra("fireBaseID");
            DatabaseReference fillRef = bookListRef.child("books").child(fireBaseID);
            fillRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    fillTextView(dataSnapshot.child("title").getValue().toString(),
                            dataSnapshot.child("author").getValue().toString(),
                            dataSnapshot.child("isbn").getValue().toString(),
                            dataSnapshot.child("status").getValue().toString(),
                            dataSnapshot.child("description").getValue().toString());
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });

        }
        if (cameFrom == 1){
            // fills text boxes with data from search
            String[] searchResults = intent.getStringArrayExtra("searchResults");
            //Log.d("999", "searchResuts: " + searchResults.toString());
            Log.d("999", "scanned barcode");
            fillTextView(searchResults[0], searchResults[1], searchResults[2], "borrowed", searchResults[4]);
        }

        // add button
        btnAdd = (Button) findViewById(R.id.btn_register);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String isbnText = isbnBox.getText().toString();
                String titleText = tilteBox.getText().toString();
                String descText = descBox.getText().toString();
                String authorText = authorBox.getText().toString();
                String statusText = statusBox.getText().toString();
                addEntry(titleText, isbnText, authorText, descText, userName, "none", statusText);
            }
        });
    }



    /**
     * Adds entry of books.
     *
     * @param isbn books' isbn

     */
    private void addEntry(String title, String isbn, String author,  String description, String owner, String borrower, String status) {
        /*// creates new book object
        Book book;
        if (status.equals("requested")){

            book = new Book(title, isbn, author, description, "none", borrower, status);
        } else if (status.equals("available")){
            book = new Book(title, isbn, author, description, owner, borrower, status);
            bookListRef.child(status).child(isbn).setValue(book);
        }*/
        Book book = new Book(title, isbn, author, description, owner, borrower, status);
        bookListRef.child(isbn+owner).setValue(book);

        // adds the book to book list on firebase
        /*
        Intent intent = new Intent(AddActivity.this, BookListActivity.class);
        startActivity(intent);
        */
    }

    /**
     * Fills text boxes with data from book api
     * I NEED TO CHANGE THIS, I THINK IT WORKS IF YOU UNCOMMENT IT BUT IT'S MESSY -Peter
     *
     * @param title
     * @param author
     * @param isbn
     * @param status
     * @param desc
     */
   private void fillTextView(String title, String author, String isbn, String status, String desc){
        isbnBox.setText(isbn);
        statusBox.setText(status);
        tilteBox.setText(title);
        descBox.setText(desc);
        authorBox.setText(author);
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

    private void getPhoto() {
        // open image picker
        Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
        getIntent.setType("image/*");

        Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickIntent.setType("image/*");

        Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});

        startActivityForResult(chooserIntent, RESULT_LOAD_IMAGE);
    }
    
     /**
     * finishes everything
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        final ImageView selected_image = findViewById(R.id.selected_image);
        final ImageView remove_image = findViewById(R.id.remove_image);
        remove_image.setVisibility(View.VISIBLE);

        remove_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected_image.setImageBitmap(null);
                remove_image.setVisibility(View.GONE);
            }
        });

        if(resultCode == Activity.RESULT_OK)
            switch (requestCode){
                case RESULT_LOAD_IMAGE:
                    Uri selectedImage = data.getData();
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                        selected_image.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        Log.i("TAG", "Some exception " + e);
                    }
                    break;
            }

    }
}
