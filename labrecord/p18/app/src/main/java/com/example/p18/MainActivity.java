package com.example.p18;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.ComponentName;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Button buttonbroadcast=findViewById(R.id.buttonBroadcast);
        buttonbroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create and send a custom broadcast intent
                Intent intent=new Intent();
                intent.setAction("com.example.CUSTOM_INTENT");
                // Explicitly set the component to target MyBroadcastReceiver
                intent.setComponent(new ComponentName(getPackageName(), MyBroadcastReceiver.class.getName()));

                sendBroadcast(intent);

            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}