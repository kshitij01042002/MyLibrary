package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LibrarianLogin extends AppCompatActivity {

    EditText id,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_librarian_login);

        getSupportActionBar().hide();

    }

    public void AdminActivity(View view){
        Intent intent = new Intent(LibrarianLogin.this,LibrarianPage.class);

        id = findViewById(R.id.id);
        password = findViewById(R.id.password);
        String adminid = id.getText().toString().trim();
        String pass = password.getText().toString().trim();
        if (adminid.contentEquals("Kshitij") && pass.contentEquals("123456")){
            id.setText("");
            password.setText("");
            startActivity(intent);
        }
        else {
            Toast.makeText(LibrarianLogin.this, "Wrong Credentials", Toast.LENGTH_SHORT).show();
        }

    }
}