package com.example.android.lendabook;

import android.app.Activity;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.EditText;
import android.widget.ListView;
import junit.framework.Assert;
import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertTrue;

import com.example.android.lendabook.Add.AddActivity;
import com.example.android.lendabook.Home.HomeActivity;
import com.example.android.lendabook.Profile.BookListActivity;
import com.robotium.solo.Solo;
import com.robotium.solo.Solo;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

/***
 * Class to test the AddActivity
 * @author cjbaker
 * @version 1.0
 */

public class AddActivityTest extends ActivityTestRule<AddActivity>{

    private Solo solo;
    public AddActivityTest() {super(AddActivity.class);}

    @Rule
    public ActivityTestRule<AddActivity> rule =
            new ActivityTestRule<>(AddActivity.class, true, true);

    @Before
    public void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), rule.getActivity());
    }

    @Test
    public void start() throws Exception{
        Activity activity = rule.getActivity();
    }



    @Test
    public void addBook(){

        solo.assertCurrentActivity("Wrong Activity", AddActivity.class);
        solo.enterText((EditText) solo.getView(R.id.input_book_title), "Game of Thrones 2");
        solo.enterText((EditText) solo.getView(R.id.input_author), "George R. R. Martin");
        solo.enterText((EditText) solo.getView(R.id.input_isbn), "123 456 789 00");
        solo.enterText((EditText) solo.getView(R.id.input_book_description), "Just like the TV show");
        solo.clickOnText("Add to my books");
        solo.assertCurrentActivity("Wrong Activity", BookListActivity.class);

    }

    //Test to see if when clicking on an item in your books list it takes you back to the add activity
    @Test
    public void editBook(){
        solo.assertCurrentActivity("Wrong Activity", AddActivity.class);
        solo.enterText((EditText) solo.getView(R.id.input_book_title), "Game of Thrones 3");
        solo.enterText((EditText) solo.getView(R.id.input_author), "George R. R. Martin");
        solo.enterText((EditText) solo.getView(R.id.input_isbn), "123 456 789 00");
        solo.enterText((EditText) solo.getView(R.id.input_book_description), "Just like the TV show");
        solo.clickOnText("Add to my books");
        solo.assertCurrentActivity("Wrong Activity", BookListActivity.class);
        solo.clickInList(0);
        solo.assertCurrentActivity("Wrong Activity",AddActivity.class);
    }
    @Test
    public void deleteBook(){
        solo.assertCurrentActivity("Wrong Activity", AddActivity.class);
        solo.enterText((EditText) solo.getView(R.id.input_book_title), "Game of Thrones 3");
        solo.enterText((EditText) solo.getView(R.id.input_author), "George R. R. Martin");
        solo.enterText((EditText) solo.getView(R.id.input_isbn), "123 456 789 00");
        solo.enterText((EditText) solo.getView(R.id.input_book_description), "Just like the TV show");
        solo.clickOnText("Add to my books");
        solo.assertCurrentActivity("Wrong Activity", BookListActivity.class);
        solo.clickLongInList(0);
        //You can see Book Deletes

    }

    @After
    public void tearDown() throws Exception{
        solo.finishOpenedActivities();
    }
}
