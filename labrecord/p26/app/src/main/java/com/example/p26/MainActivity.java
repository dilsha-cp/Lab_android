package com.example.p26;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private TextView textViewProgress;
    private Button buttonstart;
    private int ProgressStatus=0;
    private Handler handler=new Handler(Looper.getMainLooper());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        //intialis view
        progressBar=findViewById(R.id.progressBar);
        textViewProgress=findViewById(R.id.textv);
        buttonstart=findViewById(R.id.button);
        //set onclicklistener
        buttonstart.setOnClickListener(view -> {
                    //start progress in a new thread
                    new Thread(() -> {
                        //simulate some work with a loop
                        while (ProgressStatus < 100) {
                            ProgressStatus += 1;
                            //sleep for 100 milli second

                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            //update the progress bar and text view in ui thread
                            handler.post(() -> {
                                progressBar.setProgress(ProgressStatus);
                                textViewProgress.setText("progress: " + ProgressStatus + "%");

                            });

                        }
                    }).start();
                });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}