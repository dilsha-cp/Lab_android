package com.example.menu;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu1, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==R.id.item1)
            Toast.makeText(MainActivity.this,"Create a new project",Toast.LENGTH_LONG).show();
        if(item.getItemId()==R.id.item2)
            Toast.makeText(MainActivity.this,"Open a project",Toast.LENGTH_LONG).show();
        if(item.getItemId()==R.id.item3)
            Toast.makeText(MainActivity.this,"Group item1",Toast.LENGTH_LONG).show();
        if(item.getItemId()==R.id.item4)
            Toast.makeText(MainActivity.this,"Group item2",Toast.LENGTH_LONG).show();
        if(item.getItemId()==R.id.item5)
            Toast.makeText(MainActivity.this,"sub menu item1",Toast.LENGTH_LONG).show();
        if(item.getItemId()==R.id.item6)
            Toast.makeText(MainActivity.this,"sub menu item2",Toast.LENGTH_LONG).show();
        return super.onOptionsItemSelected(item);
    }

}