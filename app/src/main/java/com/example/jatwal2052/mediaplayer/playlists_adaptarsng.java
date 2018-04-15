package com.example.jatwal2052.mediaplayer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import static android.app.Activity.RESULT_OK;



public class playlists_adaptarsng extends RecyclerView.Adapter<playlists_adaptarsng.mediaViewHolder> {
    ArrayList<String> aa;
    Activity mAcivity;
    int posit;
    Context contex;

    playlists_adaptarsng(Context context, Activity mAcivity, int posi) {
        contex = context;
        this.mAcivity = mAcivity;
        posit=posi;
        aa = MainActivity.list;
    }
    @Override
    public playlists_adaptarsng.mediaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.playlay, parent, false);

        return new playlists_adaptarsng.mediaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final playlists_adaptarsng.mediaViewHolder holder, final int position) {
        holder.edit.setText(aa.get(position));

        holder.menu.setVisibility(View.GONE);

        holder.lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //to differentiate playlist pop from mainactivity and playlist

                MainActivity.b.dismiss();

                ArrayList<HashMap> playlisty = new ArrayList<HashMap>();

                ArrayList<HashMap> xyz=new ArrayList<HashMap>();


                playlisty.add(MainActivity.arrayList.get(posit));

                xyz=MainActivity.hash.get(aa.get(position));

                if(xyz!=null)
                    playlisty.addAll(xyz);
                MainActivity.hash.put(aa.get(position), playlisty);
                xyz=MainActivity.hash.get(aa.get(position));

                Toast.makeText(contex, " Song Added", Toast.LENGTH_SHORT).show();



            }
        });
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    MainActivity.b.dismiss();

                    ArrayList<HashMap> playlisty = new ArrayList<HashMap>();

                    ArrayList<HashMap> xyz=new ArrayList<HashMap>();


                    playlisty.add(MainActivity.arrayList.get(posit));

                    xyz=MainActivity.hash.get(aa.get(position));

                    if(xyz!=null)
                        playlisty.addAll(xyz);
                    MainActivity.hash.put(aa.get(position), playlisty);

                    Toast.makeText(contex, " Song Added", Toast.LENGTH_SHORT).show();








            }
        });

        holder.playing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





            }
        });
    }

    @Override
    public int getItemCount() {
        return aa.size();
    }



    public class mediaViewHolder extends RecyclerView.ViewHolder
    {
        TextView edit;
        ImageView playing;
        LinearLayout lay;
        ImageView menu;
        mediaViewHolder(View itemView)
        {

            super(itemView);
            edit=(TextView)itemView.findViewById(R.id.sname);
            menu=(ImageView)itemView.findViewById(R.id.menui);
            playing=(ImageView)itemView.findViewById(R.id.playing);
            lay=(LinearLayout)itemView.findViewById(R.id.lay2);
        }
    }
}