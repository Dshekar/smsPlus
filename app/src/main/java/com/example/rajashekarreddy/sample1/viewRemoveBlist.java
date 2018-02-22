package com.example.rajashekarreddy.sample1;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class viewRemoveBlist extends Activity {
    List<String> con=new ArrayList<>();
    DatabaseHelper dbhp;
    List<String> bHolder=new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_remove_blist);
        dbhp=new DatabaseHelper(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(viewRemoveBlist.this);
        con = Blist();
        final boolean[] checkedContacts = new boolean[con.size()];
        String[] k=con.parallelStream().toArray(String[]::new);
        dbhp=new DatabaseHelper(this);
        final List<String> colorsList = con;

        builder.setMultiChoiceItems(k, checkedContacts, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface,int which, boolean b) {

                //if a contact is saved then it will be added to currentItems lits and this list will be added into blaklist we can cancel and clear list also

                if(b)
                    checkedContacts[which]=b;
                else
                    checkedContacts[which]=b;
 //  Toast.makeText(chooseContacts.this, "just contacts selected", Toast.LENGTH_SHORT).show();
            bHolder.add(colorsList.get(which));
            }
        });

        builder.setCancelable(false);
        builder.setTitle("view or remove blocked contacts");


        builder.setPositiveButton("Remove", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
             Integer k=  dbhp.removeItems(bHolder);
            finish();
                Toast.makeText(viewRemoveBlist.this, k+" contacts removed from Blocklist", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });


//set cancel button
        builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                finish();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));

            }
        });

//here we delete the database...
        builder.setNegativeButton("Remove All", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
dbhp.delete();
                finish();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));

            }
        });


        AlertDialog dialog = builder.create();
        dialog.show();




    }
    public List<String> Blist(){
        List<String> l=new ArrayList<>();
        Cursor res=dbhp.getAllData();
            while(res.moveToNext()){
                l.add(res.getString(1));
            }
            return l;
    }
}
