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

/***
 * Class to test the AccountSettingActivity
 * @author cjbaker
 * @version 1.0
 */
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
    
    @Test
    public void editProfile() throws Exception{
        solo.clickOnText("Edit Profile");
        solo.clearEditText((EditText) solo.getView(R.id.name) );
        solo.enterText((EditText) solo.getView(R.id.name), "Chris Baker");
        solo.clearEditText((EditText) solo.getView(R.id.website) );
        solo.enterText((EditText) solo.getView(R.id.website), "www.google.com");
        solo.clearEditText((EditText) solo.getView(R.id.description) );
        solo.enterText((EditText) solo.getView(R.id.description), "My name is Chris" );
        solo.clickOnText("Save");
        solo.waitForText("Information saved!");
    }


    @After
    public void tearDown() throws Exception{
        solo.finishOpenedActivities();
    }
}
