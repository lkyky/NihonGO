package com.example.nihongo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class signup extends BaseActivity {

    private FirebaseAuth auth;
    private FirebaseFirestore db;
    private EditText emailInput, birthdateInput, passwordInputSU, usernameInputSU;
    private Button btnCreateAcc;
    private TextView linkLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher_nihongo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        emailInput = findViewById(R.id.emailInput);
        birthdateInput = findViewById(R.id.birthdateInput);
        passwordInputSU = findViewById(R.id.passwordInputSU);
        usernameInputSU = findViewById(R.id.usernameInputSU);
        btnCreateAcc = findViewById(R.id.btnCreateAcc);
        linkLogin = findViewById(R.id.linkLogin);

        btnCreateAcc.setOnClickListener(v -> {
            String email = emailInput.getText().toString().trim();
            String password = passwordInputSU.getText().toString().trim();
            String username = usernameInputSU.getText().toString().trim();
            String birthdate = birthdateInput.getText().toString().trim();

            if(email.isEmpty() || password.isEmpty() || username.isEmpty() || birthdate.isEmpty()){
                Toast.makeText(this, "Please fill out all fields.", Toast.LENGTH_SHORT).show();
                return;
            }

            auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if(task.isSuccessful()){
                            String userId = auth.getCurrentUser().getUid();
                            Map<String, Object> userData = new HashMap<>();
                            userData.put("email", email);
                            userData.put("username", username);
                            userData.put("birthdate", birthdate);

                            db.collection("users").document(userId)
                                    .set(userData)
                                    .addOnSuccessListener(aVoid -> {
                                        Toast.makeText(this, "Account created successfully!", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(signup.this, login.class);
                                        startActivity(intent);
                                        finish();
                                    })
                                    .addOnFailureListener(e -> {
                                        Toast.makeText(this, "Failed to save data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                    });
                        }
                    });
        });

        linkLogin.setOnClickListener(v -> {
            Intent intent = new Intent(signup.this, login.class);
            startActivity(intent);
        });

    }
}