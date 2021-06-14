package com.example.gtihms;

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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {

    EditText empid, empname, empdesgn, empmail, emppwd;
    String id, name, email, designation, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        TextView signuptologin = (TextView) findViewById(R.id.signuptologin);
        signuptologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Signup.this, Login.class);
                startActivity(intent);
            }
        });
    }

    public void process(View view){
        RadioGroup radioGroup = findViewById(R.id.profilesignup);
        final RadioButton[] profile = new RadioButton[1];

        empid = findViewById(R.id.empid);
        id = empid.getText().toString().trim();
        empname = findViewById(R.id.name);
        name = empname.getText().toString().trim();
        empdesgn = findViewById(R.id.designation);
        designation = empdesgn.getText().toString().trim();
        empmail = findViewById(R.id.email);
        email = empmail.getText().toString().trim();
        emppwd = findViewById(R.id.password);
        password = emppwd.getText().toString().trim();

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference node = db.getReference();
        DataHolder obj = new DataHolder(name, designation, email, password);

        int select = radioGroup.getCheckedRadioButtonId();
        if(select == -1) {
            Toast.makeText(getApplicationContext(), "Select a profile", Toast.LENGTH_SHORT).show();
        }
        else if(id.isEmpty() || name.isEmpty() || designation.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Fill all the fields", Toast.LENGTH_SHORT).show();
        }
        else {
            profile[0] = (RadioButton) findViewById(select);
            String p = profile[0].getText().toString();

            if (p.equals("Authority")) {
                obj = new DataHolder(name, designation, email, password);
                node = db.getReference();
                node.child("Authority").child(id).setValue(obj);
                Intent intent = new Intent(Signup.this, AuthorityDashboard.class);
                intent.putExtra("ID", id);
                intent.putExtra("NAME", name);
                startActivity(intent);
            } else if (p.equals("Applicant")) {
                node.child("Applicant").child(id).setValue(obj);
                Intent intent = new Intent(Signup.this, ApplicantDashboard.class);
                intent.putExtra("ID", id);
                intent.putExtra("NAME", name);
                startActivity(intent);
            }
            Toast.makeText(getApplicationContext(), "Signed up successfully", Toast.LENGTH_SHORT).show();
        }
    }
}