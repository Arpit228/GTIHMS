package com.example.gtihms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gtihms.applicant.ApplicantDashboard;
import com.example.gtihms.authority.AuthorityDashboard;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    DatabaseReference reference;
    String id, password;
    RadioGroup radioGroup;
    RadioButton profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        radioGroup = (RadioGroup)findViewById(R.id.profile);
        TextView logintosignup = (TextView)findViewById(R.id.logintosignup);
        Button submit = (Button)findViewById(R.id.loginsubmit);

        EditText loginid = findViewById(R.id.loginid);
        EditText loginpwd = findViewById(R.id.loginpwd);

        logintosignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Signup.class);
                startActivity(intent);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = loginid.getText().toString().trim();
                password = loginpwd.getText().toString().trim();
                int select = radioGroup.getCheckedRadioButtonId();
                if(select == -1 || id.isEmpty() || password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Fill in the required fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    profile = (RadioButton) findViewById(select);
                    String p = profile.getText().toString();
                    if (p.equals("Authority")) {
                        reference = FirebaseDatabase.getInstance().getReference().child("Authority").child(id);
                        reference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                String pwd = snapshot.child("password").getValue().toString();
                                String name = snapshot.child("name").getValue().toString();
                                if(pwd.equals(password)){
                                    Intent intent = new Intent(Login.this, AuthorityDashboard.class);
                                    intent.putExtra("ID", id);
                                    intent.putExtra("NAME", name);
                                    Toast.makeText(getApplicationContext(), "Logging in...", Toast.LENGTH_SHORT).show();
                                    startActivity(intent);
                                }
                                else {
                                    Toast.makeText(getApplicationContext(), "Check your input", Toast.LENGTH_SHORT).show();
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) { }
                        });
                    } else if (p.equals("Applicant")) {
                        reference = FirebaseDatabase.getInstance().getReference().child("Applicant").child(id);
                        reference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                String pwd = snapshot.child("password").getValue().toString();
                                String name = snapshot.child("name").getValue().toString();
                                if(pwd.equals(password)){
                                    Intent intent = new Intent(Login.this, ApplicantDashboard.class);
                                    intent.putExtra("ID", id);
                                    intent.putExtra("NAME", name);
                                    Toast.makeText(getApplicationContext(), "Logging in...", Toast.LENGTH_SHORT).show();
                                    startActivity(intent);
                                }
                                else {
                                    Toast.makeText(getApplicationContext(), "Check your input", Toast.LENGTH_SHORT).show();
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });
                    }
                }
            }
        });
    }
}