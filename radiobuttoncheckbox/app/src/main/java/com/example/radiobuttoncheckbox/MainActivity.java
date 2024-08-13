package com.example.radiobuttoncheckbox;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    private RadioGroup radioGroup;
    private CheckBox ch1,ch2;
    private EditText edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        radioGroup=findViewById(R.id.rg);
        ch1=findViewById(R.id.checkBox3);
        ch2=findViewById(R.id.checkBox4);
        edit=findViewById(R.id.edit);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void submit(View v)
    {
        //to get the id of currently selected radiobutton
        int selectedID=radioGroup.getCheckedRadioButtonId();
        //assign it to variable selected by finding id
        RadioButton selected=findViewById(selectedID);
        //extract the text from the radio button
        String selected_text=selected.getText().toString();
        //create a string to append the text from checkbox selections using strinbuilder class
        StringBuilder ch_text=new StringBuilder();
        if(ch1.isChecked())
        {
            ch_text.append(ch1.getText()).append(" ");
        }
        if(ch2.isChecked())
        {
            ch_text.append(ch2.getText()).append(" ");
        }
        edit.setText("Selected Course:"+selected_text+"selected Category:"+ch_text);

    }

}