package com.example.jatwal2052.mediaplayer;

import android.media.MediaPlayer;
import android.support.v7.app.ActionBar;
import android.util.DisplayMetrics;
import android.widget.MediaController;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.VideoView;

import static com.example.jatwal2052.mediaplayer.MainActivity.arrayListV;
import static com.example.jatwal2052.mediaplayer.MainActivity.currentlist;

public class videoactivity extends AppCompatActivity {

   public static VideoView videoView  ;
    int pos;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videoactivity);
       videoView = (VideoView) findViewById(R.id.vplayer);
      //  currentlist=MainActivity.arrayListV;
     //   Uri uri=Uri.parse(currentlist.get(MainActivity.currentPlayIndex).get("songPath").toString());
      //  videoView.setVideoURI(uri);
     //   videoView.start();
        Bundle bundle=getIntent().getExtras();

        pos=bundle.getInt("key2");

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        Uri uri = Uri.parse(arrayListV.get(pos).get("songPath").toString());
        videoView.setVideoURI(uri);
        videoView.start();
    }
    public void videoplay(View view)
    {

      //  mplayer = MediaPlayer.create(MainActivity.this, Uri.parse(currentlist.get(index).get("songPath").toString()));


    }
}
