package com.example.playback;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

//import
import  android.view.View;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

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
    }

    public void startMedia(View v){
        Intent i = new Intent(MainActivity.this, MyService.class);
        i.setAction("START");
        startService(i);
    }
    public void stopMedia(View v){
        Intent i = new Intent(MainActivity.this, MyService.class);
        stopService(i);//calling onDestroy() method

    }
    public void pauseMedia(View v){
        Intent i = new Intent(MainActivity.this, MyService.class);
        i.setAction("PAUSE");
        startService(i);
    }
    public void resumeMedia(View v){
        Intent i = new Intent(MainActivity.this, MyService.class);
        i.setAction("RESUME");
        startService(i);
    }


}