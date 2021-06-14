package com.example.gtihms.authority;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.example.gtihms.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class Room extends AppCompatActivity {

    AdapterRoom myadapter;
    RecyclerView recyclerView;
    DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

//        ImageButton roomback = findViewById(R.id.roomback);
//        roomback.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) { finish(); }
//        });

        recyclerView = findViewById(R.id.recyclerView);

        database = FirebaseDatabase.getInstance().getReference("Room");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    try {
        FirebaseRecyclerOptions<ModelRoom> options =
                new FirebaseRecyclerOptions.Builder<ModelRoom>()
                        .setQuery(database, ModelRoom.class)
                        .build();
        Log.d(TAG, "onCreate: "+options.toString());
        //System.out.print(options.toString());

        myadapter = new AdapterRoom(options);
        recyclerView.setAdapter(myadapter);
    } catch(Exception e){
        System.out.println("Room.class" + String.valueOf(e));
    }
    }

    @Override protected void onStart() {
        super.onStart();
        myadapter.startListening();
    }

    @Override protected void onStop() {
        super.onStop();
        myadapter.stopListening();
    }
}