package com.food_good.lilsem.food_good;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AccountFragment extends Fragment {

        private FirebaseAuth mAuth;
        private DatabaseReference mReference;

        Button mButton;
        TextView twNameUser;
        TextView twEmail;
        TextView twCity;
        TextView twBonus;

        String userRef;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        //получаем ссылку на нашу базу данных
        mReference = FirebaseDatabase.getInstance().getReference();

    }
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_account, container, false);

        mButton = (Button) view.findViewById(R.id.btnChangeUser);

        twNameUser = (TextView) view.findViewById(R.id.twNameUserAccount);
        twEmail = (TextView) view.findViewById(R.id.twEmailUserAccount);
        twCity = (TextView) view.findViewById(R.id.twCityUserAccount);
        twBonus = (TextView) view.findViewById(R.id.twBonusUserAccount);

        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // получаем ссылку на пользователя
                FirebaseUser user = mAuth.getCurrentUser();
                userRef = user.getUid();


                twNameUser.setText(dataSnapshot.child("users").child(userRef).child("name").getValue().toString());
                twBonus.setText(dataSnapshot.child("users").child(userRef).child("bonus").getValue().toString());
                twEmail.setText(dataSnapshot.child("users").child(userRef).child("email").getValue().toString());
                twCity.setText(dataSnapshot.child("users").child(userRef).child("city").getValue().toString());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.getInstance().signOut();
                Intent intent = new Intent(v.getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

}
