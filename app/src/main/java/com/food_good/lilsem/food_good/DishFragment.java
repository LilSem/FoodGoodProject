package com.food_good.lilsem.food_good;


import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.food_good.lilsem.food_good.model.Dish;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DishFragment extends Fragment implements DishAdapter.OnRecyclerViewItemClickListener {

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mReference;
    private FirebaseAuth mAuth;

    private RecyclerView mRecyclerView;
    private List<Dish> mList;
    private DishAdapter mAdapter;

    String id = "";
    String userRef;
    UUID idOne;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.dish_list, container, false);

        Bundle bundle = getArguments();
        if (bundle != null) {
            id = bundle.getString("id");
        }

        Context context = getActivity();
        mList = new ArrayList<>();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.dish_list);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new DishAdapter(mList, this);
        mRecyclerView.setAdapter(mAdapter);
        mFirebaseDatabase = FirebaseDatabase.getInstance();

        mReference = mFirebaseDatabase.getReference();

        updateList();

        mAuth = FirebaseAuth.getInstance();
        // получаем ссылку на пользователя
        FirebaseUser user = mAuth.getCurrentUser();
        userRef = user.getUid();

        return view;
    }

    private void updateList() {
        if (id.isEmpty()) {
            mReference.child("dishes").addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    mList.add(dataSnapshot.getValue(Dish.class));
                    mAdapter.notifyDataSetChanged();
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    Dish model = dataSnapshot.getValue(Dish.class);
                    int index = getItemIndex(model);
                    mList.set(index, model);
                    mAdapter.notifyItemChanged(index);

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    Dish model = dataSnapshot.getValue(Dish.class);
                    int index = getItemIndex(model);

                    mList.remove(index);
                    mAdapter.notifyItemRemoved(index);
                }


                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        } else {
            mReference.child("dishes").orderByChild("restaurantId").equalTo(id).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    mList.add(dataSnapshot.getValue(Dish.class));
                    mAdapter.notifyDataSetChanged();
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    Dish model = dataSnapshot.getValue(Dish.class);
                    int index = getItemIndex(model);
                    mList.set(index, model);
                    mAdapter.notifyItemChanged(index);

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    Dish model = dataSnapshot.getValue(Dish.class);
                    int index = getItemIndex(model);

                    mList.remove(index);
                    mAdapter.notifyItemRemoved(index);
                }


                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }


    private int getItemIndex(Dish dish) {
        int index = -1;
        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i).key.equals(dish.key)) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public void onClick(int position) {
        Toast.makeText(getActivity(), "test" + position, Toast.LENGTH_SHORT).show();

        // в нужную ветку БД помещаем значение

        String key = mReference.child("basket").push().getKey();
        mReference.child("basket").child(key).child("userId").setValue(userRef);
        mReference.child("basket").child(key).child("dishId").setValue(mList.get(position).id);
        mReference.child("basket").child(key).child("restaurantId").setValue(mList.get(position).restaurantId);
        mReference.child("basket").child(key).child("weight").setValue(mList.get(position).weight);
        mReference.child("basket").child(key).child("title").setValue(mList.get(position).title);
        mReference.child("basket").child(key).child("composition").setValue(mList.get(position).composition);
        mReference.child("basket").child(key).child("price").setValue(mList.get(position).price);

    }
}
