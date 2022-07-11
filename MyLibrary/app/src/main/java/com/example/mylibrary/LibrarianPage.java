package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LibrarianPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_librarian_page);

        getSupportActionBar().hide();

        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LibrarianPage.this,AddBook.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.issue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LibrarianPage.this,IssueBook.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.findbynumber).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LibrarianPage.this,FindBookByNumber.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.findbyname).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LibrarianPage.this,FindBookByName.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.returnbook).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LibrarianPage.this,ReturnBook.class);
                startActivity(intent);
            }
        });
    }
}