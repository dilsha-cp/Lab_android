package com.example.airplane;



import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

//import
import android.view.View;
import android.content.Intent;
import android.content.IntentFilter;

public class MainActivity extends AppCompatActivity {

    MyReceiver receiver; //declaring Broadcast receiver
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //call another function for receiving broadcast:
        configureReceiver();
    }

    //Function for receiver
    public void configureReceiver(){
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        receiver = new MyReceiver();
        registerReceiver(receiver,filter);
    }

    //[Optional]-To destroy receiver after use
    protected void onDestroy(){
        super.onDestroy();
        unregisterReceiver(receiver);
    }

}