package com.example.a30;






import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText etId, etName, etCourse;
    private Button btnAdd, btnUpdate, btnDelete;
    private ListView lvStudents;

    private DatabaseHelper databaseHelper;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> studentsList;
    private ArrayList<String> studentIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etId = findViewById(R.id.etId); // Student ID field
        etName = findViewById(R.id.etName);
        etCourse = findViewById(R.id.etCourse);
        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        lvStudents = findViewById(R.id.lvStudents);

        databaseHelper = new DatabaseHelper(this);

        studentsList = new ArrayList<>();
        studentIds = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, studentsList);
        lvStudents.setAdapter(adapter);

        loadStudents(); // Load students when activity starts

        // Add a new student
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = etId.getText().toString();
                String name = etName.getText().toString();
                String course = etCourse.getText().toString();

                if (!id.isEmpty() && !name.isEmpty() && !course.isEmpty()) {
                    boolean isAdded = databaseHelper.addStudent(id, name, course);
                    if (isAdded) {
                        Toast.makeText(MainActivity.this, "Student added", Toast.LENGTH_SHORT).show();
                        loadStudents();
                    } else {
                        Toast.makeText(MainActivity.this, "Error adding student", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Update student details
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = etId.getText().toString();
                String name = etName.getText().toString();
                String course = etCourse.getText().toString();

                if (!id.isEmpty() && !name.isEmpty() && !course.isEmpty()) {
                    boolean isUpdated = databaseHelper.updateStudent(id, name, course);
                    if (isUpdated) {
                        Toast.makeText(MainActivity.this, "Student updated", Toast.LENGTH_SHORT).show();
                        loadStudents();
                    } else {
                        Toast.makeText(MainActivity.this, "Error updating student", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Delete a student
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = etId.getText().toString();

                if (!id.isEmpty()) {
                    boolean isDeleted = databaseHelper.deleteStudent(id);
                    if (isDeleted) {
                        Toast.makeText(MainActivity.this, "Student deleted", Toast.LENGTH_SHORT).show();
                        loadStudents();
                    } else {
                        Toast.makeText(MainActivity.this, "Error deleting student", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Please enter student ID", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Handle list item click to prefill details for updating or deleting
        lvStudents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String studentDetails = studentsList.get(position);
                String[] details = studentDetails.split(":");
                String studentId = details[1].split("-")[0].trim();
                String studentName = details[1].split("-")[1].split("\\(")[0].trim();
                String studentCourse = details[1].split("-")[1].split("\\(")[1].replace(")", "").trim();

                // Set the EditTexts to the selected student's details
                etId.setText(studentId);
                etName.setText(studentName);
                etCourse.setText(studentCourse);
            }
        });
    }

    // Load all students from the database
    private void loadStudents() {
        Cursor cursor = databaseHelper.getAllStudents();
        studentsList.clear();

        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(0); // ID
                String name = cursor.getString(1); // Name
                String course = cursor.getString(2); // Course

                studentsList.add("ID: " + id + " - " + name + " (" + course + ")");
            } while (cursor.moveToNext());
        }

        adapter.notifyDataSetChanged();
    }
}
