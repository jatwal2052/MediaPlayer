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



public class playlists_adaptar extends RecyclerView.Adapter<playlists_adaptar.mediaViewHolder> {
    ArrayList<String> aa;
    Activity mAcivity;

    Context contex;
    playlists_adaptar(Context context, Activity mAcivity) {
        contex = context;
        this.mAcivity = mAcivity;
        aa = MainActivity.list;
    }
    @Override
    public playlists_adaptar.mediaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.playlay, parent, false);

        return new playlists_adaptar.mediaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final playlists_adaptar.mediaViewHolder holder, final int position) {
        holder.edit.setText(aa.get(position));

        holder.menu.setVisibility(View.GONE);

        holder.lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<HashMap> array=new ArrayList<HashMap>();
                array=MainActivity.hash.get(aa.get(position));
                if(array.isEmpty())
                { Toast.makeText(contex, " Empty List", Toast.LENGTH_SHORT).show();}

                else{
                    Intent intent=new Intent(contex,particularlist.class);
                    intent.putExtra("key",position);
                    intent.addFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
                    mAcivity.startActivity(intent);
                    mAcivity.finish();
                    }}




        });
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<HashMap> array;
                array=MainActivity.hash.get(aa.get(position));
                if(array==null)
                { Toast.makeText(contex, " Empty List", Toast.LENGTH_SHORT).show();}
                else{
                    Intent intent=new Intent(contex,particularlist.class);
                    intent.putExtra("key",position);
                    intent.addFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
                    mAcivity.startActivity(intent);
                    mAcivity.finish();
                    Toast.makeText(contex, " Playing", Toast.LENGTH_SHORT).show();
                }


        }






        });

        holder.playing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 MainActivity.currentlist=MainActivity.hash.get(aa.get(position));
                 MainActivity.mplayer.start();


                Intent i = new Intent();
                i.putExtra("index", 0);

                MainActivity.mplayer.pause();

                MainActivity.check = 0;
                mAcivity.setResult(1, i);
                mAcivity.finish();


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
