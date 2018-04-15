package com.example.jatwal2052.mediaplayer;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class songlist extends AppCompatActivity {

    RecyclerView myList;

    Context context;

    mediaAdapter adapter;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_songlist);

        context = getApplicationContext();

        myList=(RecyclerView)findViewById(R.id.ll);

        myList.setLayoutManager(new LinearLayoutManager(this));


              // Toast.makeText(context,arrayList.get(i), Toast.LENGTH_SHORT).show();

                adapter = new mediaAdapter(context,this);

                myList.setAdapter(adapter);


    }
    public void playlist(View view)
    {
       MainActivity.b.dismiss();
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText edt = (EditText) dialogView.findViewById(R.id.edit1);

        dialogBuilder.setTitle("Create New Playlist");


        dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
           MainActivity.list.add(edt.getText().toString());
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
                //Toast.makeText(context,"Hoolala", Toast.LENGTH_SHORT).show();

    }
}
