package com.example.android.lendabook;

import android.app.Activity;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.EditText;
import android.widget.ListView;
import junit.framework.Assert;
import static android.support.test.InstrumentationRegistry.getInstrumentation;

import com.example.android.lendabook.Add.AddActivity;
import com.example.android.lendabook.Home.HomeActivity;
import com.robotium.solo.Solo;
import com.robotium.solo.Solo;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


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
    public void checkBook(){

        solo.assertCurrentActivity("Wrong Activity", AddActivity.class);
        solo.enterText((EditText) solo.getView(R.id.input_book_title), "Game of Thrones");
        solo.enterText((EditText) solo.getView(R.id.input_author), "George R. R. Martin");
        solo.enterText((EditText) solo.getView(R.id.input_isbn), "123 456 789 00");
        solo.enterText((EditText) solo.getView(R.id.input_book_description), "Just like the TV show");

    }
    @Test
    public void hitHome(){
        solo.clickOnMenuItem("ic_house");
        solo.assertCurrentActivity("Wrong Activity", HomeActivity.class);
    }
    @After
    public void tearDown() throws Exception{
        solo.finishOpenedActivities();
    }
}
