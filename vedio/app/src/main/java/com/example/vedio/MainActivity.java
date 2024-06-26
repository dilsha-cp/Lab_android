package com.example.vedio;

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


public class MainActivity extends AppCompatActivity {

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
    }
        public void PlayVideo(View view){
            VideoView v= findViewById(R.id.videoView);
            v.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.vedio);
            MediaController mediaController = new MediaController(this);
            // sets the anchor view for the videoView
            mediaController.setAnchorView(v);
            // sets the media player to the videoView
            mediaController.setMediaPlayer(v);
            // sets the media controller to the videoView
            v.setMediaController(mediaController);
            // starts the video
            v.start();



        }

}