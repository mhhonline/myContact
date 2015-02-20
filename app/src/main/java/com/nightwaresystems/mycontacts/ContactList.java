package com.nightwaresystems.mycontacts;

import java.util.ArrayList;

/**
 * Created by mhhonline on 2/6/2015.
 */
public class ContactList extends ArrayList<Contact> {
    private static ContactList sInstance = null;

    private ContactList() {
    }


    public static ContactList getInstance() {
        if (sInstance == null)
            sInstance = new ContactList();
        return sInstance;
    }
}
