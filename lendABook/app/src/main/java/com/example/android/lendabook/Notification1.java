package com.example.android.lendabook;

/**
 * Created by kostin on 2019-03-04.
 * Class for notifications.
 */

/**
 * The class for info in notifications.
 */

public class Notification1 {
    private String notifDesc;
    
    /**
     * creates object of notification with string notifDesc
     *
     * @param notifDesc
     */
    public Notification1(String notifDesc) {
        this.notifDesc = notifDesc;
    }

    public String getNotifDesc() {
        return notifDesc;
    }

    public void setNotifDesc(String notifDesc) {
        this.notifDesc = notifDesc;
    }
}

