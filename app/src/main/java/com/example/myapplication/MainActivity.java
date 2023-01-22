package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Trace;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView donkey;
    AudioManager am;
    MediaPlayer mp;
    int MaxVolume;
    ImageButton pause,play;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        donkey = (ImageView) findViewById(R.id.donkey);
        pause = findViewById(R.id.pause);
        play = findViewById(R.id.play);
        mp = MediaPlayer.create(this,R.raw.song);
        GifRunner gifRunner = new GifRunner(donkey, true);
        gifRunner.start();
        mp.start();
        am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        MaxVolume = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        am.setStreamVolume(AudioManager.STREAM_MUSIC,MaxVolume/2,0);
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.pause();
                gifRunner.SetPlay(false);
            }
        });
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                gifRunner.SetPlay(true);
            }
        });

    }
    protected void onPause()
    {
        super.onPause();
        mp.release();

    }
    public class GifRunner extends Thread {
        private ImageView donkey;
        private boolean play;
        public GifRunner(ImageView donkey, boolean play){
            this.donkey = donkey;
            this.play = play;
        }
        public void SetPlay(boolean play){
            this.play = play;
        }
        public void run(){
            int ImageKey;
            //int savei = 0;
            boolean stopped = false;
            String DrawableName;
            //donkey = (ImageView) donkey.findViewById(R.id.donkey);
            for (int i = 0; i < 12; i++){
                try{
                    Thread.sleep(40);
                }
                catch(InterruptedException e){

                }

                if(this.play){
                    //if(stopped == true){
                    //    i = savei;
                    //}
                    ImageKey = getResources().getIdentifier("p" + i,"drawable",getPackageName());
                    donkey.setImageResource(ImageKey);
                    //savei = i;

                }
                //if(!this.play){
                  //  stopped = true;
                //}
                if(i == 11){
                    i = 0;
                }
            }
        }
    }
}
