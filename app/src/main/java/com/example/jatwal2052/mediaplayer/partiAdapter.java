package com.example.jatwal2052.mediaplayer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.os.Bundle;
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
import java.util.LinkedHashSet;
import java.util.Set;

import static android.app.Activity.RESULT_OK;

public class partiAdapter extends RecyclerView.Adapter<partiAdapter.mediaViewHolder> {


    Activity mAcivity;

    Context contex;
int pos;

   ArrayList<HashMap> arrayy;


    partiAdapter(Context context, Activity mAcivity, ArrayList array,int poss)
    {
        contex=context;
        this.mAcivity= mAcivity;
        arrayy=array;
        pos=poss;

    }

    @Override
    public mediaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.laylay, parent, false);

        return new mediaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final mediaViewHolder holder,final int position) {




        System.out.println(arrayy.size()+"xyzz");
        holder.playing.setVisibility(View.GONE);
        holder.menu.setVisibility(View.GONE);

        holder.edit.setText(arrayy.get(position).get("songTitle").toString());

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MainActivity.currentlist=MainActivity.hash.get(MainActivity.list.get(pos));
                Intent i = new Intent();
                i.putExtra("Idd", position);

                MainActivity.mplayer.pause();

                MainActivity.check = 0;
                mAcivity.setResult(5, i);
                mAcivity.finish();


            }
        });
        MediaMetadataRetriever metaRetriver;
        byte[] art1;

        metaRetriver = new MediaMetadataRetriever();
        metaRetriver.setDataSource(arrayy.get(position).get("songPath").toString());
        try {
            art1 = metaRetriver.getEmbeddedPicture();
            Bitmap songImage = BitmapFactory.decodeByteArray(art1, 0, art1.length);
            holder.art.setImageBitmap(songImage);
            holder.album.setText(metaRetriver .extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM));
            holder.artist.setText(metaRetriver .extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST));
            // genre.setText(metaRetriver .extractMetadata(MediaMetadataRetriever.METADATA_KEY_GENRE));
        }
        catch (Exception e) {
            holder.art.setImageResource(R.drawable.abc);
            holder. album.setText("Unknown Album");
            holder.artist.setText("Unknown Artist");
            //genre.setText("Unknown Genre");
        }

    }

    @Override
    public int getItemCount() {
        return arrayy.size();
    }



    public class mediaViewHolder extends RecyclerView.ViewHolder
    {
        TextView edit;
        ImageView playing;
        LinearLayout lay;
        ImageView menu;
        ImageView art;
        TextView album;
        TextView artist;
        mediaViewHolder(View itemView)
        {

            super(itemView);
            edit=(TextView)itemView.findViewById(R.id.sname);
            playing=(ImageView)itemView.findViewById(R.id.playing);
            lay=(LinearLayout)itemView.findViewById(R.id.lay);
            menu=(ImageView)itemView.findViewById(R.id.menui);
            art=(ImageView)itemView.findViewById(R.id.tag);
            album=(TextView)itemView.findViewById(R.id.album);
            artist=(TextView)itemView.findViewById(R.id.artist);
        }
    }}