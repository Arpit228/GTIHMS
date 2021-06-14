package com.example.gtihms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gtihms.applicant.ApplicantDashboard;
import com.example.gtihms.authority.AuthorityDashboard;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Update extends AppCompatActivity {

    TextView id;
    DatabaseReference reference;
    EditText upname, updesgn, upemail, uppwd;
    String name, desgn, email, password, profile, eid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        id = findViewById(R.id.fixempid);
        Intent intent = getIntent();
        eid = intent.getStringExtra("ID");
        id.setText("Employee ID: " + eid);
        profile = intent.getStringExtra("PROFILE");

        ImageButton back = findViewById(R.id.updateback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        upname = findViewById(R.id.upname);
        updesgn = findViewById(R.id.updesignation);
        upemail = findViewById(R.id.upemail);
        uppwd = findViewById(R.id.uppwd);

        reference = FirebaseDatabase.getInstance().getReference().child(profile).child(eid);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                password = snapshot.child("password").getValue().toString();
                name = snapshot.child("name").getValue().toString();
                desgn = snapshot.child("designation").getValue().toString();
                email = snapshot.child("email").getValue().toString();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });

//        if (profile.equals("Authority")) {
//            reference = FirebaseDatabase.getInstance().getReference().child("Authority").child(eid);
//            reference.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    password = snapshot.child("password").getValue().toString();
//                    name = snapshot.child("name").getValue().toString();
//                    desgn = snapshot.child("designation").getValue().toString();
//                    email = snapshot.child("email").getValue().toString();
//                }
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) { }
//            });
//        }
//        else {
//            reference = FirebaseDatabase.getInstance().getReference().child("Applicant").child(eid);
//            reference.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    password = snapshot.child("password").getValue().toString();
//                    name = snapshot.child("name").getValue().toString();
//                    desgn = snapshot.child("designation").getValue().toString();
//                    email = snapshot.child("email").getValue().toString();
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) { }
//            });
//        }

        upname.setText(name, TextView.BufferType.EDITABLE);
        upemail.setText(email, TextView.BufferType.EDITABLE);
        updesgn.setText(desgn, TextView.BufferType.EDITABLE);
        uppwd.setText(password, TextView.BufferType.EDITABLE);
    }

    public void saveupdate(View view) {

        password = uppwd.getText().toString();
        name =  upname.getText().toString();
        desgn =  updesgn.getText().toString();
        email =  upemail.getText().toString();
        if(password.isEmpty() || name.isEmpty() || desgn.isEmpty() || email.isEmpty()){
            Toast.makeText(getApplicationContext(), "Fill all fields", Toast.LENGTH_SHORT).show();
        }
        else {
            DataHolder obj = new DataHolder(name, desgn, email, password);
            FirebaseDatabase db = FirebaseDatabase.getInstance();
            DatabaseReference node = db.getReference();
            node.child(profile).child(eid).setValue(obj);

            Toast.makeText(getApplicationContext(), "Details Updated", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}