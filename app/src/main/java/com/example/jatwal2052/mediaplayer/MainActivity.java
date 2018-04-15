package com.example.jatwal2052.mediaplayer;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.media.MediaPlayer.OnPreparedListener;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


public class MainActivity extends AppCompatActivity  {

    public static MediaPlayer mplayer;

    static TextView title;

    ImageView play;

    ImageView repeat;

    Handler handler;

    Runnable runnable;

    SeekBar seekBar;

    static Boolean isShuffle=false;

    static Boolean isRepeat=false;

    static int flag=1;

    public static int check=1;

    TextView t1;

    TextView t2;

    public static int currentPlayIndex;
    ImageView shuffle;

    ImageView album_art;


    RecyclerView myList1;

    Context context;

    playlists_adaptarmain adapter;

    Button button;

    ImageView forward;

    ImageView backward;



    static int REP_DELAY=50;



    static  boolean repeatall=false;

    public static ArrayList<HashMap>
            arrayListV=new ArrayList<HashMap>();
    public static ArrayList<HashMap>
            arrayList=new ArrayList<HashMap>();
    public static ArrayList<HashMap>
            currentlist=new ArrayList<HashMap>();

    public static ArrayList<String> list=new ArrayList();

    public int resId;

    private String[] STAR = { "*" };

    public static  int currentSongIndex;

    private Handler repeatUpdateHandler = new Handler();

    private boolean mAutoIncrement = false;

    private boolean mAutoDecrement= false;
    public static AlertDialog b;

    TextView album, artist, genre;
    MediaMetadataRetriever metaRetriver;
    byte[] art1;
    ImageView art;
    public static HashMap<String,ArrayList<HashMap>> hash=new HashMap();
    //Read more: http://mrbool.com/how-to-extract-meta-data-from-media-file-in-android/28130#ixzz4mVAJf32U



    @Override
    protected void onDestroy() {
        super.onDestroy();
        check=0;
        System.out.println("checkd"+check);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        System.out.println("checkr"+check);
        setContentView(R.layout.activity_main);

        arrayList.clear();

        init();


        seeklistner();


        if(check==1)

        {
            currentlist=arrayList;
            playsong(0);
            mplayer.pause();
            seekBar.setMax(mplayer.getDuration());
            currentSongIndex=0;
        }

        else
        {

            if (mplayer.isPlaying()) {
                currenthelp();
                play.setImageResource(R.drawable.pause);
                playcycle();

            }
            else
            {
                currenthelp();
                playcycle();
            }
        }




        //  fastfow();
        // fastback();







    }

    public void addlist(View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.activity_playlist, null);
        builder.setView(dialogView);
        builder.setTitle("Add to Playlist");


        b = builder.create();
        b.show();
        myList1=(RecyclerView)dialogView.findViewById(R.id.ll);
        context = getApplicationContext();



        myList1.setLayoutManager(new LinearLayoutManager(this));


        // Toast.makeText(context,arrayList.get(i), Toast.LENGTH_SHORT).show();

        adapter = new playlists_adaptarmain(context,this);

