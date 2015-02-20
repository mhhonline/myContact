package com.nightwaresystems.mycontacts;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContactListFragment extends Fragment
{
    private ContactList mContacts;
    private ContactAdapter mAdapter;
    private Contract mContract;

    public ContactListFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_contacl_list, container, false);
        //To create Custom List View:
        //1-create Array List to handle the list
//        mContacts = new ArrayList<Contact>();
        mContacts = ContactList.getInstance();

        //for scroll
        for (int i = 0; i < 30; i++) {
            //2-Create a Java Class to handle all attribute on the list e.g name ..
            Contact contact1 = new Contact();

            //3- add item to your list
            contact1.setName("Mohammed Hassan");
            contact1.emails = new ArrayList<String>();
            contact1.emails.add("mhhonline@gmail.com");
            contact1.emails.add("mhhonline_2@gmail.com");
            contact1.phoneNumbers = new ArrayList<String>();
            contact1.phoneNumbers.add("0506882301");
            contact1.phoneNumbers.add("+966506882301");
            mContacts.add(contact1);

        }

        //4-Fitch the item from GUI to Java
        ListView listview = (ListView) v.findViewById(R.id.contact_list_view);

        //5- add your list to the customized adapter
        mAdapter = new ContactAdapter(mContacts);
        listview.setAdapter(mAdapter);

        //to make the actionbar disappear
        listview.setOnScrollListener(new AbsListView.OnScrollListener()
        {
            int previousFirstItem = 0;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState)
            {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount)
            {
                ActionBar ab = ((ActionBarActivity) getActivity()).getSupportActionBar();
                if (firstVisibleItem > previousFirstItem) {
                    //getActionBar() used only if you don't have a support lib
                    //else use
//                    getSupportActionBar().hide(); used with activity
                    ab.hide();
                } else if (firstVisibleItem < previousFirstItem) {
//                    getSupportActionBar().show(); used with activity
                    ab.show();
                }

                previousFirstItem = firstVisibleItem;
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                if(mContacts != null)
                    mContract.selectedPosition(position);
            }
        });


        return v;
    }


    @Override
    public void onResume()
    {
        super.onResume();
        mAdapter.notifyDataSetChanged();
    }


    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
       try
       {
            mContract = (Contract)getActivity();
       }
       catch (ClassCastException e)
       {
           throw new IllegalStateException("Activity does not implement contract");
       }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        mContract = null;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_contact_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class ContactAdapter extends ArrayAdapter<Contact>
    {
        ContactAdapter(ArrayList<Contact> contacts)
        {
            super(getActivity(), R.layout.contact_list_row, R.id.contact_row_name, contacts);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            convertView = super.getView(position, convertView, parent);
            Contact contact = getItem(position);
            TextView nameTextView = (TextView) convertView.findViewById(R.id.contact_row_name);
            nameTextView.setText(contact.getName());
            return convertView;
        }
    }

    public interface Contract
    {
        public void selectedPosition(int position);
    }


}
