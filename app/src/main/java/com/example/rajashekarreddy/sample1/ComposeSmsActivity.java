package com.example.rajashekarreddy.sample1;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URLDecoder;

public class ComposeSmsActivity extends AppCompatActivity {
EditText l;
ImageView g;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose_sms);
        Intent intent = getIntent();
l=(EditText)findViewById(R.id.editText);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Drawable d=getDrawable(R.drawable.addbutton);
        }
        if (Intent.ACTION_SENDTO.equals(intent.getAction())) {
            //in the data i'll find the number of the destination
            String destionationNumber = intent.getDataString();
            destionationNumber = URLDecoder.decode(destionationNumber);
            //clear the string
            destionationNumber = destionationNumber.replace("-", "")
                    .replace("smsto:", "")
                    .replace("sms:", "");
            //set the number when message icon in contacts is clicked

            l.setText(destionationNumber);

        }

    }

    @Override
    protected void onResume() {
        super.onResume();


    }
}
