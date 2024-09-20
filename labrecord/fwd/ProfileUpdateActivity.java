package com.example.bloodbank;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ProfileUpdateActivity extends AppCompatActivity {

    private EditText nameInput, emailInput, phoneInput, bloodGroupInput, locationInput, passwordInput;
    private Button btnUpdate;
    DatabaseHelper db;
    int donorId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_update);

        // Initialize DatabaseHelper
        db = new DatabaseHelper(this);

        // Get donor id from intent (assuming donor id is passed from previous activity)
        donorId = getIntent().getIntExtra("DONOR_ID", -1);
        if (donorId == -1) {
            Toast.makeText(this, "Invalid donor", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Initialize UI elements
        nameInput = findViewById(R.id.name);
        emailInput = findViewById(R.id.email);
        phoneInput = findViewById(R.id.phone);
        bloodGroupInput = findViewById(R.id.blood_group);
        locationInput = findViewById(R.id.location);
        passwordInput = findViewById(R.id.password);
        btnUpdate = findViewById(R.id.btnUpdate);

        // Load donor data
        loadDonorData();

        // Handle Update button click
        btnUpdate.setOnClickListener(v -> {
            String name = nameInput.getText().toString();
            String email = emailInput.getText().toString();
            String phone = phoneInput.getText().toString();
            String bloodGroup = bloodGroupInput.getText().toString();
            String location = locationInput.getText().toString();
            String password = passwordInput.getText().toString();

            // Update donor profile in the database
            if (db.updateDonor(donorId, name, email, phone, bloodGroup, location, password)) {
                Toast.makeText(ProfileUpdateActivity.this, "Profile updated successfully", Toast.LENGTH_SHORT).show();
                finish(); // Close the activity after successful update
            } else {
                Toast.makeText(ProfileUpdateActivity.this, "Profile update failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Load donor data from the database and set it to the EditText fields
    private void loadDonorData() {
        Donor donor = db.getDonorById(donorId);
        if (donor != null) {
            nameInput.setText(donor.getName());
            emailInput.setText(donor.getEmail());
            phoneInput.setText(donor.getPhone());
            bloodGroupInput.setText(donor.getBloodGroup());
            locationInput.setText(donor.getLocation());
            passwordInput.setText(donor.getPassword());
        } else {
            Toast.makeText(this, "Unable to load donor data", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
