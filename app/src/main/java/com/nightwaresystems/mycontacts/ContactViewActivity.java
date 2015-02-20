package com.nightwaresystems.mycontacts;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;


public class ContactViewActivity extends ActionBarActivity {

    public static final String EXTRA = ContactViewActivity.EXTRA;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_view);

        if(getFragmentManager().findFragmentById(R.id.view_fragment_container) == null)
        {
            getFragmentManager().beginTransaction()
                    .add(R.id.view_fragment_container,new ContactListFragment())
                    .commit();
        }

    }
}
