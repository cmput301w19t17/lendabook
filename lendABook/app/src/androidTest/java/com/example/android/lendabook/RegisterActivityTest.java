package com.example.android.lendabook;

import android.app.Activity;
import android.support.test.rule.ActivityTestRule;
import android.widget.EditText;

import com.example.android.lendabook.LogIn.LogInActivity;
import com.example.android.lendabook.LogIn.RegisterActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/***
 * Class to test the RegisterActivity
 * @author cjbaker
 * @version 1.0
 */
public class RegisterActivityTest extends ActivityTestRule<RegisterActivity> {

    private Solo solo;
    public RegisterActivityTest(){ super(RegisterActivity.class);}
    private FirebaseAuth Authentication;
    private DatabaseReference mRef;

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
    //register a duplicate username
    @Test
    public void registerDuplicate() throws Exception {
        solo.enterText((EditText) solo.getView(R.id.input_username), "juicy");
        solo.enterText((EditText) solo.getView(R.id.input_email), "hello@gmail.com");
        solo.enterText((EditText) solo.getView(R.id.input_full_name), "asdf");
        solo.enterText((EditText) solo.getView(R.id.input_password), "swimming");
        solo.clickOnText("Register :)");

        assertTrue(solo.waitForText("Username Taken"));
    }
    //Test new username
    @Test
    public void Register() throws Exception{
        solo.enterText((EditText) solo.getView(R.id.input_username), "hello21134");
        solo.enterText((EditText) solo.getView(R.id.input_email), "example9@gmail.com");
        solo.enterText((EditText) solo.getView(R.id.input_full_name), "aasdf");
        solo.enterText((EditText) solo.getView(R.id.input_password), "swimming");
        solo.clickOnText("Register :)");
        assertTrue(solo.waitForText("Registration Successful"));
        solo.assertCurrentActivity("Wrong Activity", LogInActivity.class);
        Authentication = FirebaseAuth.getInstance();

        mRef = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = Authentication.getCurrentUser();








    }


    @After
    public void tearDown() throws Exception{
        solo.finishOpenedActivities();
    }


}
