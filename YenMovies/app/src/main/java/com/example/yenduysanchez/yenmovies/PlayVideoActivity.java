package com.example.yenduysanchez.yenmovies;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * Created by Yenduy Sanchez on 11/20/2016.
 */

public class PlayVideoActivity extends AppCompatActivity {

    private VideoView video;
    private MediaController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_layout);

        Intent intent = getIntent();
        String videoUrl = intent.getStringExtra("url");

        video = (VideoView) findViewById(R.id.videoView);
        controller = new MediaController(this);

        video.setMediaController(controller);
        video.setVideoURI(Uri.parse(videoUrl));

        video.requestFocus();
        video.start();
    }
}