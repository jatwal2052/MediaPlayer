package com.example.jatwal2052.mediaplayer;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class videol extends AppCompatActivity {

    RecyclerView myList;

    Context context;

    videoadapter adapter;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_songlist);

        context = getApplicationContext();

        myList=(RecyclerView)findViewById(R.id.ll);

        myList.setLayoutManager(new LinearLayoutManager(this));


        // Toast.makeText(context,arrayList.get(i), Toast.LENGTH_SHORT).show();

        adapter = new videoadapter(context,this);

        myList.setAdapter(adapter);


    }
}
