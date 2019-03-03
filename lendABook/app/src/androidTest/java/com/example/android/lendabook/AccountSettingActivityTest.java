package com.example.android.lendabook;

import android.app.Activity;
import android.support.test.rule.ActivityTestRule;

import com.example.android.lendabook.Profile.AccountSettingActivity;
import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

public class AccountSettingActivityTest extends ActivityTestRule<AccountSettingActivity> {

    private Solo solo;
    public AccountSettingActivityTest() { super(AccountSettingActivity.class);}

    @Rule
    public ActivityTestRule<AccountSettingActivity> rule =
            new ActivityTestRule<>(AccountSettingActivity.class, true, true);

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
