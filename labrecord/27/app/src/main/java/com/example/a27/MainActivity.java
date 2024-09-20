package com.example.a27;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.loader.content.AsyncTaskLoader;

public class MainActivity extends AppCompatActivity {
    private Button StartButton;
    private ProgressBar progressBar;
    private TextView resultTextview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
       StartButton=findViewById(R.id.startButton);
       progressBar=findViewById(R.id.progressBar);
       resultTextview=findViewById(R.id.resultTextView);
       StartButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               //Execute the asynctask
               new MyAsynctask().execute();

           }
       });

    }
    //asynctask class
    private class MyAsynctask extends AsyncTask<Void,Integer,String>{
        protected void onPreExecute(){
            //setup a ui before the background task starts
            progressBar.setVisibility(View.VISIBLE);
            resultTextview.setText("Starting the task");
        }
        protected String doInBackground(Void... Voids){
            //simulate a long running task
            for(int i=1;i<=5;i++){
                try{
                    Thread.sleep(1000);//sleep for one second
                    publishProgress(i*20);//update progress 20%,40%

                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            return "Task completed";
        }
        protected void onProgressUpdate(Integer...values){
            //update the progress bar
            int progress=values[0];
            resultTextview.setText("progress: " + progress + "%");

        }
        protected void onPostExecute(String result){
            //update ui after task completeion
            progressBar.setVisibility(View.GONE);
            resultTextview.setText(result);
        }

    }
}


