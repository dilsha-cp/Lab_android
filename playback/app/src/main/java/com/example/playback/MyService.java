package com.example.playback;

import android.app.Service;//this is the base class to implement a service
import android.content.Intent;
import android.os.IBinder;

//import
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.provider.Settings;


public class MyService extends Service {

    //declare object for media player
    private MediaPlayer player;

    public MyService() {
    }

    //optional onCreate() : initialization task

    //Invoked when service is call from main activity, startService()
    public int onStartCommand(Intent intent,int flags,int startId){
        if(intent != null){
            String action = intent.getAction(); //stores the intent passed from main activity[START,PAUSE or RESUME]

            if(action != null){
                switch(action){
                    case "START":
                        player = MediaPlayer.create(this,Settings.System.DEFAULT_RINGTONE_URI);
                        player.setLooping(true);
                        player.start();
                        break;

                    case "PAUSE":
                        if(player != null && player.isPlaying()){
                            player.pause();
                        }
                        break;

                    case "RESUME":
                        if(player != null && !player.isPlaying()){
                            player.start();
                        }
                        break;
                }
            }
        }
        return START_STICKY;
    }

    //For stop button[to destroy service]
    public void onDestroy(){
        super.onDestroy();
        if(player!=null){
            //releases media player
            player.release();
            player=null;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}