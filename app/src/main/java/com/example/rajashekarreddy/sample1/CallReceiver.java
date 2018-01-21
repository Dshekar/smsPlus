package com.example.rajashekarreddy.sample1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;


public class CallReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
                    Toast.makeText(context, "broadcast received", Toast.LENGTH_SHORT).show();
    }
}
