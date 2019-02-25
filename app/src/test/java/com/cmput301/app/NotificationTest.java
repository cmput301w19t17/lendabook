package com.cmput301.app;

import org.junit.Test;
import static org.junit.Assert.*;

public class NotificationTest {
    @Test
    public void ConstructorTest() {
        Notification notification = new Notification("the sky is blue");
        assertEquals("the sky is blue", notification.getNotifDesc());
    }

    public void TestsetNotifDesc() {
        Notification notification = new Notification("the sky is blue");
        notification.setNotifDesc("another notification");
        assertEquals("another notification", notification.getNotifDesc());
    }
}
