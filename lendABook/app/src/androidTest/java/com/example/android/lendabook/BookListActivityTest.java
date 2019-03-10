package com.example.android.lendabook;

import android.app.Activity;
import android.support.test.rule.ActivityTestRule;

import com.example.android.lendabook.Add.AddActivity;
import com.example.android.lendabook.Profile.BookListActivity;
import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

public class BookListActivityTest extends ActivityTestRule<BookListActivity> {
    private Solo solo;
    public BookListActivityTest() { super(BookListActivity.class);}

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

    @After
    public void tearDown() throws Exception{
        solo.finishOpenedActivities();
    }
}
