package com.example.rajashekarreddy.sample1;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Displaymsgs extends AppCompatActivity {

private List<Product> mProductList;
private ListView lvProduct;
private ProductListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displaymsgs);
        setTitle(getIntent().getStringExtra("name"));
        Toast.makeText(this, getIntent().getStringExtra("name"), Toast.LENGTH_SHORT).show();
    }

}

