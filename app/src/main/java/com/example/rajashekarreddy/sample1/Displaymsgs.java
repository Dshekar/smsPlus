package com.example.rajashekarreddy.sample1;


import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Displaymsgs extends AppCompatActivity {

    private List<Message> mProductList;
    private ListView lvProduct;
    private MessageListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displaymsgs);
        setTitle(getIntent().getStringExtra("name"));
        mProductList = new ArrayList<>();
        lvProduct = (ListView) findViewById(R.id.listview_product1);
       // Toast.makeText(this, getIntent().getStringExtra("name"), Toast.LENGTH_SHORT).show();


        String[] res = {"body" , "date" , "type"};
        String cond="address='"+ getIntent().getStringExtra("name")+"'";
        Cursor c = getContentResolver().query(Uri.parse("content://sms/"), res ,  cond , null, "date");
int t=1;

        if (c.moveToFirst()) { // must check the result to prevent exception
            do {
                mProductList.add(new Message(t++,c.getString(0),new SimpleDateFormat(" hh:mm  dd/MM/yy ").format(new Date(Long.parseLong(c.getString(1)))),c.getString(2)));

            } while (c.moveToNext());

        }




       // mProductList.add(new Message(t++,c.getString(0),new Date(Long.parseLong(c.getString(1))).toString(),c.getString(2)));

        //we have to populate the list

        adapter = new MessageListAdapter(getApplicationContext(), mProductList);
        lvProduct.setAdapter(adapter);

    }

}

