package com.example.vedionet;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.widget.VideoView;
import android.widget.MediaController;
import android.media.MediaPlayer;
import android.net.Uri;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
    }
        public void PlayVideo(View view){
            VideoView v= findViewById(R.id.videoView);
            String videoUrl = "https://cdn.pixabay.com/video/2016/04/02/2637-161442811_medium.mp4";
            Uri uri = Uri.parse(videoUrl);
            v.setVideoURI(uri);
            MediaController mediaController = new MediaController(this);
            mediaController.setAnchorView(v);
            mediaController.setMediaPlayer(v);
            v.setMediaController(mediaController);
            v.start();

        }

    }
