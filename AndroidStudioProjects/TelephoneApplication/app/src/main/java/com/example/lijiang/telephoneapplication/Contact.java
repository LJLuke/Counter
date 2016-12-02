package com.example.lijiang.telephoneapplication;

import android.content.SharedPreferences;

/**
 * Created by lijiang on 2016/11/28.
 */
public class Contact {
    private String mContactName;
    private String mContactNumber;

    public String getContactName() {
        return mContactName;
    }

    public void setContactName(String contactName) {
        mContactName = contactName;
    }

    public String getContactNumber() {
        return mContactNumber;
    }

    public void setContactNumber(String contactNumber) {
        mContactNumber = contactNumber;
    }
}
