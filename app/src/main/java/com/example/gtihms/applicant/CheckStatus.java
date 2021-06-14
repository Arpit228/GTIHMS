package com.example.gtihms.applicant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gtihms.Login;
import com.example.gtihms.R;
import com.example.gtihms.authority.AuthorityDashboard;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CheckStatus extends AppCompatActivity {

    DatabaseReference reference;
    TextView empiddata, prgdata, locdata, startdata, enddata, statusdata, roomdata;
    String id, prg, loc, start, end, status, room;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_status);

        ImageButton back = findViewById(R.id.backcheck);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        id = intent.getStringExtra("ID");
        empiddata = findViewById(R.id.empiddata);
        prgdata = findViewById(R.id.prgdata);
        locdata = findViewById(R.id.locdata);
        startdata = findViewById(R.id.startdatedata);
        enddata = findViewById(R.id.enddatedata);
        statusdata = findViewById(R.id.statusdata);
        roomdata = findViewById(R.id.roomdata);

        reference = FirebaseDatabase.getInstance().getReference().child("Room_Requests").child(id);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                prg = snapshot.child("programme").getValue().toString();
                loc = snapshot.child("location").getValue().toString();
                start = snapshot.child("startdate").getValue().toString();
                end = snapshot.child("enddate").getValue().toString();
                status = snapshot.child("status").getValue().toString();
                room = snapshot.child("alloted_room").getValue().toString();

                empiddata.setText(id);
                prgdata.setText(prg);
                locdata.setText(loc);
                startdata.setText(start);
                enddata.setText(end);
                statusdata.setText(status);
                if(room.equals("0"))
                    roomdata.setText("Not Allotted");
                else
                    roomdata.setText(room);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }
}