package com.example.nihongo;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class forgotpw extends BaseActivity {

    private FirebaseAuth auth;
    private EditText emailInputFP;
    private Button btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpw);

        auth = FirebaseAuth.getInstance();

        emailInputFP = findViewById(R.id.emailInputFP);
        btnConfirm = findViewById(R.id.btnConfirm);

        btnConfirm.setOnClickListener(v -> {
            String email = emailInputFP.getText().toString().trim();
            if (validateEmail(email)) {
                sendPasswordResetEmail(email);
            }
        });
        setupBackBtn();
    }

    private boolean validateEmail(String email){
        if (TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please enter your email address.", Toast.LENGTH_SHORT).show();
            return  false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(this, "Please enter a valid email address.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void sendPasswordResetEmail(String email){
        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        Toast.makeText(this, "Password reset email has been sent successfully. Please check your inbox.", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else {
                        String errorMessage = "Error sending password reset email.";
                        if (task.getException() != null){
                            errorMessage += " " + task.getException().getMessage();
                        }
                        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
                    }
                });
    }
}