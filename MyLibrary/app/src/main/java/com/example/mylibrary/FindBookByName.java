package com.example.mylibrary;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class FindBookByName extends AppCompatActivity {

    EditText bookid;
    Button submit;
    ListView status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_book_by_name);
        getSupportActionBar().hide();

        bookid = findViewById(R.id.bookname);
        submit = findViewById(R.id.submit);
        status = findViewById(R.id.list);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> a = new ArrayList<>();
                ArrayAdapter arrayAdapter = new ArrayAdapter<>(FindBookByName.this, R.layout.items, a);
                status.setAdapter(arrayAdapter);
                FirebaseFirestore.getInstance().collection("Books").whereEqualTo("Name",bookid.getText().toString().trim())
                        .addSnapshotListener(new EventListener<QuerySnapshot>() {
                            @Override
                            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                                a.add("No Such Book");
                                Toast.makeText(FindBookByName.this, "Check Spelling of the book", Toast.LENGTH_SHORT).show();
                                a.clear();
                                for (QueryDocumentSnapshot document: value){
                                        String t =("Name: " + document.getString("Name")
                                                + "\nBook ID: " + document.getString("Number")
                                                + "\nAvailability: " + document.getString("Availability")
                                                + "\nGenre: " + document.getString("Genre"));
                                        a.add(t);
                                }
                                arrayAdapter.notifyDataSetChanged();
                            }
                        });
            }
        });
    }
}