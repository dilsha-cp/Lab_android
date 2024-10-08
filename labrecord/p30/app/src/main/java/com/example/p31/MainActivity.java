package com.example.p31;



import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

//import the following
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private EditText stud_name,stud_course,stud_semester;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //initialize
        stud_name=findViewById(R.id.text_name);
        stud_course=findViewById(R.id.text_course);
        stud_semester=findViewById(R.id.text_sem);

        //create a new DBHandler object and pass context to it
        dbHandler=new DBHandler(MainActivity.this);

    }
    public void addStudent(View v)
    {

        String studentName=stud_name.getText().toString();
        String studentCourse=stud_course.getText().toString();
        int studentSemester=Integer.parseInt(stud_semester.getText().toString());

        // validating if the text fields are empty or not.
        if (studentName.equals("") || studentCourse.equals("") || stud_semester.equals("")) {
            Toast.makeText(MainActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
            return;
        }

        dbHandler.addNewStudent(studentName,studentCourse,studentSemester);

        Toast.makeText(MainActivity.this, "Student is added",Toast.LENGTH_LONG).show();

        stud_name.setText("");
        stud_course.setText("");
        stud_semester.setText("");
    }

    public void lookupStudent(View v)
    {
        String studentName=stud_name.getText().toString();
        Student student=dbHandler.findStudent(studentName);

        if(student!=null)
        {
            stud_course.setText(String.valueOf(student.getCourse()));
            stud_semester.setText(String.valueOf(student.getSemester()));
        }
        else
        {
            Toast.makeText(MainActivity.this,"No Match Found",Toast.LENGTH_SHORT).show();
        }
    }
    public void removeStudent(View v)
    {
        String studentName=stud_name.getText().toString();
        boolean result=dbHandler.deleteStudent(studentName);
        if (result)
        {
            Toast.makeText(MainActivity.this,"Record Deleted",Toast.LENGTH_SHORT).show();
            stud_name.setText("");
            stud_course.setText("");
            stud_semester.setText("");
        }
        else
            Toast.makeText(MainActivity.this,"No Match Found",Toast.LENGTH_SHORT).show();
    }
    public void modifyStudentDetails(View v)
    {
        String studentName=stud_name.getText().toString();
        String studentCourse=stud_course.getText().toString();
        int studentSemester=Integer.parseInt(stud_semester.getText().toString());
        dbHandler.updateStudent(studentName,studentCourse,studentSemester);
        Toast.makeText(MainActivity.this,"Record Updated",Toast.LENGTH_SHORT).show();
        stud_name.setText("");
        stud_course.setText("");
        stud_semester.setText("");
    }
}