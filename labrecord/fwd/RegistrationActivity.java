package com.example.bloodbank;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {

    private EditText nameInput, emailInput, phoneInput, bloodGroupInput, locationInput, passwordInput;
    private Button btnRegister;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        db = new DatabaseHelper(this);

        nameInput = findViewById(R.id.name);
        emailInput = findViewById(R.id.email);
        phoneInput = findViewById(R.id.phone);
        bloodGroupInput = findViewById(R.id.blood_group);
        locationInput = findViewById(R.id.location);
        passwordInput = findViewById(R.id.password);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(v -> {
            String name = nameInput.getText().toString();
            String email = emailInput.getText().toString();
            String phone = phoneInput.getText().toString();
            String bloodGroup = bloodGroupInput.getText().toString();
            String location = locationInput.getText().toString();
            String password = passwordInput.getText().toString();

            if (db.insertDonor(name, email, phone, bloodGroup, location, password)) {
                Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Registration Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
