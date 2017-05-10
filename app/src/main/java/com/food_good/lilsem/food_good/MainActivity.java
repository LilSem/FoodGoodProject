package com.food_good.lilsem.food_good;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    AccountFragment accountFragment;
    Toolbar mToolbar;

    TextView twNavBarName;
    TextView twNavBarEmail;


    private FirebaseAuth mAuth;
    private DatabaseReference mReference;

    private String userRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);

        twNavBarName = (TextView) header.findViewById(R.id.nav_header_tw_name);
        twNavBarEmail = (TextView) header.findViewById(R.id.nav_header_tw_email);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setTitle(getString(R.string.app_title));



        mAuth = FirebaseAuth.getInstance();
        // получаем ссылку на пользователя
        FirebaseUser user = mAuth.getCurrentUser();
        userRef = user.getUid();

        //получаем ссылку на нашу базу данных
        mReference = FirebaseDatabase.getInstance().getReference();



        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                twNavBarName.setText(dataSnapshot.child("users").child(userRef).child("name").getValue().toString());
                twNavBarEmail.setText(dataSnapshot.child("users").child(userRef).child("email").getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewBasket();
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_promotions) {

        } else if (id == R.id.nav_restaurant) {

        } else if (id == R.id.nav_basket) {
            viewBasket();
        } else if (id == R.id.nav_history) {

        } else if (id == R.id.nav_settings) {

            accountFragment = new AccountFragment();
            final FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.fragment, accountFragment)
                    .addToBackStack(null)
                    .commit();
            mToolbar.setTitle("Профиль");
        } else if (id == R.id.nav_exit) {
            this.finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void viewBasket() {
        Intent intent = new Intent(MainActivity.this, BasketActivity.class);
        startActivity(intent);
    }
}