package com.food_good.lilsem.food_good;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OtherInfoUserLayoutActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference mReference;

    private EditText etName;
    private EditText etPhoneNumber;
    private EditText etLocation;
    Button btnEndRegister;

    String userRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_info_user);
        setTitle("Дополнительная информация");
        mAuth = FirebaseAuth.getInstance();

        //получаем ссылку на нашу базу данных
        mReference = FirebaseDatabase.getInstance().getReference();

        etName = (EditText)  findViewById(R.id.etNameUser);
        etPhoneNumber = (EditText)  findViewById(R.id.etPhoneNumber);
        etLocation = (EditText) findViewById(R.id.etLocation);
        etPhoneNumber.addTextChangedListener(new PhoneNumberFormattingTextWatcher());

        etLocation.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    showPlacePickerDialog();
                }
                return false;
            }
        });

        btnEndRegister = (Button) findViewById(R.id.btnValidateUserInfo);
        btnEndRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // получаем ссылку на пользователя
                FirebaseUser user = mAuth.getCurrentUser();
                userRef = user.getUid();

                // в нужную ветку БД помещаем значение
                mReference.child("users").child(userRef).child("name").setValue(etName.getText().toString());
                mReference.child("users").child(userRef).child("phone").setValue(etPhoneNumber.getText().toString());
                mReference.child("users").child(userRef).child("city").setValue(etLocation.getText().toString());
                mReference.child("users").child(userRef).child("email").setValue(user.getEmail());
                mReference.child("users").child(userRef).child("bonus").setValue("0");
               viewActivity();
            }
        });
    }

    public void viewActivity() {
        Intent intent = new Intent(OtherInfoUserLayoutActivity.this, MainActivity.class);
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
