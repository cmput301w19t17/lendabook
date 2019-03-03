package com.example.android.lendabook;

import android.app.Activity;
import android.provider.ContactsContract;
import android.support.test.rule.ActivityTestRule;

import com.example.android.lendabook.Profile.ProfileActivity;
import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

public class ProfileActivityTest extends ActivityTestRule<ProfileActivity> {

    private Solo solo;
    public ProfileActivityTest(){ super(ProfileActivity.class);}

    @Rule
    public ActivityTestRule<ProfileActivity> rule =
            new ActivityTestRule<>(ProfileActivity.class, true, true);

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
