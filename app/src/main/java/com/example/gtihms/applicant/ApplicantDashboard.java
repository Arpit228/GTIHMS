package com.example.gtihms.applicant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gtihms.MainActivity;
import com.example.gtihms.R;
import com.example.gtihms.Update;

public class ApplicantDashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applicant_dashboard);

        Button update = findViewById(R.id.update);
        Button checkstatus = findViewById(R.id.checkstatus);
        Button logout = findViewById(R.id.applicantlogout);
        Button bookroom = findViewById(R.id.bookroom);

        Intent intent = getIntent();
        String id = intent.getStringExtra("ID");
        String name = intent.getStringExtra("NAME");
        String welcomemsg = "Welcome " + name;
        TextView welcome = findViewById(R.id.welcomeapp);
        welcome.setText(welcomemsg);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ApplicantDashboard.this, Update.class);
                intent.putExtra("ID", id);
                intent.putExtra("PROFILE", "Applicant");
                startActivity(intent);
            }
        });

        checkstatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ApplicantDashboard.this, CheckStatus.class);
                intent.putExtra("ID", id);
                startActivity(intent);
            }
        });

        bookroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ApplicantDashboard.this, BookRoom.class);
                intent.putExtra("ID", id);
                intent.putExtra("NAME", name);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Logout successful...", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ApplicantDashboard.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}