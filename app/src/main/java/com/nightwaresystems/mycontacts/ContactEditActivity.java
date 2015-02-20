package com.nightwaresystems.mycontacts;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class ContactEditActivity extends ActionBarActivity {
    public static final String EXTRA = "CEA_Contact";
    private EditText mEditName;
    private Contact mContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_edit);

//        mContact = (Contact) getIntent().getSerializableExtra(EXTRA);
        int position = getIntent().getIntExtra(EXTRA, 0);
        mContact = ContactList.getInstance().get(position);

        Toolbar toolbar = (Toolbar) findViewById(R.id.contact_edit_toolbar);
        toolbar.setTitle(getResources().getString(R.string.edit_contact));
        toolbar.setNavigationIcon(R.drawable.ic_done);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText mEditName = (EditText) findViewById(R.id.contact_edit_name);
                mContact.setName(mEditName.getText().toString());
                mContact.phoneNumbers = getSectionValues(R.id.phone_number_section);
                mContact.emails = getSectionValues(R.id.email_section);

                Toast.makeText(ContactEditActivity.this, "Contact Saved", Toast.LENGTH_LONG)
                        .show();
                finish();
            }
        });

        EditText mEditName = (EditText) findViewById(R.id.contact_edit_name);
        mEditName.setText(mContact.getName());

        addToSection(R.id.phone_number_section, mContact.phoneNumbers);
        addToSection(R.id.email_section, mContact.emails);

        TextView addNewPhoneNumber = (TextView) findViewById(R.id.add_new_phone_number);
        addNewPhoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToSection(R.id.phone_number_section, "");
            }
        });
        TextView addNewEmail = (TextView) findViewById(R.id.add_new_phone_number);
        addNewEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToSection(R.id.email_section, "");
            }
        });


    }

    private ArrayList<String> getSectionValues(int sectionId) {
        ArrayList<String> values = new ArrayList<String>();
        LinearLayout section = (LinearLayout) findViewById(sectionId);
        for (int i = 0; i < section.getChildCount(); i++) {
            EditText edit = (EditText) section.getChildAt(i);
            values.add(edit.getText().toString());
        }
        return values;
    }

    private void addToSection(int sectionId, String value) {
        LinearLayout section = (LinearLayout) findViewById(sectionId);
        EditText et = new EditText(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        et.setLayoutParams(lp);
        et.setText(value);
        section.addView(et);

    }

    private void addToSection(int sectionId, ArrayList<String> values) {
        LinearLayout section = (LinearLayout) findViewById(sectionId);
        for (int i = 0; i < values.size(); i++) {
            EditText et = new EditText(this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            et.setLayoutParams(lp);
            et.setText(values.get(i));
            section.addView(et);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contact_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
}
