package com.example.rajashekarreddy.sample1;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;


public class SmsReceiver extends BroadcastReceiver {

    DatabaseHelper dbhp;
    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    private static final String TAG = "SMSBroadcastReceiver";
    public static Context contextOfApplication;
    List<String> jk=new ArrayList<>();

    @Override
    public void onReceive(Context context, Intent intent) {
       // Toast.makeText(context, "just received an sms", Toast.LENGTH_SHORT).show();

        dbhp=new DatabaseHelper(context);

        Cursor res=dbhp.getAllData();
        while(res.moveToNext()){
            jk.add(res.getString(1).replace("\n",""));
        }
    int Flag=0;

        if (intent.getAction().equals(SMS_RECEIVED)) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                Object[] pdus = (Object[])bundle.get("pdus");
                final SmsMessage[] messages = new SmsMessage[pdus.length];
                for (int i = 0; i < pdus.length; i++) {
                    messages[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
                }
String numbr=messages[0].getOriginatingAddress();
                for (String s:jk){
                    if (s.matches("(.*)"+numbr+"(.*)")){
                        Flag=1;
                        abortBroadcast();
                        Toast.makeText(context, "blocked ", Toast.LENGTH_SHORT).show();
                    }
                }
                if (Flag==0){
                if (messages.length > -1) {
                    Log.i(TAG, "Message recieved: " + messages[0].getMessageBody());
                    ContentValues values = new ContentValues();
                    values.put("address", messages[0].getOriginatingAddress());
                    values.put("body", messages[0].getMessageBody());
                    Toast.makeText(context, "Message recieved: " + messages[0].getMessageBody(), Toast.LENGTH_SHORT).show();
                    context.getContentResolver().insert(Uri.parse("content://sms/inbox"), values);
                    // Toast.makeText(context,  "" + messages[0].getOriginatingAddress(), Toast.LENGTH_SHORT).show();
                }
                }

            }

        }
        

    }

    private List<String> Blist1(Context context) {
        List<String> l=new ArrayList<>();

        Cursor res=dbhp.getAllData();
        while(res.moveToNext()){
            l.add(res.getString(1));
        }
        return l;
    }


    private Context getApplicationContext() {
        return contextOfApplication;
    }



}

