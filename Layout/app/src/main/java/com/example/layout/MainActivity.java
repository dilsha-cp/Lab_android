package com.example.layout;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.activity.OnBackPressedCallback;

public class MainActivity extends AppCompatActivity {
    private int currentLayoutId = R.layout.activity_main; // Track the current layout
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
        // Add an OnBackPressedCallback for handling back presses
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (currentLayoutId != R.layout.activity_main) {
                    setContentView(R.layout.activity_main);
                    currentLayoutId = R.layout.activity_main;
                } else {
                    finish(); // Close the app
                }
            }
        });
        findViewById(R.id.l1).setOnClickListener(v -> {
            setContentView(R.layout.linearlayouteg);
            currentLayoutId = R.layout.linearlayouteg;
        });

        findViewById(R.id.l2).setOnClickListener(v -> {
            setContentView(R.layout.relativelayout);
            currentLayoutId = R.layout.relativelayout;
        });

        findViewById(R.id.l3).setOnClickListener(v -> {
            setContentView(R.layout.framelayout);
            currentLayoutId = R.layout.framelayout;
        });

        findViewById(R.id.l4).setOnClickListener(v -> {
            setContentView(R.layout.tablelayout);
            currentLayoutId = R.layout.tablelayout;
        });

    }
}