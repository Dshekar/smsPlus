package com.example.rajashekarreddy.sample1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Rajashekar Reddy on 23-Feb-18.
 */

public class MyAlarm extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

String s=intent.getAction();
        Toast.makeText(context, "  "+intent.getExtras().getString("name"), Toast.LENGTH_SHORT).show();
    }
}
