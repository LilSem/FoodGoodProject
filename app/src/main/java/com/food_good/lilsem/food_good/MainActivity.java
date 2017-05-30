package com.food_good.lilsem.food_good;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
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

    BasketFragment mBasketFragment;
    AccountFragment accountFragment;
    RestaurantFragment restaurantFragment;
    DishFragment dishFragment;
    EventFragment eventFragment;

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

        updateUI();
        getRestaurant();

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_basket:
                final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                mBasketFragment = new BasketFragment();
                fragmentTransaction.replace(R.id.content_main, mBasketFragment)
                        .commit();
                mToolbar.setTitle("Корзина");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        int id = item.getItemId();

        if (id == R.id.nav_promotions) {

            eventFragment = new EventFragment();
            fragmentTransaction.replace(R.id.content_main, eventFragment)
                    .commit();
            mToolbar.setTitle("Акции");

        } else if (id == R.id.nav_restaurant) {

            restaurantFragment = new RestaurantFragment();
            fragmentTransaction.replace(R.id.content_main, restaurantFragment)
                    .commit();
            mToolbar.setTitle("Рестораны");

        } else if (id == R.id.nav_dishes) {

            dishFragment = new DishFragment();
            fragmentTransaction.replace(R.id.content_main, dishFragment)
                    .commit();
            mToolbar.setTitle("Блюда");

        } else if (id == R.id.nav_basket) {

            mBasketFragment = new BasketFragment();
            fragmentTransaction.replace(R.id.content_main, mBasketFragment)
                    .commit();
            mToolbar.setTitle("Корзина");

        } else if (id == R.id.nav_history) {

        } else if (id == R.id.nav_settings) {

            accountFragment = new AccountFragment();
            fragmentTransaction.replace(R.id.content_main, accountFragment)
                    .commit();
            mToolbar.setTitle("Профиль");

        } else if (id == R.id.nav_exit) {
            this.finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void updateUI() {
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

    private void getRestaurant(){
        final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        RestaurantFragment fragment = new RestaurantFragment();
        fragmentTransaction.add(R.id.content_main, fragment);
        fragmentTransaction.commit();
    }
}