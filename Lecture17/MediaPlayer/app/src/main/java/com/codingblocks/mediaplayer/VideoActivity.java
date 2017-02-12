package com.codingblocks.mediaplayer;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity {

    VideoView vv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        vv = (VideoView) findViewById(R.id.videoView);
        MediaController mController = new MediaController(this);

        vv.setMediaController(mController);
        vv.setVideoURI(Uri.parse
                ("android.resource://com.codingblocks.mediaplayer/" + R.raw.video)
        );
        vv.start();

    }
}
