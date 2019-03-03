package com.example.android.lendabook;

import android.app.Activity;
import android.support.test.rule.ActivityTestRule;

import com.example.android.lendabook.LogIn.RegisterActivity;
import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

public class RegisterActivityTest extends ActivityTestRule<RegisterActivity> {

    private Solo solo;
    public RegisterActivityTest(){ super(RegisterActivity.class);}

    @Rule

    public ActivityTestRule<RegisterActivity> rule =
            new ActivityTestRule<>(RegisterActivity.class, true, true);

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
