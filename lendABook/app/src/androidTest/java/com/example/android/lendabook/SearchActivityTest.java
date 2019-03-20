package com.example.android.lendabook;

import android.app.Activity;
import android.support.test.rule.ActivityTestRule;

import com.example.android.lendabook.Search.SearchActivity;
import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

/***
 * Class to test the SearchActivity
 * @author cjbaker
 * @version 1.0
 */
public class SearchActivityTest extends ActivityTestRule<SearchActivity> {

    private Solo solo;
    public SearchActivityTest() { super(SearchActivity.class);}

    @Rule
    public ActivityTestRule<SearchActivity> rule =
            new ActivityTestRule<>(SearchActivity.class, true, true);

    @Before
    public void setUp() throws Exception{
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
