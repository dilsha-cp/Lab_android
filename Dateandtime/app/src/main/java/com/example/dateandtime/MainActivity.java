package com.example.dateandtime;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        TextView t1=(TextView) findViewById(R.id.t1);
        TextView t2=(TextView) findViewById(R.id.t2);
        TextView t3=(TextView) findViewById(R.id.t3);
        TextView t4=(TextView) findViewById(R.id.t4);
        TextView t5=(TextView) findViewById(R.id.t5);
        TextView t6=(TextView) findViewById(R.id.t6);
        TextView t7=(TextView) findViewById(R.id.t7);
        TextView t8=(TextView) findViewById(R.id.t8);
        TextView t9=(TextView) findViewById(R.id.t9);
        TextView t10=(TextView) findViewById(R.id.t10);
        Long dtlong=System.currentTimeMillis();
        t2.setText(dtlong.toString());
        t1.setText("Time and Date");
        t3.setText("Different format of Time And Date");
        String dateTime;
        Calendar calendar;
        SimpleDateFormat simpleDateFormat;
        calendar=Calendar.getInstance();
        simpleDateFormat=new SimpleDateFormat("dd.MM.yyyy HH:mm:ss aaa z");
        dateTime=simpleDateFormat.format(calendar.getTime()).toString();
        t4.setText(dateTime);
        calendar=Calendar.getInstance();
        simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss aaa z");
        dateTime=simpleDateFormat.format(calendar.getTime()).toString();
        t5.setText(dateTime);
        calendar=Calendar.getInstance();
        simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss aaa z");
        dateTime=simpleDateFormat.format(calendar.getTime()).toString();
        t6.setText(dateTime);
        calendar=Calendar.getInstance();
        simpleDateFormat=new SimpleDateFormat("dd.LLLL.yyyy HH:mm:ss aaa z");
        dateTime=simpleDateFormat.format(calendar.getTime()).toString();
        t7.setText(dateTime);
        calendar=Calendar.getInstance();
        simpleDateFormat=new SimpleDateFormat("dd.LLLL.yyyy HH:mm:ss aaa z");
        dateTime=simpleDateFormat.format(calendar.getTime()).toString();
        t8.setText(dateTime);
        calendar=Calendar.getInstance();
        simpleDateFormat=new SimpleDateFormat("E.LLLL.yyyy HH:mm:ss aaa z");
        dateTime=simpleDateFormat.format(calendar.getTime()).toString();
        t9.setText(dateTime);
        calendar=Calendar.getInstance();
        simpleDateFormat=new SimpleDateFormat("EEEE.LLLL.yyyy KK:mm:ss aaa z");
        dateTime=simpleDateFormat.format(calendar.getTime()).toString();
        t10.setText(dateTime);






    }
}