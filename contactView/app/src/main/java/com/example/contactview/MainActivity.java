package com.example.contactview;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private ListView contactListView;
    private ContactAdapter contactAdapter;
    private List<Contact> contactList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        contactListView = findViewById(R.id.contact_list_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        contactList = new ArrayList<>();
        contactList.add(new Contact("Dilsha", "123-456-7890"));
        contactList.add(new Contact("Basheer", "098-765-4321"));
        contactList.add(new Contact("irshad", "555-123-4567"));
        contactList.add(new Contact("Bob Johnson", "777-888-9999"));

        contactAdapter = new ContactAdapter(this, contactList);
        contactListView.setAdapter(contactAdapter);
    }
}