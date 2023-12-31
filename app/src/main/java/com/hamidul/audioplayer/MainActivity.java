package com.hamidul.audioplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.thekhaeng.pushdownanim.PushDownAnim;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    ImageView song_1,song_2,song_3;
    ImageView image1,image2,image3;
    MediaPlayer mediaPlayer;
    BroadcastReceiver broadcastReceiver;
    Animation custom_animation,zoom_in;
    LinearLayout push;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_main);

        song_1 = findViewById(R.id.song_1);
        song_2 = findViewById(R.id.song_2);
        song_3 = findViewById(R.id.song_3);
        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        push = findViewById(R.id.push);


        PushDownAnim.setPushDownAnimTo(song_1,song_2,song_3)
                .setScale(PushDownAnim.MODE_SCALE,0.80f);

        custom_animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.custom_animation);
        zoom_in = AnimationUtils.loadAnimation(MainActivity.this,R.anim.zoom_in);

        broadcastReceiver = new InternetConnection();
        registerNetwork();
        song_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (song_1.getTag()!=null && song_1.getTag().toString().contains("Play")){
                    if (mediaPlayer!=null) mediaPlayer.release();
                    mediaPlayer = new MediaPlayer();

                    try {
                        mediaPlayer.setDataSource("https://smhamidulcodding.000webhostapp.com/mp3/Alone_Sad_Ringtone.mp3");
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    icon();
                    song_1.setImageResource(R.drawable.pause_icon);
                    song_1.setTag("Pause");
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            song_1.setImageResource(R.drawable.play_icon);
                            song_1.setTag("Play");
                        }
                    });

                } else {
                    if (mediaPlayer!=null) mediaPlayer.release();
                    song_1.setImageResource(R.drawable.play_icon);
                    song_1.setTag("Play");
                }
            }
        });

        song_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (song_2.getTag()!=null && song_2.getTag().toString().contains("Play")){
                    if (mediaPlayer!=null) mediaPlayer.release();
                    mediaPlayer = new MediaPlayer();
                    try {
                        mediaPlayer.setDataSource("https://smhamidulcodding.000webhostapp.com/mp3/sad_ringtone__emotional_ringtone__breakup_ringtone__.mp3");
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    icon();
                    song_2.setImageResource(R.drawable.pause_icon);
                    song_2.setTag("Pause");
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            song_2.setImageResource(R.drawable.play_icon);
                            song_2.setTag("Play");
                        }
                    });

                } else {
                    if (mediaPlayer!=null) mediaPlayer.release();
                    song_2.setImageResource(R.drawable.play_icon);
                    song_2.setTag("Play");
                }
            }
        });

        song_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (song_3.getTag()!=null && song_3.getTag().toString().contains("Play")){
                    if (mediaPlayer!=null) mediaPlayer.release();
                    mediaPlayer = new MediaPlayer();
                    try {
                        mediaPlayer.setDataSource("https://smhamidulcodding.000webhostapp.com/mp3/viral_ringtone.mp3");
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    icon();
                    song_3.setImageResource(R.drawable.pause_icon);
                    song_3.setTag("Pause");
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            song_3.setImageResource(R.drawable.play_icon);
                            song_3.setTag("Play");
                        }
                    });

                } else {
                    if (mediaPlayer!=null) mediaPlayer.release();
                    song_3.setImageResource(R.drawable.play_icon);
                    song_3.setTag("Play");
                }
            }
        });

    }

    private void icon(){

        if (song_1.getTag().toString().contains("Pause")){
            song_1.setImageResource(R.drawable.play_icon);
            song_1.setTag("Play");
        }
        if (song_2.getTag().toString().contains("Pause")){
            song_2.setImageResource(R.drawable.play_icon);
            song_2.setTag("Play");
        }
        if (song_3.getTag().toString().contains("Pause")){
            song_3.setImageResource(R.drawable.play_icon);
            song_3.setTag("Play");
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.release();
        icon();
    }

    protected void registerNetwork(){
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
            registerReceiver(broadcastReceiver,new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
    }

    protected void unregisterNetwork(){
        try {
            unregisterReceiver(broadcastReceiver);
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterNetwork();
    }
}
