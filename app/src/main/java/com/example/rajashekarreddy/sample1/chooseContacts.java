package com.example.rajashekarreddy.sample1;

import android.content.ContentResolver;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class chooseContacts extends AppCompatActivity {
    List<String> con=new ArrayList<>();
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_contacts);
        ContentResolver cr = getContentResolver();
        AlertDialog.Builder builder = new AlertDialog.Builder(chooseContacts.this);
        con=getNumber(cr);
        final boolean[] checkedContacts = new boolean[con.size()];

        final List<String> colorsList = con;

        String[] d=con.parallelStream().toArray(String[]::new);
        builder.setMultiChoiceItems(d, checkedContacts, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which, boolean b) {
                checkedContacts[which]=b;
                String currentItem=colorsList.get(which);
                Toast.makeText(chooseContacts.this, "just contacts selected", Toast.LENGTH_SHORT).show();
            }
        });

builder.setCancelable(false);
builder.setTitle("Select contacts for blocking");

builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
    @Override
    public void onClick(DialogInterface dialogInterface, int i) {

    }
});


builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
    @Override
    public void onClick(DialogInterface dialogInterface, int i) {

    }
});

builder.setNeutralButton("cancel", new DialogInterface.OnClickListener() {
    @Override
    public void onClick(DialogInterface dialogInterface, int i) {

    }
});

   AlertDialog dialog = builder.create();
   dialog.show();




    }
    public List<String> getNumber(ContentResolver cr)
    {
        List<String> contacts = new ArrayList<>();
        Cursor phones = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null, null);
        while (phones.moveToNext())
        {
            String name=phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            contacts.add(name+" "+phoneNumber);
        }
        phones.close();// close cursor
        return contacts;
        //display contact numbers in the list
    }
}
