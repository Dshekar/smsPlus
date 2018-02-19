package com.example.rajashekarreddy.sample1;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;


public class chooseContacts extends Activity {
    List<String> con=new ArrayList<>();
    List<String> currentItem=new ArrayList<>();
    DatabaseHelper dbhp;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_choose_contacts);
        ContentResolver cr = getContentResolver();
        dbhp=new DatabaseHelper(this);

        AlertDialog.Builder builder = new AlertDialog.Builder(chooseContacts.this);
        con=getNumber(cr);
        final boolean[] checkedContacts = new boolean[con.size()];
        final List<String> colorsList = con;
        String[] d=con.parallelStream().toArray(String[]::new);

        builder.setMultiChoiceItems(d, checkedContacts, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface,int which, boolean b) {
//if a contact is saved then it will be added to currentItems lits and this list will be added into blaklist we can cancel and clear list also

                if(b)
    checkedContacts[which]=b;
else
    checkedContacts[which]=b;

    currentItem.add(colorsList.get(which));
            //  Toast.makeText(chooseContacts.this, "just contacts selected", Toast.LENGTH_SHORT).show();


            }
        });

builder.setCancelable(false);
builder.setTitle("Add contacts into blocklist");

builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
       dbhp.delete();
        for (String s: currentItem) {
            boolean b=dbhp.insertData(s);

        }
        finish();
        for (String s:currentItem) {
            Toast.makeText(chooseContacts.this, " added \t"+s, Toast.LENGTH_SHORT).show();
        }


        startActivity(new Intent(getApplicationContext(),MainActivity.class));


    }
});



builder.setNeutralButton("cancel", new DialogInterface.OnClickListener() {
    @Override
    public void onClick(DialogInterface dialogInterface, int which) {

        for (int i=0;i<checkedContacts.length;i++){
        checkedContacts[i]=false;
        currentItem.clear();
        }
        finish();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));

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
            phoneNumber=phoneNumber.replaceAll("[-+.^:, ]","");
            contacts.add(name+"\n "+phoneNumber);
        }
        phones.close();// close cursor
        return contacts;
        //display contact numbers in the list
    }
}