        myList1.setAdapter(adapter);
    }

    public void playlist(View view)
    {
        b.dismiss();
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText edt = (EditText) dialogView.findViewById(R.id.edit1);

        dialogBuilder.setTitle("Create New Playlist");

        dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                list.add(edt.getText().toString());
                Toast.makeText(getApplicationContext(),"Added",Toast.LENGTH_SHORT).show();
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }
    public  void play(View view)
    {
        //  MediaPlayer ring= MediaPlayer.create(MainActivity.this,R.raw.patake);

        if(flag==1)
        {


            mplayer.start();
            play.setImageResource(R.drawable.pause);
            flag=0;}
        // }
        else if(flag==0)

        {mplayer.pause();
            play.setImageResource(R.drawable.play);
            flag=1;
        }




        currenthelp();
        playcycle();



    }

    public void repeat(View v) {

        if(repeatall){

            isRepeat = false;
            repeatall=false;
            repeat.setImageResource(R.drawable.repeatoff);
            Toast.makeText(getApplicationContext(), "Repeat is OFF", Toast.LENGTH_SHORT).show();

        }else if(isRepeat==false&&repeatall==false){
            // make repeat to true
            isRepeat = true;
            Toast.makeText(getApplicationContext(), "Repeat Single is ON", Toast.LENGTH_SHORT).show();
            // make shuffle to false
            isShuffle = false;
            shuffle.setImageResource(R.drawable.shuffle);
            repeat.setImageResource(R.drawable.replay);
        }else
        {
            repeatall=true;
            isRepeat=false;
            isShuffle=false;
            shuffle.setImageResource(R.drawable.shuffle);
            repeat.setImageResource(R.drawable.allrepeat);

            Toast.makeText(getApplicationContext(), "Repeat ALL is ON", Toast.LENGTH_SHORT).show();
        }
    }


    public void shuffle(View view)
    {
        if(isShuffle)
        { isShuffle=false;
            Toast.makeText(getApplicationContext(),"Shuffle Off",Toast.LENGTH_SHORT).show();
            shuffle.setImageResource(R.drawable.shuffle);}
        else

        {  isShuffle= true;
            shuffle.setImageResource(R.drawable.couplesf);
            Toast.makeText(getApplicationContext(), "Shuffle is ON", Toast.LENGTH_SHORT).show();
            // make shuffle to false
            repeat.setImageResource(R.drawable.repeatoff);
            isRepeat = false;}

    }











    public void dotsopen(View view)
    {
        final CharSequence[] items = { "All Songs","All Videos", "Playlists" , "Online Songs" , "Online Video"};
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        // builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                // boolean result=Utility.checkPermission(MainActivity.this);
                String userChoosenTask;
                if (items[item].equals("All Songs")) {


                    Intent intent =new Intent(MainActivity.this,songlist.class);

                    startActivityForResult(intent,1);
                } else if (items[item].equals("All Videos")) {


                    Intent intent =new Intent(MainActivity.this,videol.class);


                    startActivityForResult(intent, 4);
                }
                else if (items[item].equals("Playlists")) {
                    Intent intent =new Intent(MainActivity.this,playlist.class);


                    startActivityForResult(intent, 2);




                }else if(items[item].equals("Online Songs"))
                {
                    Intent intent =new Intent(MainActivity.this,onlinesong.class);


                    startActivityForResult(intent, 3);
                }
                else if(items[item].equals("Online Video"))
                {
                    Intent intent =new Intent(MainActivity.this,onlinevideo.class);


                    startActivityForResult(intent, 4);
                }
            }
        });
        builder.show();

    }







    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(data!=null) {
            Toast.makeText(getApplicationContext(), "Working", Toast.LENGTH_SHORT).show();
            if (requestCode == 1)

            {
                int x, y, z;
                x = data.getIntExtra("Id", -1);
                y = data.getIntExtra("index", -1);
                if(resultCode==6)
                    currentlist=arrayListV;
                else
                    currentlist=arrayList;
                if(x!=-1)
                    currentSongIndex=x;
                else
                    currentSongIndex=y;
                //   System.out.println(currentSongIndex+"jsdfsj");
                playsong(currentSongIndex);
                if (repeatall) {

                    repeat.setImageResource(R.drawable.allrepeat);

                } else if (isRepeat) {

                    repeat.setImageResource(R.drawable.replay);
                } else if (isShuffle) {

                    shuffle.setImageResource(R.drawable.couplesf);

                }
            }
            else {
                if(resultCode==5) {
                    Toast.makeText(getApplicationContext(), "check", Toast.LENGTH_SHORT).show();
                    currentSongIndex = data.getIntExtra("Idd", -1);
                    playsong(currentSongIndex);
                }
                else {
                    currentSongIndex = data.getIntExtra("index", -1);
                    playsong(currentSongIndex);
                }
            }


        }}



    public void fow(View view)
    {
        mplayer.pause();
        if(isShuffle)
        {
            Random rand = new Random();
            currentSongIndex= rand.nextInt((currentlist.size() - 1) - 0 + 1) + 0;
            playsong(currentSongIndex);
        }else
        {
            if(currentSongIndex+1==currentlist.size())
            {
                playsong(0);
                currentSongIndex=0;
            }

            else
            {

                playsong(currentSongIndex+1);
                currentSongIndex=currentSongIndex+1;
            }

        }



    }

    public void back(View view)
    {
        mplayer.pause();
        if(isShuffle)
        {
            Random rand = new Random();
            currentSongIndex= rand.nextInt((currentlist.size() - 1) - 0 + 1) + 0;
            playsong(currentSongIndex);
        }else {
            if (currentSongIndex - 1 < 0) {

                playsong(currentlist.size() - 1);
                currentSongIndex = currentlist.size() - 1;
            } else {

                playsong(currentSongIndex - 1);
                currentSongIndex = currentSongIndex - 1;
            }

        }
    }


    public  void playcycle()
    {
        if(check!=1)
            System.out.println("playcle");
        currenthelp();
        if(mplayer.isPlaying())
        {
            runnable=new Runnable() {
                @Override
                public void run()
                {
                    playcycle();
                }
            };
            handler.postDelayed(runnable,0);
        }else
        {
            play.setImageResource(R.drawable.play);
            flag=1;

        }
    }



   /*  class RptUpdater implements Runnable {
         public void run() {
             if (mAutoIncrement) {
                 int currentPosition = mplayer.getCurrentPosition();
                 // check if seekForward time is lesser than song duration
                 if(currentPosition + 2000 <= mplayer.getDuration()){
                     // forward song
                     mplayer.seekTo(currentPosition +2000);
                 }else{
                     // forward to end position
                     mplayer.seekTo(mplayer.getDuration());
                 }if(mplayer.isPlaying()==false)
                 {
                    currenthelp();}
                 repeatUpdateHandler.postDelayed(new RptUpdater(),30);
             }
             else if( mAutoDecrement ){
                 int currentPosition = mplayer.getCurrentPosition();
                 // check if seekBackward time is greater than 0 sec
                 if(currentPosition - 2000 >= 0){
                     // forward song
                     mplayer.seekTo(currentPosition - 2000);
                 }else{
                     // backward to starting position
                     mplayer.seekTo(0);
                 }if(mplayer.isPlaying()==false){
                     currenthelp();
                     }

                 repeatUpdateHandler.postDelayed( new RptUpdater(),30 );
             }

         }
     }*/


    public void ListAllSongs()
    {
        Cursor cursor;
        Uri allsongsuri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String selection = MediaStore.Audio.Media.IS_MUSIC+ " != 0";

        if (isSdPresent()) {
            cursor = getContentResolver().query(allsongsuri, STAR, selection, null, null);//selection ->null

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        String songname = cursor
                                .getString(cursor
                                        .getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
                        int song_id = cursor.getInt(cursor
                                .getColumnIndex(MediaStore.Audio.Media._ID));

                        String fullpath = cursor.getString(cursor
                                .getColumnIndex(MediaStore.Audio.Media.DATA));
                        System.out.println("dfbfsdnbjfdb"+fullpath);
                        String albumname = cursor.getString(cursor
                                .getColumnIndex(MediaStore.Audio.Media.ALBUM));

                        HashMap<String, String> song = new HashMap<String, String>();
                        song.put("songTitle",songname);
                        song.put("songPath",fullpath);
                        arrayList.add(song);
                    } while (cursor.moveToNext());
                }
                cursor.close();
            }

        }

    }
    public void ListAllVideos()
    {
        Cursor cursor;
        Uri allsongsuri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        // String selection = MediaStore.Video.Media.DATA+ " != 0";

        if (isSdPresent()) {
            cursor = getContentResolver().query(allsongsuri, STAR, null, null, null);//selection ->null

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        String songname = cursor
                                .getString(cursor
                                        .getColumnIndex(MediaStore.Video.Media.DISPLAY_NAME));
                        int song_id = cursor.getInt(cursor
                                .getColumnIndex(MediaStore.Video.Media._ID));

                        String fullpath = cursor.getString(cursor
                                .getColumnIndex(MediaStore.Video.Media.DATA));
                        System.out.println("dfbfsdnbjfdb"+fullpath);
                        String albumname = cursor.getString(cursor
                                .getColumnIndex(MediaStore.Video.Media.ALBUM));

                        HashMap<String, String> song = new HashMap<String, String>();
                        song.put("songTitle",songname);
                        song.put("songPath",fullpath);
                        arrayListV.add(song);
                    } while (cursor.moveToNext());
                }
                cursor.close();
            }

        }

    }
    // Fetch Id's form xml

    public  void retriever(int index)
    {

        metaRetriver = new MediaMetadataRetriever();
        metaRetriver.setDataSource(currentlist.get(index).get("songPath").toString());
        try {
            art1 = metaRetriver.getEmbeddedPicture();
            Bitmap songImage = BitmapFactory .decodeByteArray(art1, 0, art1.length);
            art.setImageBitmap(songImage);
            // album.setText(metaRetriver .extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM));
            // artist.setText(metaRetriver .extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST));
            // genre.setText(metaRetriver .extractMetadata(MediaMetadataRetriever.METADATA_KEY_GENRE));
        }
        catch (Exception e) {
            art.setImageResource(R.drawable.abc);
            //album.setText("Unknown Album");
            // artist.setText("Unknown Artist"); genre.setText("Unknown Genre");
        }
    }


    public static boolean isSdPresent()
    {
        return android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED);
    }

    public void currenthelp()
    {
        if(check!=1)
            System.out.println("help");
        seekBar.setMax(mplayer.getDuration());
        seekBar.setProgress(mplayer.getCurrentPosition());
        long mediaDuration = mplayer.getDuration();
        long mediaPosition = mplayer.getCurrentPosition();
        t1.setText(Utility.milliSecondsToTimer(mediaPosition));
        t2.setText(Utility.milliSecondsToTimer(mediaDuration));
    }



    /* public  void fastfow()
     {
         forward.setOnLongClickListener(
                 new View.OnLongClickListener(){
                     public boolean onLongClick(View arg0) {
                         mAutoIncrement = true;

                         repeatUpdateHandler.post( new RptUpdater() );
                         return false;
                     }
                 }
         );

         forward.setOnTouchListener( new View.OnTouchListener() {
             public boolean onTouch(View v, MotionEvent event) {
                 if( (event.getAction()==MotionEvent.ACTION_UP || event.getAction()==MotionEvent.ACTION_CANCEL)
                         && mAutoIncrement )
                 {mAutoIncrement = false;

                 }
                 return false;
             }
         });
     }






     public void fastback()
     {
         backward.setOnLongClickListener(
                 new View.OnLongClickListener(){
                     public boolean onLongClick(View arg0) {
                         mAutoDecrement = true;

                         repeatUpdateHandler.post( new RptUpdater() );
                         return false;
                     }
                 }
         );

         backward.setOnTouchListener( new View.OnTouchListener() {
             public boolean onTouch(View v, MotionEvent event) {
                 if( (event.getAction()==MotionEvent.ACTION_UP || event.getAction()==MotionEvent.ACTION_CANCEL)
                         && mAutoDecrement){mAutoDecrement = false;

                 }
                 return false;
             }
         });
     }
 */
    public  void playsong(int index) {
        currentPlayIndex=index;

        title.setText(currentlist.get(index).get("songTitle").toString());

        // int id=getApplicationContext().getResources().getIdentifier(arrayList.get(index), "raw",getApplicationContext().getPackageName());

//        mplayer= MediaPlayer.create(MainActivity.this,id);
        try {

            mplayer = MediaPlayer.create(MainActivity.this, Uri.parse(currentlist.get(index).get("songPath").toString()));
            mplayer.setOnPreparedListener(new OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mplayer) {

                    //mplayer.pause();
                }
            });

            //mplayer.pause();
            // mplayer.prepare();
            mplayer.start();
            flag = 0;

            mplayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            seekBar.setMax(mplayer.getDuration());
            play.setImageResource(R.drawable.pause);
            int mediaDuration = mplayer.getDuration();
            int mediaPosition = mplayer.getCurrentPosition();
            t1.setText(Utility.milliSecondsToTimer(mediaPosition));
            t2.setText(Utility.milliSecondsToTimer(mediaDuration));
            retriever(index);
            playcycle();

            completion();

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void init(){

        play=(ImageView)findViewById(R.id.play);
        t1=(TextView)findViewById(R.id.tm1);
        t2=(TextView)findViewById(R.id.tm2);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        title=(TextView)findViewById(R.id.title);

        art=(ImageView)findViewById(R.id.art);

        handler=new Handler();
        shuffle=(ImageView)findViewById(R.id.shuffle);
        repeat=(ImageView)findViewById(R.id.repeat);


        album_art=(ImageView)findViewById(R.id.art);

        forward=(ImageView)findViewById(R.id.forward);

        backward=(ImageView)findViewById(R.id.backward);


        getSupportActionBar().hide();

        additem();




    }

    void additem() {


        boolean result = SongsManager.checkPermission(MainActivity.this);
        if (result == true)
        {
            ListAllSongs();
            ListAllVideos();
        }
        //arrayList.add("came");
        //arrayList.add("cellos");
        //arrayList.add("patake");
        //arrayList.add("shot");



    }






    public void seeklistner(){
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser)
                {
                    mplayer.seekTo(progress);
                    int mediaDuration = mplayer.getDuration();
                    int mediaPosition = mplayer.getCurrentPosition();

                    t1.setText(Utility.milliSecondsToTimer(mediaPosition));
                    t2.setText(Utility.milliSecondsToTimer(mediaDuration));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void completion()
    {
        mplayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                mp.pause();

                if(isRepeat){
                    // repeat is on play same song again
                    playsong(currentSongIndex);

                } else if(isShuffle){
                    // shuffle is on - play a random song
                    Random rand = new Random();
                    currentSongIndex= rand.nextInt((currentlist.size() - 1) - 0 + 1) + 0;
                    playsong(currentSongIndex);
                } else{
                    // no repeat or shuffle ON - play next song
                    if(currentSongIndex< (currentlist.size() - 1)){
                        playsong(currentSongIndex + 1);
                        currentSongIndex = currentSongIndex + 1;
                    }else if(repeatall){
                        // play first song
                        playsong(0);
                        currentSongIndex = 0;
                    }
                }
            }
        });

    }

    private void selectImage() {
    }



}
