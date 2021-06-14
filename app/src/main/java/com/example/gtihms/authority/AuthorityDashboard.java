package com.example.gtihms.authority;

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

public class AuthorityDashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authority_dashboard);

        Intent intent = getIntent();
        String id = intent.getStringExtra("ID");
        String name = intent.getStringExtra("NAME");
        String welcomemsg = "Welcome " + name;
        TextView welcome = findViewById(R.id.welcomeauth);
        welcome.setText(welcomemsg);

        Button update = findViewById(R.id.updateauth);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AuthorityDashboard.this, Update.class);
                intent.putExtra("ID", id);
                intent.putExtra("PROFILE", "Authority");
                startActivity(intent);
            }
        });

        Button view_requests = findViewById(R.id.viewrequest);
        view_requests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AuthorityDashboard.this, AttendRequest.class);
                startActivity(intent);
            }
        });

        Button edit_rooms = findViewById(R.id.editroom);
        edit_rooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AuthorityDashboard.this, Room.class);
                startActivity(intent);
            }
        });

        Button logout = findViewById(R.id.authoritylogout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Logout successful...", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AuthorityDashboard.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}