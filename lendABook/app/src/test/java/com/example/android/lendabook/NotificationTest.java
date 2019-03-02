package com.example.android.lendabook;

import com.example.android.lendabook.Notification1;

import org.junit.Test;
import static org.junit.Assert.*;

public class NotificationTest {
    @Test
    public void ConstructorTest() {
        Notification1 notification = new Notification1("the sky is blue");
        assertEquals("the sky is blue", notification.getNotifDesc());
    }

    public void TestsetNotifDesc() {
        Notification1 notification = new Notification1("the sky is blue");
        notification.setNotifDesc("another notification");
        assertEquals("another notification", notification.getNotifDesc());
    }
}
