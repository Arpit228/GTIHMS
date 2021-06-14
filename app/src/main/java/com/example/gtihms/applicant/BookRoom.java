package com.example.gtihms.applicant;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.gtihms.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class BookRoom extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_room);

        ImageButton back = findViewById(R.id.backrequest);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void bookroom(View view){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference node = db.getReference();

        Intent intent = getIntent();
        String id = intent.getStringExtra("ID");
        String name = intent.getStringExtra("NAME");

        EditText prg = findViewById(R.id.programme);
        EditText loc = findViewById(R.id.location);
        EditText sd = findViewById(R.id.startDate);
        EditText ed = findViewById(R.id.endDate);

        String programme = prg.getText().toString().trim();
        String location = loc.getText().toString().trim();
        String startdate = sd.getText().toString().trim();
        String enddate = ed.getText().toString().trim();
        String status = "Pending";
        String alloted_room = "0";

        if(programme.isEmpty() || location.isEmpty() || startdate.isEmpty() || enddate.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Fill all fields", Toast.LENGTH_SHORT).show();
        }
        else if(!checkDate(startdate)){
            Toast.makeText(getApplicationContext(), "Check start date", Toast.LENGTH_SHORT).show();
        }
        else if(!checkDate(enddate)){
            Toast.makeText(getApplicationContext(), "Check end date", Toast.LENGTH_SHORT).show();
        }
        else {
            String start = startdate.substring(0,2) + "/" + startdate.substring(2,4) + "/" + startdate.substring(4);
            String end = enddate.substring(0,2) + "/" + enddate.substring(2,4) + "/" + enddate.substring(4);
            BookingData obj = new BookingData(name, programme, location, start, end, status, alloted_room);
            node.child("Room_Requests").child(id).setValue(obj);
            Toast.makeText(getApplicationContext(), "Request registered", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    boolean checkDate(String date) {
        boolean result = false;
        if(date.length() == 8) {
            int dd = Integer.parseInt(date.substring(0,2));
            int mm = Integer.parseInt(date.substring(2,4));
            int yy = Integer.parseInt(date.substring(4));

            if(yy == 2021){
                switch (mm){
                    case 1 :
                    case 3 :
                    case 5 :
                    case 7 :
                    case 8 :
                    case 10 :
                    case 12 : if(dd > 0 && dd < 32) result = true; break;
                    case 2 : if(dd > 0 && dd < 29) result = true; break;
                    case 4 :
                    case 6 :
                    case 9 :
                    case 11: if(dd > 0 && dd < 31) result = true; break;
                }
            }
        }
        return result;
    }
}