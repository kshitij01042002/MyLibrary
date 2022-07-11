package com.example.mylibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class StudentLogin extends AppCompatActivity {

    EditText studentmail,studentpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);

        getSupportActionBar().hide();

        findViewById(R.id.forgetpassword).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentLogin.this,ForgetPassword.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.studentregister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentLogin.this,StudentRegistration.class);
                startActivity(intent);
            }
        });


        studentmail = findViewById(R.id.studentmail);
        studentpassword = findViewById(R.id.studentpassword);

        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = studentmail.getText().toString();
                String password = studentpassword.getText().toString();
                if (email.isEmpty()) {
                    studentmail.setError(getString(R.string.input_error_email));
                    studentmail.requestFocus();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    studentmail.setError(getString(R.string.input_error_email_invalid));
                    studentmail.requestFocus();
                    return;
                }

                if (password.isEmpty()) {
                    studentpassword.setError(getString(R.string.input_error_password));
                    studentpassword.requestFocus();
                    return;
                }

                if (password.length() < 6) {
                    studentpassword.setError(getString(R.string.input_error_password_length));
                    studentpassword.requestFocus();
                    return;
                }
                else {
                    regis(email,password);

                }
            }
        });
    }
    private void regis(String email, String password) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(StudentLogin.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(StudentLogin.this,StudentPage.class);
                    studentmail.setText("");
                    studentpassword.setText("");
                    startActivity(intent);
                } else {
                    Toast.makeText(StudentLogin.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

