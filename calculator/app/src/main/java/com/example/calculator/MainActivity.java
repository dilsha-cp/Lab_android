package com.example.calculator;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView display;
    private StringBuilder currentInput = new StringBuilder();
    private double firstNumber = 0;
    private String operator = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        display = findViewById(R.id.display);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                String buttonText = button.getText().toString();
                handleInput(buttonText);
            }
        };

        int[] buttonIds = {
                R.id.button, R.id.button2, R.id.button3, R.id.button4,
                R.id.button5, R.id.button6, R.id.button7, R.id.button8,
                R.id.button9, R.id.button10, R.id.button11, R.id.button12,
                R.id.button13, R.id.button14, R.id.button15, R.id.button16, R.id.buttonC, R.id.buttonBackspace
        };

        for (int id : buttonIds) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    private void handleInput(String input) {
        if ("0123456789.".contains(input)) {
            currentInput.append(input);
            display.setText(currentInput.toString());
        } else if ("+-*/".contains(input)) {
            if (currentInput.length() > 0) {
                firstNumber = Double.parseDouble(currentInput.toString());
                operator = input;
                currentInput.setLength(0);
            }
        } else if ("=".equals(input)) {
            if (currentInput.length() > 0 && !operator.isEmpty()) {
                double secondNumber = Double.parseDouble(currentInput.toString());
                double result = 0;
                switch (operator) {
                    case "+":
                        result = firstNumber + secondNumber;
                        break;
                    case "-":
                        result = firstNumber - secondNumber;
                        break;
                    case "*":
                        result = firstNumber * secondNumber;
                        break;
                    case "/":
                        if (secondNumber != 0) {
                            result = firstNumber / secondNumber;
                        } else {
                            display.setText("Error");
                            return;
                        }
                        break;
                }
                display.setText(String.valueOf(result));
                currentInput.setLength(0);
                currentInput.append(result);
                operator = "";
            }
        } else if ("C".equals(input)) {
            currentInput.setLength(0);
            display.setText("0");
            operator = "";
            firstNumber = 0;
        } else if ("⌫".equals(input)) {
            if (currentInput.length() > 0) {
                currentInput.deleteCharAt(currentInput.length() - 1);
                display.setText(currentInput.toString());
            }
            if (currentInput.length() == 0) {
                display.setText("0");
            }
        }
    }
}