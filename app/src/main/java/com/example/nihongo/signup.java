package com.example.nihongo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

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

        btnCreateAcc.setOnClickListener(v -> registerUser());

        linkLogin.setOnClickListener(v -> {
            Intent intent = new Intent(signup.this, login.class);
            startActivity(intent);
        });
    }

    private void registerUser() {
        String email = emailInput.getText().toString().trim();
        String password = passwordInputSU.getText().toString().trim();
        String username = usernameInputSU.getText().toString().trim();
        String birthdate = birthdateInput.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty() || username.isEmpty() || birthdate.isEmpty()) {
            Toast.makeText(this, "Please fill out all fields.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6) {
            showPasswordErrorDialog();
            return;
        }

        checkUsernameExists(username, email, password, birthdate);
    }

    private void checkUsernameExists(String username, String email, String password, String birthdate) {
        db.collection("users")
                .whereEqualTo("username", username)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        QuerySnapshot querySnapshot = task.getResult();
                        if (querySnapshot != null && !querySnapshot.isEmpty()) {
                            Toast.makeText(this, "Username already taken. Choose another one.", Toast.LENGTH_SHORT).show();
                        } else {
                            createFirebaseUser(email, password, username, birthdate);
                        }
                    } else {
                        Toast.makeText(this, "Error checking username availability.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void createFirebaseUser(String email, String password, String username, String birthdate) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        saveUserDataToFirestore(email, username, birthdate);
                    } else {
                        handleSignupError(task.getException());
                    }
                });
    }

    private void saveUserDataToFirestore(String email, String username, String birthdate) {
        String userId = auth.getCurrentUser().getUid();
        Map<String, Object> userData = new HashMap<>();
        userData.put("email", email);
        userData.put("username", username);
        userData.put("birthdate", birthdate);

        db.collection("users").document(userId)
                .set(userData)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(this, "Account created successfully!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(signup.this, login.class));
                    finish();
                })
                .addOnFailureListener(e -> Toast.makeText(this, "Failed to save data: " + e.getMessage(), Toast.LENGTH_SHORT).show());
    }

    private void handleSignupError(Exception e) {
        String errorMessage = "Registration failed.";
        if (e instanceof FirebaseAuthUserCollisionException) {
            errorMessage = "This email is already in use. Please use a different one.";
        } else if (e != null) {
            errorMessage = "Error: " + e.getMessage();
        }
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
    }

    private void showPasswordErrorDialog() {
        new android.app.AlertDialog.Builder(this)
                .setTitle("Invalid Password")
                .setMessage("Password must be at least 6 characters long.")
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                .show();
    }
}