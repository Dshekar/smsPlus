package com.example.rajashekarreddy.sample1;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.telephony.SmsManager;
import android.widget.Toast;

/**
 * Created by Rajashekar Reddy on 23-Feb-18.
 */

public class MyAlarm extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {


        Toast.makeText(context, "  "+intent.getExtras().getString("name"), Toast.LENGTH_SHORT).show();
        MediaPlayer mediaPlayer=MediaPlayer.create(context, Settings.System.DEFAULT_RINGTONE_URI);
        mediaPlayer.start();


        /*
        Intent i =new Intent();
        i.setClassName("com.example.rajashekarreddy.sample1","com.example.rajashekarreddy.sample1.ComposeSmsActivity");
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.putExtra("name",intent.getExtras().getString("name").toString());
        i.putExtra("message",intent.getExtras().getString("message").toString());
        context.startActivity(i);*/

        sendSMS(intent.getStringExtra("name"),intent.getStringExtra("message"),context);

    }


    private void sendSMS(String phoneNumber, String message,Context context)
    {
        String SENT = "SMS_SENT";
        String DELIVERED = "SMS_DELIVERED";

        PendingIntent sentPI = PendingIntent.getBroadcast(context, 0,
                new Intent(SENT), 0);

        PendingIntent deliveredPI = PendingIntent.getBroadcast(context, 0,
                new Intent(DELIVERED), 0);


        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, sentPI, deliveredPI);
    }
}
