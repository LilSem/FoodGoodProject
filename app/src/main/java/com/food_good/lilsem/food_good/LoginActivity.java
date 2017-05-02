package com.food_good.lilsem.food_good;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private EditText etPassword;
    private EditText etEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    viewActivity();

                } else {
                    // User is signed out
                }
            }
        };
        etEmail = (EditText) findViewById(R.id.twEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        findViewById(R.id.btnLogin).setOnClickListener(this);
        findViewById(R.id.btnRegister).setOnClickListener(this);

        FirebaseUser user = mAuth.getInstance().getCurrentUser();
        if (user != null){
            viewActivity();
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnLogin){
            if(etEmail.getText().toString().isEmpty() || etPassword.getText().toString().isEmpty()) {
                Toast.makeText(LoginActivity.this, "Поля не должны быть пустыми", Toast.LENGTH_SHORT).show();
            }else {
                signIn(etEmail.getText().toString(), etPassword.getText().toString());
            }
        }else if(view.getId() == R.id.btnRegister){
            Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
            startActivity(intent);
        }
    }

    public void signIn(String email, String password){
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, "Авторизация успешна", Toast.LENGTH_SHORT).show();
                    viewActivity();
                } else {
                    Toast.makeText(LoginActivity.this, "Авторизация провалена", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void viewActivity(){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
