package com.example.DcDriver;

import android.app.ActionBar;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.VideoView;

public class IntroActivity extends AppCompatActivity {

    private Button BtnDriver;
    private Button BtnPassenger;
    private String TAG = "VideoActivity";
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        BtnDriver = findViewById(R.id.button);
        BtnPassenger = findViewById(R.id.button2);

        Animation anima = AnimationUtils.loadAnimation(this,R.anim.anim);
        BtnDriver.setAnimation(anima);
        BtnPassenger.setAnimation(anima);

        BtnDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntroActivity.this, DMainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        BtnPassenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntroActivity.this, PMainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // 동영상 배경
//        videoView = (VideoView) findViewById(R.id.videoView);
//        videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.back));
//        videoView.start();
//        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mp) {
//                mp.setLooping(true);
//            }
//        });



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                BtnDriver.setVisibility(View.VISIBLE);
                BtnPassenger.setVisibility(View.VISIBLE);
            }
        },2000);




    }
}
