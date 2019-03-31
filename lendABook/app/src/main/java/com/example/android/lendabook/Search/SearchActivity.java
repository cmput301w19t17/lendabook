package com.example.android.lendabook.Search;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.lendabook.Book;
import com.example.android.lendabook.LogIn.RegisterActivity;
import com.example.android.lendabook.Profile.BookListActivity;
import com.example.android.lendabook.R;
import com.example.android.lendabook.Utils.BottomNavigationViewHelper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

/**
 * Created by belachew on 2019-03-05.
 * Class for the Search in the bottom navigation bar of our app.
 */

/**
 * This class is for the Search activity where you can search a book by Author or Book name and receive:
 * - book description, book owner and current book status.
 * The class does a query for the book and author and if one of the other matches, it inputs it in our results list with the above information.
 */

public class SearchActivity extends AppCompatActivity {
    private static final String TAG = "SearchActivity";
    private Context mContext = SearchActivity.this;
    private static final int ACTIVITY_NUM = 1;

    private FirebaseAuth Authentication;
    private DatabaseReference mRef;

    //Search result of Books
    private ArrayList<SearchListEntry> searchResultBooks;
    private ListView searchResult;
    private ArrayAdapter<SearchListEntry> adapter;

    private EditText searchText;
    private Button searchButton;
    /**
     * Initializes the layout of the Search page.
     *
     * @param savedInstanceState
     */

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Log.d(TAG, "onCreate: started");

        searchText = (EditText) findViewById(R.id.search);
        searchButton = (Button) findViewById(R.id.searchBtn);

            //get instance of firebase for authentication and real time database
        Authentication = FirebaseAuth.getInstance();
        mRef = FirebaseDatabase.getInstance().getReference();

        //setup list, listview and adapter and link adapter to listview
        searchResult = (ListView) findViewById(R.id.searchList);
        searchResultBooks = new ArrayList<SearchListEntry>();
        adapter = new ArrayAdapter<SearchListEntry>(SearchActivity.this, android.R.layout.simple_list_item_1, searchResultBooks);
        searchResult.setAdapter(adapter);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchForMatch();
            }
        });

        setUpBottomNavigationView();
    }


    /**
     * The search bar searches for matches based on a keyword.
     */
    private void searchForMatch() {
        String search = searchText.getText().toString();

        //getting current firebase user.
        FirebaseUser user = Authentication.getCurrentUser();

        //SIMPLY AN EXAMPLE FOR DEMO! The searching is not complete!
        //new Measurement object instance with input from above.
        SearchListEntry newEntry = new SearchListEntry("Harry Potter","Harry Potter thinks he is an ordinary boy--until he is rescued by an owl, taken to Hogwarts School of Witchcraft and Wizardry, learns to play Quidditch and does battle in a deadly duel.", "Hwarinho","borrowed");
        //append this new Measurement object to list.
        searchResultBooks.add(newEntry);

        adapter.notifyDataSetChanged();

        //Firebase query for the book with a certain name.
        mRef = FirebaseDatabase.getInstance().getReference("Users");
        Query query = mRef.child("books").orderByChild("author").equalTo(search);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
    
    //hideSoftKeyboard function for inputs.
    private void hideSoftKeyboard() {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
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
}
