package com.food_good.lilsem.food_good;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.food_good.lilsem.food_good.model.Basket;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class BasketFragment extends Fragment implements BasketAdapter.OnRecyclerViewItemClickListener {


    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mReference;
    private FirebaseAuth mAuth;

    private RecyclerView mRecyclerView;
    TextView tvSumDishes;
    private List<Basket> mList;

    private BasketAdapter mAdapter;

    String userRef;
    int index = -1;

    public BasketFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.basket_list, container, false);

        mAuth = FirebaseAuth.getInstance();
        // получаем ссылку на пользователя
        FirebaseUser user = mAuth.getCurrentUser();
        Context context = getActivity();
        mList = new ArrayList<>();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.basket_list);
        tvSumDishes = (TextView) view.findViewById(R.id.tv_sum_dishes);

        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new BasketAdapter(mList, this);
        mRecyclerView.setAdapter(mAdapter);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mReference = mFirebaseDatabase.getReference();

        userRef = user.getUid();

        updateList();
        return view;
    }

    private void updateList() {
        mReference.child("basket").orderByChild("userId").equalTo(userRef).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mList.add(dataSnapshot.getValue(Basket.class));
                mAdapter.notifyDataSetChanged();
                getSumDishes();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Basket model = dataSnapshot.getValue(Basket.class);
                    int index = getItemIndex(model);
                    mList.set(index, model);
                    mAdapter.notifyItemChanged(index);
                getSumDishes();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Basket model = dataSnapshot.getValue(Basket.class);
                int index = getItemIndex(model);
                mList.remove(index);
                mAdapter.notifyItemRemoved(index);
                getSumDishes();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }


    private int getItemIndex(Basket basket) {

        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i) != null && mList.get(i).orderId.equals(basket.orderId)) {
                index = i;
                break;
            }
        }
        return index;
    }

    private void getSumDishes() {
        int sum = 0;
        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i).price != null) {
                sum += Integer.parseInt(mList.get(i).price);
            }
        }
        tvSumDishes.setText("Сумма заказа: " + sum);
    }

    @Override
    public void onClick(int position) {

            removeOrder(position);

    }

    private void removeOrder(int position) {
                mReference.child("basket").child(mList.get(position).orderId).removeValue();
        }
}

