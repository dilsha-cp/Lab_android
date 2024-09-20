package com.example.p2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText marks1,marks2,marks3;
    Button calculateButton;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        //intializing ui components
        marks1=findViewById(R.id.marks1);
        marks2=findViewById(R.id.marks2);
        marks3=findViewById(R.id.marks3);
        calculateButton=findViewById(R.id.calculateButton);
        result=findViewById(R.id.result);
        //set button onclicklistener
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculatesumaverage();
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void calculatesumaverage(){
        //to get value from the feild
        String marks1string=marks1.getText().toString();
        String marks2string=marks2.getText().toString();
        String marks3string=marks3.getText().toString();
        if (marks1string.isEmpty() || marks2string.isEmpty() || marks3string.isEmpty()) {
            // Show a toast message if any field is empty
            Toast.makeText(MainActivity.this, "Please enter all the marks", Toast.LENGTH_LONG).show();
            return;
        }
        int mark1=Integer.parseInt(marks1string);
        int mark2=Integer.parseInt(marks2string);
        int mark3=Integer.parseInt(marks3string);
        //calculate sum and average
        int sum=mark1+mark2+mark3;
        float avg=sum/3f;
        //display
        result.setText("sum: " +sum+ "\nAverage: " +avg);

    }
}