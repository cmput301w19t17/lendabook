package com.example.android.lendabook;

import android.app.Activity;
import android.support.test.rule.ActivityTestRule;
import android.widget.EditText;

import com.example.android.lendabook.Add.AddActivity;
import com.example.android.lendabook.Home.HomeActivity;
import com.example.android.lendabook.LogIn.LogInActivity;
import com.example.android.lendabook.Profile.ProfileActivity;
import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertTrue;

/***
 * Class to test the LogInActivity
 * @author cjbaker
 * @version 1.0
 */
public class LogInActivityTest extends ActivityTestRule<LogInActivity> {

    private Solo solo;
    public LogInActivityTest() { super(LogInActivity.class);}

    @Rule
    public ActivityTestRule<LogInActivity> rule =
            new ActivityTestRule<>(LogInActivity.class, true, true);

    @Before
    public void setUp() throws Exception{
        solo = new Solo(getInstrumentation(), rule.getActivity());
    }

    @Test
    public void start() throws Exception{
        Activity activity = rule.getActivity();
    }


    //Log In with correct inputs
    @Test
    public void logIn() throws Exception{
        solo.enterText((EditText) solo.getView(R.id.input_email), "example12345@gmail.com");
        solo.enterText((EditText) solo.getView(R.id.input_password), "swimming");
        solo.clickOnText("Log In");
        solo.assertCurrentActivity("Wrong Activity", HomeActivity.class);

    }



    //Log In with incorrect Inputs
    @Test
    public void logInWrong() throws Exception{
        solo.enterText((EditText) solo.getView(R.id.input_email), "example");
        solo.enterText((EditText) solo.getView(R.id.input_password), "swimming");
        solo.clickOnText("Log In");
        assertTrue(solo.waitForText("LogIn Unsuccessful"));

    }

    @Test
    public void LogInNoEmail() throws Exception{
        solo.enterText((EditText) solo.getView(R.id.input_password), "swimming");
        solo.clickOnText("Log In");
        assertTrue(solo.waitForText("Email not entered"));
    }

    @Test
    public void LogInNoPassword() throws Exception{
        solo.enterText((EditText) solo.getView(R.id.input_email), "example1@gmail.com");
        solo.clickOnText("Log In");
        assertTrue(solo.waitForText("Password not entered"));
    }


    @After
    public void tearDown() throws Exception{
        solo.finishOpenedActivities();
    }



}
