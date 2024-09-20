package com.example.p24;

import android.content.ClipData;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;

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
        //find the gridview by it's id
        GridView gridView=findViewById(R.id.gv);
        //create a list of item to display in the rid view
        String[] items={
                "Item 1","Item 2","Item 3","Item 4"
                ,"Item 5","Item 6",
                "Item 7","Item 8","Item 9",

        };
        //create an arrayadapter
        ArrayAdapter<String> adapter=new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                items

        );
        //set the adapter to the gridview
        gridView.setAdapter(adapter);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}