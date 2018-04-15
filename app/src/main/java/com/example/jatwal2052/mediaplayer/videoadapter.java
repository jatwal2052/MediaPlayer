package com.example.jatwal2052.mediaplayer;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import static android.app.Activity.RESULT_OK;

public class videoadapter extends RecyclerView.Adapter<videoadapter.mediaViewHolder> {

    ArrayList<HashMap> aa;
    Activity mAcivity;
    RecyclerView myList1;

    Context context;

   // playlists_adaptarsng adapter;



    Context contex;

    videoadapter(Context context, Activity mAcivity) {
        contex = context;
        this.mAcivity = mAcivity;
        aa = MainActivity.arrayListV;
    }

    @Override
    public mediaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.laylay, parent, false);

        return new mediaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final mediaViewHolder holder, final int position) {

        holder.edit.setText(aa.get(position).get("songTitle").toString());

        if (MainActivity.mplayer.isPlaying() && MainActivity.currentlist.get(MainActivity.currentPlayIndex).get("songTitle").toString() == aa.get(position).get("songTitle"))
            holder.playing.setVisibility(View.VISIBLE);
        else
            holder.playing.setVisibility(View.INVISIBLE);

    /*    holder.lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


               Intent i = new Intent();
                i.putExtra("Id",position);

                MainActivity.mplayer.pause();

                MainActivity.check=0;
                mAcivity.setResult(1,i);
                mAcivity.finish();


                //displaying the popup

            }
        });
*/




        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent=new Intent(contex,videoactivity.class);
                intent.putExtra("key2",position);
               // intent.addFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
                mAcivity.startActivity(intent);
               // mAcivity.finish();
                Toast.makeText(contex, " Playing", Toast.LENGTH_SHORT).show();

            }
        });


      /*  holder.menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                PopupMenu popup = new PopupMenu(contex, view);
                //inflating menu from xml resource
                popup.inflate(R.menu.menu);
                popup.show();

                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu1:
                                Intent i = new Intent();
                                i.putExtra("index", position);
                                System.out.println(position + "hey");
                                MainActivity.mplayer.pause();

                                MainActivity.check = 0;
                                mAcivity.setResult(1, i);
                                mAcivity.finish();


                                Toast.makeText(contex, "Playing", Toast.LENGTH_SHORT).show();
                                break;

                            case R.id.menu3:
                                AlertDialog.Builder builder = new AlertDialog.Builder(view.getRootView().getContext());
                                LayoutInflater inflater = mAcivity.getLayoutInflater();

                                final View dialogView = inflater.inflate(R.layout.activity_playlist, null);

                                builder.setView(dialogView);
                                builder.setTitle("Add to Playlist");


                                MainActivity.b = builder.create();
                                MainActivity.b.show();
                                myList1 = (RecyclerView) dialogView.findViewById(R.id.ll);


                                myList1.setLayoutManager(new LinearLayoutManager(contex));


                                // Toast.makeText(context,arrayList.get(i), Toast.LENGTH_SHORT).show();

                                adapter = new playlists_adaptarsng(contex, mAcivity,position);

                                myList1.setAdapter(adapter);


                                //
                                break;
                        }

                        return false;
                    }
                });

            }

        });
        MediaMetadataRetriever metaRetriver;
        byte[] art1;

        metaRetriver = new MediaMetadataRetriever();
        metaRetriver.setDataSource(MainActivity.arrayList.get(position).get("songPath").toString());
        try {
            art1 = metaRetriver.getEmbeddedPicture();
            Bitmap songImage = BitmapFactory.decodeByteArray(art1, 0, art1.length);
            holder.art.setImageBitmap(songImage);
            holder.album.setText(metaRetriver.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM));
            holder.artist.setText(metaRetriver.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST));
            // genre.setText(metaRetriver .extractMetadata(MediaMetadataRetriever.METADATA_KEY_GENRE));
        } catch (Exception e) {
            holder.art.setImageResource(R.drawable.abc);
            holder.album.setText("Unknown Album");
            holder.artist.setText("Unknown Artist");
            //genre.setText("Unknown Genre");
        }*/

    }

    @Override
    public int getItemCount() {
        return aa.size();
    }


    public class mediaViewHolder extends RecyclerView.ViewHolder {
        TextView edit;
        ImageView playing;
        LinearLayout lay;
        ImageView menu;
        ImageView art;
        TextView album;
        TextView artist;


        mediaViewHolder(View itemView) {

            super(itemView);
            edit = (TextView) itemView.findViewById(R.id.sname);
            playing = (ImageView) itemView.findViewById(R.id.playing);
            lay = (LinearLayout) itemView.findViewById(R.id.lay);
            menu = (ImageView) itemView.findViewById(R.id.menui);
            art = (ImageView) itemView.findViewById(R.id.tag);
            album = (TextView) itemView.findViewById(R.id.album);
            artist = (TextView) itemView.findViewById(R.id.artist);

        }
    }
}