package com.example.rajashekarreddy.sample1;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class scheduleSMS extends AppCompatActivity implements DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener {
int day,year,month,hour,minute;
int dayFinal,monthFinal,yearFinal,hourFinal,minuteFinal;

    Calendar c;
EditText num;
EditText msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_sms);
        num=(EditText)findViewById(R.id.phnnumber);
        msg=(EditText)findViewById(R.id.msgTxt);

    }
    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public void timer(View view){
     c=Calendar.getInstance();
        year=c.get(Calendar.YEAR);
        month=c.get(Calendar.MONTH);
        day=c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog=new DatePickerDialog(scheduleSMS.this,scheduleSMS.this,year,month,day);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();

    }
    public void msgSET(View view){
        AlarmManager alarmManager=(AlarmManager) getSystemService(getApplicationContext().ALARM_SERVICE);
        Intent intent=new Intent(this,MyAlarm.class);
        intent.putExtra("name",num.getText().toString());
        PendingIntent pendingIntent=PendingIntent.getBroadcast(this,0,intent,0);

        //      alarm code for sending message   dayFinal,monthFinal,yearFinal,hourFinal,minuteFinal;
        Calendar c=Calendar.getInstance();
        c.set(yearFinal,monthFinal,dayFinal,hourFinal,minuteFinal);

        Toast.makeText(this, ""+c.getTime(), Toast.LENGTH_SHORT).show();
        alarmManager.set(AlarmManager.RTC,c.getTimeInMillis(),pendingIntent);

    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

        yearFinal=i;
        monthFinal=i+1;
        dayFinal=i2;

         c=Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute=c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(scheduleSMS.this,scheduleSMS.this,hour,minute, DateFormat.is24HourFormat(this));
        timePickerDialog.show();
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {

        hourFinal=i;
        minuteFinal=i1;

    }
}
