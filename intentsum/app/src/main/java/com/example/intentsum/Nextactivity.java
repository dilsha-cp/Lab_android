package com.example.intentsum;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.widget.TextView;

public class Nextactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_nextactivity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Bundle e=getIntent().getExtras();
        if(e!=null){
            TextView tv=findViewById(R.id.t5);
            String msg=e.getString("message");
            int num1=e.getInt("a");
            int num2=e.getInt("b");
            int s=num1+num2;
            tv.setText(msg+s);
        }
    }
    public void gotohome(View v)
    {
        Intent i=new Intent(Nextactivity.this,MainActivity.class);
        startActivity(i);
    }
}