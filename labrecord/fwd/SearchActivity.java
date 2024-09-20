package com.example.bloodbank;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class SearchActivity extends AppCompatActivity {

    private EditText bloodGroupInput, locationInput;
    private Button btnSearch;
    private ListView listView;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        db = new DatabaseHelper(SearchActivity.this);

        bloodGroupInput = findViewById(R.id.blood_group);
        locationInput = findViewById(R.id.location);
        btnSearch = findViewById(R.id.btnSearch);
        listView = findViewById(R.id.donorList);

        btnSearch.setOnClickListener(v -> {
            String bloodGroup = bloodGroupInput.getText().toString();
            String location = locationInput.getText().toString();
            Cursor cursor = db.searchDonors(bloodGroup, location);

            if (cursor != null && cursor.getCount() > 0) {
                String[] from = {"name", "phone"};
                int[] to = {R.id.donor_name, R.id.donor_phone};
                SimpleCursorAdapter adapter = new SimpleCursorAdapter(SearchActivity.this, R.layout.donor_list, cursor, from, to, 0);
                listView.setAdapter(adapter);
            } else {
                Toast.makeText(SearchActivity.this, "No donors found", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
