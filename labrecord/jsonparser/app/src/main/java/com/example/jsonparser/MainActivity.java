package com.example.jsonparser;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    TextView tv1, tv2;
    public static final String JSON_STRING = "{\"employee\":{\"name\":\"dilsha\",\"salary\":5600}}";
    public static final String JSON_ARRAY = "{ \"Employee\": [ {\"id\":\"101\",\"name\":\"Sonoo Jaiswal\", \"salary\":\"50000\"}, {\"id\":\"102\",\"name\":\"Vimal Jaiswal\", \"salary\":\"60000\"} ] }";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        tv1 = findViewById(R.id.textView);
        tv2 = findViewById(R.id.textView2);
        try {
            // JSON Object parsing
            JSONObject emp = (new JSONObject(JSON_STRING)).getJSONObject("employee");
            String emp_name = emp.getString("name");
            int emp_salary = emp.getInt("salary");

            String str = "Employee Name: " + emp_name + "\n" + "Employee Salary: " + emp_salary;
            tv1.setText(str);

            // JSON Array parsing
            StringBuilder data = new StringBuilder();
            JSONObject array_obj = new JSONObject(JSON_ARRAY);
            JSONArray jsonArray = array_obj.optJSONArray("Employee");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int id = jsonObject.getInt("id");
                String name = jsonObject.getString("name");
                int salary = jsonObject.getInt("salary");

                // Append each object's data to the display data
                data.append("Object: ").append(i).append(" ID: ").append(id)
                        .append(" Name: ").append(name)
                        .append(" Salary: ").append(salary).append("\n");
            }

            tv2.setText(data.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
