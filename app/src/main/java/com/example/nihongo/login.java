package com.example.nihongo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

public class login extends BaseActivity {

    private FirebaseAuth auth;
    private FirebaseFirestore db;
    private EditText usernameInput, passwordInput;
    private Button linkForgotPw, btnLogin;
    private TextView linkSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher_nihongo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        usernameInput = findViewById(R.id.usernameInput);
        passwordInput = findViewById(R.id.passwordInput);
        linkForgotPw = findViewById(R.id.linkForgotPw);
        btnLogin = findViewById(R.id.btnLogin);
        linkSignUp = findViewById(R.id.linkSignUp);

        btnLogin.setOnClickListener(v -> {
            String username = usernameInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();

            if(username.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "Please enter your username and password.", Toast.LENGTH_SHORT).show();
                return;
            }

            db.collection("users")
                    .whereEqualTo("username", username)
                    .get()
                    .addOnCompleteListener(task -> {
                        if(task.isSuccessful() && !task.getResult().isEmpty()){
                            String email = null;
                            String course = null;
                            for(QueryDocumentSnapshot document: task.getResult()){
                                email = document.getString("email");
                                course = document.getString("course");
                                break;
                            }
                            if(email != null){
                                String chosenCourse = course;
                                auth.signInWithEmailAndPassword(email, password)
                                        .addOnCompleteListener(authTask -> {
                                            if(authTask.isSuccessful()){
                                                Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show();

                                                Intent intent = new Intent(login.this, MainActivity.class);
                                                intent.putExtra("course", chosenCourse);
                                                startActivity(intent);
                                                finish();
                                            }
                                            else{
                                                Toast.makeText(this, "Login unsuccessful.", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }
                            else{
                                Toast.makeText(this, "Username not found.", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(this, "Username not found.", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        });

        linkForgotPw.setOnClickListener(v -> {
            Intent intent = new Intent(login.this, forgotpw.class);
            startActivity(intent);
        });

        linkSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(login.this, signup.class);
            startActivity(intent);
        });

    }
}