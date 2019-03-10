package com.example.android.lendabook.Add;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.lendabook.R;
import com.example.android.lendabook.Utils.BottomNavigationViewHelper;
import com.example.android.lendabook.Utils.fetchData;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import static com.example.android.lendabook.Utils.fetchData.isbn;
import static com.example.android.lendabook.Utils.fetchData.parsedAuthor;
import static com.example.android.lendabook.Utils.fetchData.parsedDescription;
import static com.example.android.lendabook.Utils.fetchData.parsedTitle;

public class AddActivity extends AppCompatActivity {
    private static final String TAG = "AddActivity";
    private Context mContext = AddActivity.this;
    private static final int ACTIVITY_NUM = 2;
    private static final int RESULT_LOAD_IMAGE = 1;

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
        fillTextView();
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
     * Responsible for setting up the add toolbar
     */
    private void setupToolbar() {
        Toolbar toolbar =  findViewById(R.id.profileToolBar);
        setSupportActionBar(toolbar);

    }

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
