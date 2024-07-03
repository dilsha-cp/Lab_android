package com.example.contextmenu;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.graphics.Color;
import androidx.constraintlayout.widget.ConstraintLayout;


public class MainActivity extends AppCompatActivity {
    TextView textView;
    ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        textView=(TextView) findViewById(R.id.textView);
        layout=(ConstraintLayout) findViewById(R.id.main);
        registerForContextMenu(textView);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void onCreateContextMenu(ContextMenu menu,View v,ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu,v,menuInfo);
        menu.setHeaderTitle("Choose a color");
        menu.add(0,v.getId(),0,"Yellow");
        menu.add(0,v.getId(),0,"Gray");
        menu.add(0,v.getId(),0,"Cyan");

    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getTitle().equals("Yellow")) {
            layout.setBackgroundColor(Color.YELLOW);
        } else if (item.getTitle().equals("Gray")) {
            layout.setBackgroundColor(Color.GRAY);
        } else if (item.getTitle().equals("Cyan")) {
            layout.setBackgroundColor(Color.CYAN);
        }
        return true;
    }
}