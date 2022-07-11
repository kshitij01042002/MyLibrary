package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.net.Inet4Address;

public class StudentPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_page);
        getSupportActionBar().hide();

        findViewById(R.id.returnbook).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentPage.this,ReturnBook.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.issue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentPage.this,IssueBook.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.findbyname).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentPage.this,FindBookByName.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.findbynumber).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentPage.this,FindBookByNumber.class);
                startActivity(intent);
            }
        });
    }
}