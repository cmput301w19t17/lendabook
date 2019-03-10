package com.example.android.lendabook;

import android.app.Activity;
import android.support.test.rule.ActivityTestRule;

import com.example.android.lendabook.Notification.NotificationActivity;
import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

/***
 * Class to test the NotificationActivity
 * @author cjbaker
 * @version 1.0
 */
public class NotificationActivityTest extends ActivityTestRule<NotificationActivity> {

    private Solo solo;
    public NotificationActivityTest() { super(NotificationActivity.class);}

    @Rule
    public ActivityTestRule<NotificationActivity> rule =
            new ActivityTestRule<>(NotificationActivity.class, true, true);

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
