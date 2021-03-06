package com.example.mylibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class StudentRegistration extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextName, editTextEmail, editTextPassword, editTextPhone;
    Spinner editTextJobProfile;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_student_registration);


        editTextName = findViewById(R.id.name);
        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        editTextPhone = findViewById(R.id.phone);
        editTextJobProfile = findViewById(R.id.branch);
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.register).setOnClickListener(this);
    }

    private void registerUser() {
        String name = editTextName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();
        String branch =  editTextJobProfile.getSelectedItem().toString();

        if (name.isEmpty()) {
            editTextName.setError(getString(R.string.input_error_name));
            editTextName.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            editTextEmail.setError(getString(R.string.input_error_email));
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError(getString(R.string.input_error_email_invalid));
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError(getString(R.string.input_error_password));
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPassword.setError(getString(R.string.input_error_password_length));
            editTextPassword.requestFocus();
            return;
        }

        if (phone.isEmpty()) {
            editTextPhone.setError(getString(R.string.input_error_phone));
            editTextPhone.requestFocus();
            return;
        }

        if (phone.length() != 10) {
            editTextPhone.setError(getString(R.string.input_error_phone_invalid));
            editTextPhone.requestFocus();
            return;
        }


        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            User user = new User(
                                    name,
                                    email,
                                    phone,
                                    branch
                            );

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(editTextName.getText().toString().trim())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    editTextEmail.setText("");
                                    editTextPassword.setText("");
                                    editTextPhone.setText("");
                                    editTextJobProfile.setSelection(0);
                                    editTextName.setText("");
                                    progressBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {
                                        Toast.makeText(StudentRegistration.this, getString(R.string.registration_success), Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(StudentRegistration.this, "Failed", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        }
                        if (mAuth.getCurrentUser() != null) {
                            Toast.makeText(StudentRegistration.this, "User Already Exists", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(StudentRegistration.this, task.getException().toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register:
                registerUser();
                break;
        }
    }
}