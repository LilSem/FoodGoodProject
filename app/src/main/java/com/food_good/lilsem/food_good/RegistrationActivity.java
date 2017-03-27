package com.food_good.lilsem.food_good;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    private EditText etValidatePassword;
    private EditText etValidateEmail;
    private EditText etValidatePassword2;
    EditText etLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        mAuth = FirebaseAuth.getInstance();

        etValidateEmail = (EditText) findViewById(R.id.etValidateEmail);
        etValidatePassword = (EditText) findViewById(R.id.etValidatePassword);
        etValidatePassword2 = (EditText) findViewById(R.id.etValidatePassword2);
        findViewById(R.id.btnValidateRegister).setOnClickListener(this);

        etLocation = (EditText) findViewById(R.id.etLocation);


        etLocation.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    showPlacePickerDialog();
                }
                return false;
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnValidateRegister) {
            if (etValidateEmail.getText().toString().isEmpty() || etValidatePassword.getText().toString().isEmpty()
                    || etValidatePassword2.getText().toString().isEmpty()) {
                Toast.makeText(RegistrationActivity.this, "Поля не должны быть пустыми", Toast.LENGTH_SHORT).show();
            }else if (!etValidatePassword.getText().toString().equals(etValidatePassword2.getText().toString())){
                Toast.makeText(RegistrationActivity.this, "Введенные пароли не совпадают!", Toast.LENGTH_SHORT).show();
            }else {
                registration(etValidateEmail.getText().toString(), etValidatePassword.getText().toString());
            }
        }
    }

    private void registration(final String email, final String password) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                View view = new View(RegistrationActivity.this);
                if (task.isSuccessful()) {
                    Toast.makeText(RegistrationActivity.this, "Регистрация успешна", Toast.LENGTH_SHORT).show();
                    signIn(email, password);
                } else {
                    Toast.makeText(RegistrationActivity.this, "Регистрация провалена", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(RegistrationActivity.this, "Авторизация успешна", Toast.LENGTH_SHORT).show();
                    viewActivity();
                } else {
                    Toast.makeText(RegistrationActivity.this, "Авторизация провалена", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void viewActivity() {
        Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }


    private void showPlacePickerDialog() {

        PlaceSearchDialog placeSearchDialog = new PlaceSearchDialog.Builder(this)
                //.setHeaderImage(R.drawable.dialog_header)
                .setLocationNameListener(new PlaceSearchDialog.LocationNameListener() {
                    @Override
                    public void locationName(String locationName) {
                        etLocation.setText(locationName);
                    }
                })
                .build();
        placeSearchDialog.show();
    }
}
