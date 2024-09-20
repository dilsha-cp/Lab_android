package com.example.p7;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.EdgeToEdge;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Display a toast when the activity starts
        Toast.makeText(MainActivity.this, "Activity Launched successfully", Toast.LENGTH_SHORT).show();

    }
}