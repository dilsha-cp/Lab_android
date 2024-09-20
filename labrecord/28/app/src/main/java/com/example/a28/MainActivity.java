package com.example.a28;



import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView tvStatus;
    private Button btnDownload;

    // URL to download file from (replace this with your own file URL)
    private static final String FILE_URL = "https://www.tutorialspoint.com/android/android_tutorial.pdf";
    private static final String FILE_NAME = "android_tutorial.pdfp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        tvStatus = findViewById(R.id.tvstatus);
        btnDownload = findViewById(R.id.btndownload);

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start downloading the file
                new DownloadFileTask().execute(FILE_URL);
            }
        });
    }

    // AsyncTask to download file in background and update progress
    private class DownloadFileTask extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setProgress(0);
            tvStatus.setText("Starting download...");
        }

        @Override
        protected String doInBackground(String... urls) {
            String fileUrl = urls[0];
            String filePath = null;
            InputStream inputStream = null;
            FileOutputStream outputStream = null;
            HttpURLConnection connection = null;

            try {
                URL url = new URL(fileUrl);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                // Check if the connection is successful
                if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    return "Server returned HTTP " + connection.getResponseCode() + " " + connection.getResponseMessage();
                }

                // Get file length for progress tracking
                int fileLength = connection.getContentLength();

                // Input stream to read file
                inputStream = new BufferedInputStream(connection.getInputStream());

                // Output stream to save file
                File downloadDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                File outputFile = new File(downloadDir, FILE_NAME);
                outputStream = new FileOutputStream(outputFile);
                filePath = outputFile.getAbsolutePath();

                byte[] data = new byte[4096];
                long total = 0;
                int count;
                while ((count = inputStream.read(data)) != -1) {
                    total += count;
                    // Write data to file
                    outputStream.write(data, 0, count);

                    // Update progress (total file length is used to calculate percentage)
                    if (fileLength > 0) {
                        publishProgress((int) (total * 100 / fileLength));
                    }
                }

            } catch (Exception e) {
                return e.toString();
            } finally {
                try {
                    if (inputStream != null) inputStream.close();
                    if (outputStream != null) outputStream.close();
                    if (connection != null) connection.disconnect();
                } catch (Exception ignored) {
                }
            }

            return filePath;
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            super.onProgressUpdate(progress);
            // Update progress bar
            progressBar.setProgress(progress[0]);
            tvStatus.setText("Downloaded: " + progress[0] + "%");
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result != null) {
                tvStatus.setText("File downloaded to: " + result);
                Toast.makeText(MainActivity.this, "Download Complete", Toast.LENGTH_LONG).show();
            } else {
                tvStatus.setText("Download failed");
            }
        }
    }
}
