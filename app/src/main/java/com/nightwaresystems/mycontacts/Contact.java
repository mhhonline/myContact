package com.nightwaresystems.mycontacts;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by mhhonline on 2/5/2015.
 */
public class Contact implements Serializable
{
    private String mName;
    public ArrayList <String> emails;
    public ArrayList <String> phoneNumbers;

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }




}
