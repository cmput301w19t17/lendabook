package com.example.android.lendabook;
import android.app.Activity;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.EditText;
import android.widget.ListView;
import junit.framework.Assert;
import static android.support.test.InstrumentationRegistry.getInstrumentation;

import com.robotium.solo.Solo;
import com.robotium.solo.Solo;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.example.android.lendabook.Home.HomeActivity;
@RunWith(AndroidJUnit4.class)

public class HomeActivityTest extends ActivityTestRule<HomeActivity> {
    private Solo solo;
    public HomeActivityTest() { super(HomeActivity.class);}

    @Rule
    public ActivityTestRule<HomeActivity> rule =
            new ActivityTestRule<>(HomeActivity.class, true, true);

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
