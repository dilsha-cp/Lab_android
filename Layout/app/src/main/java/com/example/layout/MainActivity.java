package com.example.layout;

import android.os.Bundle;

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

        setMainView();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void setMainView() {
        setContentView(R.layout.activity_main);

        findViewById(R.id.l1).setOnClickListener(v -> {
            setContentView(R.layout.linearlayouteg);
            findViewById(R.id.back_button).setOnClickListener(view -> setMainView());
        });

        findViewById(R.id.l2).setOnClickListener(v -> {
            setContentView(R.layout.relativelayout);
            findViewById(R.id.back_button).setOnClickListener(view -> setMainView());
        });

        findViewById(R.id.l3).setOnClickListener(v -> {
            setContentView(R.layout.framelayout);
            findViewById(R.id.back_button).setOnClickListener(view -> setMainView());
        });

        findViewById(R.id.l4).setOnClickListener(v -> {
            setContentView(R.layout.tablelayout);
            findViewById(R.id.back_button).setOnClickListener(view -> setMainView());
        });
}
}