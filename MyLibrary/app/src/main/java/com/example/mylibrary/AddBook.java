package com.example.mylibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddBook extends AppCompatActivity {

    EditText name,number;
    Spinner branch,available;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        getSupportActionBar().hide();

        branch = findViewById(R.id.branch);

        name = findViewById(R.id.name);
        number = findViewById(R.id.number);
        available = findViewById(R.id.available);
        String name1 = name.getText().toString().trim();
        String number1 = number.getText().toString().trim();
        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().isEmpty() || number.getText().toString().isEmpty()){
                    Toast.makeText(AddBook.this, "Enter All Details", Toast.LENGTH_SHORT).show();
                }
                else {
                    addbook();
                }
            }
        });
    }

    private void addbook(){
        Map<String, String> v = new HashMap<>();
        v.put("Name",name.getText().toString().trim());
        v.put("Number",number.getText().toString().trim());
        v.put("Availability",available.getSelectedItem().toString());
        v.put("Genre",branch.getSelectedItem().toString());
        db.collection("Books").document(number.getText().toString()).set(v).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    name.setText("");
                    number.setText("");
                    branch.setSelection(0);
                    available.setSelection(0);
                    Toast.makeText(AddBook.this, "Book Registered Successfully", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(AddBook.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}