package com.example.jatwal2052.mediaplayer;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

public class onlinevideo extends AppCompatActivity {
    private Button btnonce, btncontinuously, btnstop, btnplay;
    private VideoView vv;
    private MediaController mediacontroller;
    private Uri uri;
    private boolean isContinuously = false;
    private ProgressBar progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onlinevideo);
        final ProgressBar spinnerView = (ProgressBar) findViewById(R.id.my_spinner);
        vv=(VideoView)findViewById(R.id.vv);


         final MediaPlayer.OnInfoListener onInfoToPlayStateListener = new MediaPlayer.OnInfoListener() {

            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                if (MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START == what) {
                    spinnerView.setVisibility(View.GONE);
                }
                if (MediaPlayer.MEDIA_INFO_BUFFERING_START == what) {
                    spinnerView.setVisibility(View.VISIBLE);
                }
                if (MediaPlayer.MEDIA_INFO_BUFFERING_END == what) {
                    spinnerView.setVisibility(View.VISIBLE);
                }
                return false;
            }
        };
        vv.setOnInfoListener(onInfoToPlayStateListener);
        MediaController mediaController= new MediaController(this);
        mediaController.setAnchorView(vv);
        vv.setMediaController(mediaController);
        Uri uri=Uri.parse("http://videocdn.bodybuilding.com/video/mp4/62000/62792m.mp4");
        vv.start();

        //Uri uri=Uri.parse("rtsp://r7---sn-4g57kue6.googlevideo.com/Ck0LENy73wIaRAmk3cJBg-iaXhMYDSANFC3u0pRWMOCoAUIJbXYtZ29vZ2xlSARSBXdhdGNoYIaluaTkzciOVooBCzVxRjNraG5XcXdnDA==/D693A8E7577C3A29E60C292B42C9C87D7C25A565.762A63DC4CA0A028DA83256C6A79E5F160CBEDA3/yt6/1/video.3gp");
        //vv.setMediaController(mediaController);
        vv.setVideoURI(uri);
        vv.requestFocus();
        vv.start();

    }
    }

