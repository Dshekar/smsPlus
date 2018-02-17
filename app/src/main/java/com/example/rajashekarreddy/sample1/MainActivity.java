package com.example.rajashekarreddy.sample1;


import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.Telephony;
import android.os.Bundle;
import android.support.design.internal.NavigationMenu;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.github.yavski.fabspeeddial.FabSpeedDial;


public class MainActivity extends AppCompatActivity {

    private List<Product> mProductList;
    private ListView lvProduct;
    private ProductListAdapter adapter;
    //  private Log mLogger;
    AlertDialog.Builder alertdialogbuilder;


    //        app:miniFabDrawableTint="?attr/colorPrimaryDesk"
    //app:miniFabTitleTextColor="?attr/colorPrimaryDesk"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (!(ContextCompat.checkSelfPermission(getBaseContext(), "android.permission.READ_SMS") == PackageManager.PERMISSION_GRANTED)) {
            final int REQUEST_CODE_ASK_PERMISSIONS = 123;
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.READ_SMS"}, REQUEST_CODE_ASK_PERMISSIONS);
        }

        if ((!Telephony.Sms.getDefaultSmsPackage(this).equals(getPackageName()))) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("This app is not set as your default messaging app. Do you want to set it as default?")
                    .setCancelable(false)
                    .setTitle("Alert!")
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @TargetApi(19)
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(Telephony.Sms.Intents.ACTION_CHANGE_DEFAULT);
                            intent.putExtra(Telephony.Sms.Intents.EXTRA_PACKAGE_NAME, getPackageName());
                            startActivity(intent);
                        }
                    });
            builder.show();
        }

        FabSpeedDial fabSpeedDial = (FabSpeedDial)findViewById(R.id.fabSpeedDial);
        fabSpeedDial.setMenuListener(new FabSpeedDial.MenuListener() {
            @Override
            public boolean onPrepareMenu(NavigationMenu navigationMenu) {
                return true;//false :dont show menu
            }

            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
                Toast.makeText(MainActivity.this, " "+menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public void onMenuClosed() {

            }
        });



    //the end of onCreate......
    }

        public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
           if (item.getItemId()==R.id.opt1) {
        // android dialog box code starts here************

    startActivity(new Intent(getApplicationContext(),chooseContacts.class));


        }

        if (item.getItemId()==R.id.opt2){
            Toast.makeText(this, "clicked on opt2", Toast.LENGTH_SHORT).show();
        }
        if (item.getItemId()==R.id.opt3){
            Toast.makeText(this, "clicked on opt3", Toast.LENGTH_SHORT).show();
        }
        if (item.getItemId()==R.id.opt4){
            Toast.makeText(this, "clicked on opt4", Toast.LENGTH_SHORT).show();
        }
        if (item.getItemId()==R.id.opt5){
            Toast.makeText(this, "clicked on opt5", Toast.LENGTH_SHORT).show();
        }
            return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();


        lvProduct = (ListView) findViewById(R.id.listview_product);
        mProductList = new ArrayList<>();
//        mProductList.add(new Product(1,"shekar reddy","hi shekar just resumed ",new SimpleDateFormat("dd/MM/yy hh:mm").format(new Date())));
// max code
        if (!(ContextCompat.checkSelfPermission(getBaseContext(), "android.permission.READ_SMS") == PackageManager.PERMISSION_GRANTED)) {
            final int REQUEST_CODE_ASK_PERMISSIONS = 123;
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.READ_SMS"}, REQUEST_CODE_ASK_PERMISSIONS);
        }
        else {
            List<sMs> nlist = new ArrayList<sMs>();
            String[] res = {"address", "body", "date"};
            Cursor c = getContentResolver().query(Uri.parse("content://sms/"), res, null, null, null);

            if (c.moveToFirst()) { // must check the result to prevent exception
                do {
                    nlist.add(new sMs(c.getString(0), c.getString(1), new Date(Long.parseLong(c.getString(2)))));
                } while (c.moveToNext());

            }
            else {
                // empty box, no SMS
                Toast.makeText(this, "there are no sms in inbox", Toast.LENGTH_SHORT).show();
            }
            Collections.sort(nlist);
            HashMap<String, sMs> hm = new HashMap<String, sMs>();
            int i = 0;
            while (i < nlist.size()) {
                hm.put(nlist.get(i).getContact(), nlist.get(i));
                i++;
            }


            List<sMs> k = new ArrayList<>();


            for (Map.Entry m : hm.entrySet()) {
                System.out.println(m.getKey() + " \t" + m.getValue());
                k.add((sMs) m.getValue());
            }
/*
       int j = 0;
            for (sMs f : k) {
                // Toast.makeText(this, f.getContact()+"  "+f.getMsg()+"\n", Toast.LENGTH_SHORT).show();
                        mProductList.add(new Product(++j, f.getContact(), f.getMsg(), new SimpleDateFormat("dd/MM/yy hh:mm").format(f.getDate())));
                j++;
            }*/
            List<finsms>  fin=new ArrayList<>();
            int y=0;
            while(y<k.size()){
                fin.add(new finsms(k.get(y).getContact(),k.get(y).getMsg(),k.get(y).getDate()));
                y++;
            }
            Collections.sort(fin,new finsms());
            Collections.reverse(fin);
            final List<String> io=new ArrayList<String>();
            int jj = 0;
            for (finsms f : fin) {
                // Toast.makeText(this, f.getContact()+"  "+f.getMsg()+"\n", Toast.LENGTH_SHORT).show();
                mProductList.add(new Product(++jj, f.getContact(), f.getMsg(), new SimpleDateFormat("dd/MM/yy   hh:mm").format(f.getDate())));
                io.add(f.getContact());
                jj++;
            }

            final Intent ii=new Intent(this,Displaymsgs.class);
            //max code
            adapter = new ProductListAdapter(getApplicationContext(), mProductList);
            lvProduct.setAdapter(adapter);
    lvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener(){
        public void onItemClick(AdapterView<?> arg,View v,int position,long arg1){
            Product p=(Product) arg.getItemAtPosition(position);
            //Toast.makeText(MainActivity.this, p.getName(), Toast.LENGTH_SHORT).show();
            ii.putExtra("name",p.getName());
            startActivity(ii);
        }
    });

        }
    }
//just gets numbers from contacts....
    public  List<String> getNumber(ContentResolver cr)
    {
List<String> contacts=null;
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
