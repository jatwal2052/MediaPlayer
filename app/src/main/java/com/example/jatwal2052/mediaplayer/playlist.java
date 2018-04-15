package com.example.jatwal2052.mediaplayer;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class playlist extends AppCompatActivity {

    RecyclerView myList;

    Context context;

    playlists_adaptar adapter;

    TextView button;





    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);

       context = getApplicationContext();

        myList=(RecyclerView)findViewById(R.id.ll);

        myList.setLayoutManager(new LinearLayoutManager(this));

        button=(TextView) findViewById(R.id.playlist);
        // Toast.makeText(context,arrayList.get(i), Toast.LENGTH_SHORT).show();

        adapter = new playlists_adaptar(context,this);

        myList.setAdapter(adapter);


    }
    @Override
    protected void onStart() {

        super.onStart();



    }
    @Override
    protected void onStop(){
        super.onStop();

    }
    @Override

    protected void onPause(){
        super.onPause();

    }
    @Override
    protected void onResume() {
        super.onResume();


    }

    public void playlist(View view)
    {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText edt = (EditText) dialogView.findViewById(R.id.edit1);

        dialogBuilder.setTitle("Create New Playlist");

        dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
               MainActivity.list.add(edt.getText().toString());
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

    }
