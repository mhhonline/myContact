package com.nightwaresystems.mycontacts;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;


public class ContactListActivity extends ActionBarActivity implements ContactListFragment.Contract
{


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        if(getFragmentManager().findFragmentById(R.id.list_fragment_container) == null)
        {
            ContactViewFragment cvf = new ContactViewFragment();
            cvf.setArguments(getIntent().getExtras());
            getFragmentManager().beginTransaction()
                    .add(R.id.list_fragment_container,cvf)
                    .commit();
        }

    }

    @Override
    public void selectedPosition(int position)
    {
        Intent i = new Intent(this, ContactViewActivity.class);
        i.putExtra(ContactViewActivity.EXTRA, position);
        startActivity(i);
    }
}
