package com.example.jatwal2052.mediaplayer;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

public class particularlist extends AppCompatActivity {

    RecyclerView myList;

    Context context;

    partiAdapter adapter;
    ArrayList<HashMap> array;
String y;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle=getIntent().getExtras();

       int pos=bundle.getInt("key");
       y= MainActivity.list.get(pos);
        array=MainActivity.hash.get(y);



        setContentView(R.layout.activity_particularlist);

        context = getApplicationContext();

        myList=(RecyclerView)findViewById(R.id.ll5);

        myList.setLayoutManager(new LinearLayoutManager(this));


        // Toast.makeText(context,arrayList.get(i), Toast.LENGTH_SHORT).show();
        adapter = new partiAdapter(context,this,array,pos);

        myList.setAdapter(adapter);


    }

}